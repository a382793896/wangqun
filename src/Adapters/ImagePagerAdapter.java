package Adapters;

import com.android.volley.toolbox.NetworkImageView;

import myLibs.ImageCacheManager;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public abstract class ImagePagerAdapter extends PagerAdapter
{
	public abstract int getRealSize();
	
	public abstract String getImageUrl(int postion);
	
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		if (getRealSize() == 0)
			return 1;
		return getRealSize();
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1)
	{
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		// 获取网络图片
		Context context = container.getContext();
		ImageView iView = null;
		if (position < getRealSize())// 虽然不会超过最后一张图片，但是为了宝箱期间，还是加一个判断好一点，意思不大。
		{
			// 一部加载网络图片
			NetworkImageView netImage = new NetworkImageView(context);
			netImage.setImageUrl(getImageUrl(position), ImageCacheManager
					.getInstance().getImageLoader());
			 iView = netImage;
		}
		container.addView(iView);
		// TODO Auto-generated method stub
		return iView;
	}
	
}
