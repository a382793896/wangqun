package Adapters;

import java.util.List;

import com.android.volley.toolbox.NetworkImageView;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class ItemAdapter extends ImagePagerAdapter
{
	List<String> mPhotos;
	Activity mActivity;
	
	
	public ItemAdapter(List<String> mPhotos, Activity mActivity)
	{
		super();
		this.mPhotos = mPhotos;
		this.mActivity = mActivity;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		// TODO Auto-generated method stub
		View view =  (View) super.instantiateItem(container, position);
		if(view instanceof NetworkImageView)
		{
			NetworkImageView imageView = (NetworkImageView) view;
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//处理图片单击的事件
				}
			});
		}
		return view;
	}

	@Override
	public int getRealSize()
	{
		// TODO Auto-generated method stub
		return (mPhotos == null ? 0:mPhotos.size());
	}
	
	@Override
	public String getImageUrl(int postion)
	{
		// TODO Auto-generated method stub
		return mPhotos.get(postion);
	}
	
}
