package Adapters;


import java.util.ArrayList;
import java.util.List;




import modles.API;
import modles.ImageInfo;
import modles.TuitangJson;



import com.loch.android.bitmapfun.util.ImageFetcher;
import com.loch.yh_android.R;


import Adapters.EndlessAdapter.cacheNotifer;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;


import com.android.volley.Response;

import com.android.volley.VolleyError;

public class StaggeredViewAdapter <T extends TuitangJson> extends BaseAdapter implements EndlessAdapter.EndlessListAdapter ,Response.Listener<T>,Response.ErrorListener
{
	List<ImageInfo> imageList = null;
	ImageFetcher mFetcher;
	Context mContext;
	LayoutInflater lInflater;
	EndlessAdapter.cacheNotifer notiferl;
	Class<T> mClass;
	private final  String	TAG = "StaggeredViewAdapter";
	
	public StaggeredViewAdapter(ImageFetcher mFetcher, Context mContext,Class<T> imageClass)
	{
		super();
		this.mFetcher = mFetcher;
		this.mContext = mContext;
		imageList = new ArrayList<ImageInfo>();
		lInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mClass = imageClass;
	}

	@Override
	public void cachedoInBlack(cacheNotifer cacheNotifer) throws Exception
	{
		// TODO Auto-generated method stub
		notiferl = cacheNotifer;
		API.getAPI().getScenince(mClass, 4, this, this);
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return imageList.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return imageList.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		ImageInfo imgInfo  = imageList.get(position);
		assert (imgInfo !=null);
		Log.i(TAG, "imgInfo height:"+imgInfo.getIht());
		Log.i(TAG, "imgInfo msg:"+imgInfo.getMsg());
		Log.i(TAG, "imgInfo src:"+imgInfo.getIsrc());
		TextView textView =null ;
		ImageView imageView  = null;
		convertView=  (LinearLayout) lInflater.inflate(R.layout.image_cell, null);
		imageView =  (ImageView) convertView.findViewById(R.id.imageView);
		textView = (TextView) convertView.findViewById(R.id.textView);
		imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) imgInfo.getIht()));
		textView.setText(imgInfo.getMsg());
		mFetcher.loadImage(imgInfo.getIsrc(), imageView);
		convertView.setTag(imgInfo);
		return convertView;
	}

/*	public  void addItems(String url,int imageType)
	{
		getJson(url,imageType);
	}*/
/*	public void getJson(String url,int imageType)
	{
		Log.i(TAG ,"访问的地址是:"+url );
		RequestManager.getQueue().add(new JsonObjectRequest(Method.GET, url+imageType+"/24", null, this, null));
		
	}*/
	

	@Override
	public void onErrorResponse(VolleyError arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResponse(T arg0)
	{
		TuitangJson tuitangObj  = arg0;
		if(tuitangObj.isSuccess())
		{
			 tuitangObj.getData().getBlogs().add(tuitangObj.getData().getFirst_blog());
			 for (int i = 0; i < tuitangObj.getData().getBlogs().size(); i++)
			{
				 imageList.remove(tuitangObj.getData().getBlogs().get(i));
				 imageList.add(tuitangObj.getData().getBlogs().get(i));
			}
			
		}
		if(notiferl != null)
		{
				notiferl.onCacheCompleted(tuitangObj.getData().getBlogs().size() == 24);
		}
	}



	}
