package myLibs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;

/**
 * Created by Herb on 13-10-29.
 */
public class ImageCacheManager{

    public enum CacheType {
        DISK, MEMORY
    }

    private static ImageCacheManager mInstance;
    private ImageLoader mImageLoader;
    private ImageCache mImageCache;

    public static ImageCacheManager getInstance(){
        if(mInstance == null)
            mInstance = new ImageCacheManager();

        return mInstance;
    }

    public void init(ImageCache imageCache){
        mImageCache = imageCache;
        mImageLoader = new ImageLoader(RequestManager.getQueue(), mImageCache);
    }

    public void init(Context context, String uniqueName, int cacheSize, CompressFormat compressFormat, int quality, CacheType type) {
        ImageCache imageCache;
        switch (type) {
            case DISK:
                imageCache = new DiskLruImageCache(context, uniqueName, cacheSize, compressFormat, quality);
                break;
            case MEMORY:
                imageCache = new BitmapLruImageCache(cacheSize);
                break;
            default:
                imageCache = new BitmapLruImageCache(cacheSize);
                break;
        }
        init(imageCache);
    }

    public Bitmap getBitmap(String url) {
        try {
            return mImageCache.getBitmap(createKey(url));
        } catch (NullPointerException e) {
            throw new IllegalStateException("Disk Cache Not initialized");
        }
    }

    public void putBitmap(String url, Bitmap bitmap) {
        try {
            mImageCache.putBitmap(createKey(url), bitmap);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Disk Cache Not initialized");
        }
    }

    public void getImage(String url, ImageListener listener){
        mImageLoader.get(url, listener);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    private String createKey(String url){
        return String.valueOf(url.hashCode());
    }

    public void clearCache() {
       if (mImageCache instanceof DiskLruImageCache) {
           DiskLruImageCache diskImageCache = (DiskLruImageCache) mImageCache;
           diskImageCache.clearCache();
       }
    }

}

