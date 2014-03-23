package modles;

import myLibs.GSonRequest;
import myLibs.RequestManager;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.net.Uri;

public class API
{
	private static volatile API	smAPI	= null;
	
	private Uri					mMainUri;
	GsonBuilder					mGsonBuilder;
	Gson						mGson;
	
	public static API getAPI()
	{
		if (smAPI == null)
		{
			synchronized (API.class)
			{
				if (smAPI == null)
				{
					smAPI = new API();
				}
			}
		}
		return smAPI;
	}
	
	public void init(String mainurl)
	{
		mMainUri = Uri.parse(mainurl);
		mGsonBuilder = new GsonBuilder();
		mGson = mGsonBuilder.create();
	}
	
	public <T extends TuitangJson> void getScenince(Class<T> imClass,
			int imageType, final Response.Listener<T> listener,
			Response.ErrorListener eListener)
	{
		String ImageUrl = getImageUrl(imClass.getSimpleName(),imageType);
		GSonRequest<T> request = new GSonRequest<T>(Method.GET, ImageUrl,
				imClass, listener, eListener, mGsonBuilder);
		RequestManager.getQueue().add(request);
	}
	
	public <T> void getFravoras(Class<T> imClass, int imageType,
			final Response.Listener<T> listener,
			Response.ErrorListener eListener)
	{
		String ImageUrl = getImageUrl(imClass.getSimpleName(), imageType);
		GSonRequest<T> request = new GSonRequest<T>(Method.GET, ImageUrl,
				imClass, listener, eListener, mGsonBuilder);
		RequestManager.getQueue().add(request);
	}
	
	private String getImageUrl(String category, int ImageType)
	{
		Uri.Builder uBuilder = mMainUri.buildUpon();
		if ("TuitangJson".equals(category))
		{
			uBuilder.appendPath("" + ImageType);
			uBuilder.appendPath("24");
		}
		else if ("FravoraInfo".equals(category))
		{
			uBuilder.appendPath("" + ImageType);
			uBuilder.appendPath("24");
		}

		return uBuilder.build().toString();
	}
}
