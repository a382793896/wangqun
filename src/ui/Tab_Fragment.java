package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loch.yh_android.R;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class Tab_Fragment extends Fragment implements OnClickListener
{
	private List<View> mTab   = new ArrayList<View>(4);
	private Map<View, Pair<Class<?>, Bundle>> mapViewArg = new HashMap<View, Pair<Class<?>,Bundle>>(4);
	public static final String mNavigation ="Navigation";
	private View currenView = null;
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		view =  inflater.inflate(R.layout.fragment_tab, null);
		Bundle argBundle = new Bundle();
		//添加景点框架
		addTab(view.findViewById(R.id.jingdian), ScenicsFragment.class, null);
		//添加优惠框架
		argBundle.putCharSequence(mNavigation, "fravoravle");
		addTab(view.findViewById(R.id.youhui), FavorableFragment.class, argBundle);
		return view;
	}
	public void addTab(View view,Class<?> class1,Bundle arg)
	{
		view.setOnClickListener(this);
		mTab.add(view);
		mapViewArg.put(view, new Pair<Class<?>, Bundle>(class1, arg));
		return;
	}
	//选择Tab栏目的项0 --- 3
	public void selectTab(int index)
	{
		if(index < 0 || index >mTab.size())
		{
			return;
		}
		onClick(mTab.get(index));
	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		if(currenView == v)
		{
			return;
		}
		FragmentManager fragmentManager =  getFragmentManager();
		 Pair<Class<?>, Bundle> argPair = mapViewArg.get(v);
		 Fragment fClassFragment = null;
		try
		{
			fClassFragment = (Fragment) argPair.first.newInstance();
		}
		catch (java.lang.InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 assert (fClassFragment != null);
		 fClassFragment.setArguments(argPair.second);
		 fragmentManager.beginTransaction().replace(R.id.content_frame, fClassFragment)
		 .commit();
		 
		 if(currenView != null)
			 currenView.setSelected(false);
		 currenView = v;
		 currenView.setSelected(true);
		 
		 return;
	}
	
	
}//end class
