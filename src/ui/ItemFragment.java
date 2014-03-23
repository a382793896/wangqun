package ui;

import java.util.ArrayList;
import java.util.List;

import com.loch.yh_android.R;

import modles.ImageInfo;
import modles.Item;
import Adapters.ItemAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ItemFragment extends Fragment {
	Context mContext;
	LinearLayout mainLayout;
	ListView mainListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mContext = inflater.getContext();
		mainLayout = new LinearLayout(mContext);
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		mainListView = new ListView(mContext);
		mainLayout.addView(mainListView,
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		return mainLayout;
	}

	public void setItem(Item item) {
		if (item instanceof ImageInfo) {
			ImageInfo imageInfo = (ImageInfo) item;
			View headView = View.inflate(mContext, R.layout.widget_item, null);
			assert (headView != null);
			// 展示图片
			ViewPager pager = (ViewPager) headView.findViewById(R.id.photos);
			View photoIndicator = headView
					.findViewById(R.id.multiple_photos_indicator);
			photoIndicator.setVisibility(View.VISIBLE);
			List<String> phList = new ArrayList<String>();
			phList.add(imageInfo.getIsrc());
			pager.setAdapter(new ItemAdapter(phList, getActivity()));
			
			photoIndicator.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//处理图片右下角的图片单击事件
				}
			});
			//展示文章內容
			

		}
	}
}
