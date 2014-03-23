package Adapters;



import java.util.ArrayList;
import java.util.List;



import modles.API;
import modles.FravoraInfo;

import Adapters.EndlessAdapter.EndlessListAdapter;
import Adapters.EndlessAdapter.cacheNotifer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.volley.Response;
import com.android.volley.VolleyError;
public class FravorableAdapter <T> extends BaseAdapter  implements Response.Listener<T>,EndlessListAdapter,Response.ErrorListener
{
     List<FravoraInfo> mFravoraList =new ArrayList<FravoraInfo>();
     cacheNotifer mNotifer;
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mFravoraList.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return mFravoraList.get(position);
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
		return null;
	}

	@Override
	public void onResponse(T arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
@Override
	public void onErrorResponse(VolleyError arg0)
	{
		// TODO Auto-generated method stub
		
	}

	//获取网络数据
	@Override
	public void cachedoInBlack(cacheNotifer cacheNotifer) throws Exception
	{
		// TODO Auto-generated method stub
		mNotifer = cacheNotifer;
		
	}
	
}
