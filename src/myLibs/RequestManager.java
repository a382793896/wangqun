package myLibs;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestManager
{
	private static RequestQueue reQueue;
	
	public RequestQueue init(Context context)
	{
		reQueue = Volley.newRequestQueue(context);
		return reQueue;
	}
	public static RequestQueue getQueue()
	{
		if(reQueue==null)
		{
			 throw new IllegalStateException("RequestQueue is not init");
		}
		return reQueue;
	}
}
