package ui;

import modles.Item;

import com.loch.yh_android.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ItemActivity extends FragmentActivity
{

	@Override
	protected void onCreate(Bundle arg0)
	{
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_item);
		Item item  = (Item) getIntent().getSerializableExtra("item");
		assert (item != null);
		
		super.onCreate(arg0);
	}
	
}
