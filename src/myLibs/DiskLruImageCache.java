package myLibs;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.jakewharton.disklrucache.DiskLruCache;


/**
 * Created by Herb on 13-10-29.
 */
public class DiskLruImageCache implements ImageCache {

    private static final int APP_VERSION = 1;
    private static final int VALUE_COUNT = 1;
    protected static int IO_BUFFER_SIZE = 8 * 1024;
    protected DiskLruCache mDiskCache;
    private CompressFormat mCompressFormat = CompressFormat.JPEG;
    private int mCompressQuality = 70;

    public DiskLruImageCache(Context context, String uniqueName, int diskCacheSize,
                             CompressFormat compressFormat, int quality) {
        try {
            final File diskCacheDir = getDiskCacheDir(context, uniqueName);
            mDiskCache = DiskLruCache.open(diskCacheDir, APP_VERSION, VALUE_COUNT, diskCacheSize);
            mCompressFormat = compressFormat;
            mCompressQuality = quality;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int computeImageSampleSize(int srcWidth, int srcHeight,
                                             int targetWidth, int targetHeight, boolean powerOf2Scale) {
        int scale = 1;

        int widthScale = srcWidth / targetWidth;
        int heightScale = srcHeight / targetHeight;

        if (powerOf2Scale) {
            while (srcWidth / 2 >= targetWidth || srcHeight / 2 >= targetHeight) { // ||
                srcWidth /= 2;
                srcHeight /= 2;
                scale *= 2;
            }
        } else {
            scale = Math.max(widthScale, heightScale); // max
        }

        if (scale < 1) {
            scale = 1;
        }

        return scale;
    }

    private boolean writeBitmapToFile(Bitmap bitmap, DiskLruCache.Editor editor)
            throws IOException {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(editor.newOutputStream(0), IO_BUFFER_SIZE);
            return bitmap.compress(mCompressFormat, mCompressQuality, out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected File getDiskCacheDir(Context context, String uniqueName) {
        final String cachePath = context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName);
    }

    protected String encodeKey(String key) {
        return key.toLowerCase().replaceAll("[^a-z0-9_-]", "-");
    }

    @Override
    public void putBitmap(String key, Bitmap data) {
        key = encodeKey(key);
        DiskLruCache.Editor editor = null;
        try {
            editor = mDiskCache.edit(key);
            if (editor == null) {
                return;
            }

            if (writeBitmapToFile(data, editor)) {
                mDiskCache.flush();
                editor.commit();
            } else {
                editor.abort();
            }
        } catch (IOException e) {
            try {
                if (editor != null) {
                    editor.abort();
                }
            } catch (IOException ignored) {
            }
        }
    }

    private Bitmap decodeBitmap(InputStream buffIn, BitmapFactory.Options options) {
        try {
            return BitmapFactory.decodeStream(buffIn, null, options);
        } catch (OutOfMemoryError e) {
            options.inSampleSize *= 2;
            return decodeBitmap(buffIn, options);
        }
    }

    @Override
    public Bitmap getBitmap(String key) {
        key = encodeKey(key);

        Bitmap bitmap = null;
        DiskLruCache.Snapshot snapshot = null;
        try {
            snapshot = mDiskCache.get(key);
            if (snapshot == null) {
                return null;
            }
            InputStream in = snapshot.getInputStream(0);
            if (in != null) {
                BufferedInputStream buffIn =
                        new BufferedInputStream(in, IO_BUFFER_SIZE);

                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(buffIn, null, o);

                BitmapFactory.Options o2 = new BitmapFactory.Options();
                o2.inSampleSize = 1;
                if(o.outWidth > 640 || o.outHeight > 302) {
                    o2.inPreferredConfig = Bitmap.Config.RGB_565;
                }

                snapshot.close();
                snapshot = mDiskCache.get(key);
                in = snapshot.getInputStream(0);
                buffIn = new BufferedInputStream(in, IO_BUFFER_SIZE);;
                bitmap = decodeBitmap(buffIn, o2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }
        return bitmap;

    }

    public boolean containsKey(String key) {

        boolean contained = false;
        DiskLruCache.Snapshot snapshot = null;
        try {
            snapshot = mDiskCache.get(key);
            contained = snapshot != null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }

        return contained;

    }

    public void clearCache() {
        try {
            FileUtils.deleteContents(getCacheFolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getCacheFolder() {
        return mDiskCache.getDirectory();
    }

}

