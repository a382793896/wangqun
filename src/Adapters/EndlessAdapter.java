package Adapters;


import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Activity;
import android.content.Context;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class EndlessAdapter extends AdapterWrapper
{
	protected Context	mContext;
	protected Activity	mActivity;
	AtomicBoolean keepOnAppendding = new AtomicBoolean(true);

	public EndlessAdapter(ListAdapter _wrapped, Context context
			)
	{
		super(_wrapped);
		this.mContext = context;

	}
	public interface cacheNotifer
	{
		void onCacheCompleted(boolean hasMore) ;

	}
	public void onDataReady(boolean hasmore)
	{
		keepOnAppendding.set(hasmore);
		notifyDataSetChanged();
	}
	public interface EndlessListAdapter  
	{
		void cachedoInBlack(cacheNotifer cacheNotifer ) throws Exception;
	}
	
	@Override
	public int getCount()
	{
		if(keepOnAppendding.get())
			 return super.getCount()+1;
		return super.getCount();
	}
	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		if(position > super.getCount())
			return null;
		return super.getItem(position);
	}
	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return super.getItemId(position);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		if(position == super.getCount() && keepOnAppendding.get())
		{
		
			try
			{
				cacheInBackGround();
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				keepOnAppendding.set(false);
				e.printStackTrace();
			}//获取网络数据
			TextView textView = new TextView(mContext);
			textView.setText("正在加载。。");
			return textView;
		}
		return super.getView(position, convertView, parent);
	}
	
	private void cacheInBackGround() throws Exception
	{
		ListAdapter listAdapter =  getwrapper();//
		if(listAdapter instanceof EndlessListAdapter)
		{
			EndlessListAdapter endlessListAdapter = (EndlessListAdapter) listAdapter;

				endlessListAdapter.cachedoInBlack(new cacheNotifer()
				{

					@Override
					public void onCacheCompleted(boolean hasMore)
					{
						// TODO Auto-generated method stub
						onDataReady(hasMore);
					}
					
				});
			}
		

	}
}
