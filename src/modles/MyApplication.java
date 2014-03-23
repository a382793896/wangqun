package modles;

import myLibs.ImageCacheManager;
import myLibs.RequestManager;
import android.app.Application;
import android.graphics.Bitmap;

public class MyApplication extends Application
{
	
	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();
		RequestManager reqManager = new RequestManager();
		reqManager.init(getApplicationContext());
		
		API.getAPI().init("http://www.duitang.com/album/1733789/masn/p/");
		
		ImageCacheManager.getInstance().init(getApplicationContext(),
				this.getPackageName(), 1024 * 1024 * 10,
				Bitmap.CompressFormat.PNG, 100,
				ImageCacheManager.CacheType.DISK);
	}
}
