package ui;




import modles.ImageInfo;
import modles.TuitangJson;
import myLibs.ImageCacheManager;

import com.loch.android.bitmapfun.util.ImageFetcher;
import com.loch.yh_android.R;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;

import Adapters.EndlessAdapter;
import Adapters.StaggeredViewAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScenicsFragment extends Fragment implements OnItemClickListener
{
	public static  final String	mainUrl	= "http://www.duitang.com/album/1733789/masn/p/";
	View					contentView;
	StaggeredGridView		staggeredGridView;
	ImageFetcher			imFetcher;
	StaggeredViewAdapter	mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		contentView = inflater.inflate(R.layout.scenics_fragment, null);
		staggeredGridView = (StaggeredGridView) contentView
				.findViewById(R.id.sv);
		staggeredGridView.setFastScrollEnabled(true);
	
			imFetcher =  new ImageFetcher(getActivity(), 240);
	
		mAdapter = new StaggeredViewAdapter(imFetcher, getActivity(),TuitangJson.class);
		
		staggeredGridView.setAdapter(new EndlessAdapter(mAdapter,getActivity()));
		staggeredGridView.setOnItemClickListener(this);                     
		mAdapter.notifyDataSetChanged();
		return contentView;
	}

	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position,
			long id)
	{
		// TODO Auto-generated method stub
		//Bundle argment = new Bundle();
		//argment.putCharSequence("item", value)
		if(position >= 0 && view != null)
		{
			ImageInfo item = (ImageInfo) view.getTag();
			Intent intent = new Intent(getActivity(),ItemActivity.class);
			intent.putExtra("item", item);//´«Êä¶ÔÏó
		}
	} 
	 
}
