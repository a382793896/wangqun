package ui;

import com.loch.yh_android.R;



import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity
{
	public static final String TAb_INDEX = "Tab_Index";//Tab选项参数Key

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Tab_Fragment tab_Fragment =  (Tab_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment_tab);
		tab_Fragment.selectTab(0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
	
		
		return true;
	}
	
	@Override
	protected void onNewIntent(Intent intent)
	{
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		int nIndex = intent.getIntExtra(MainActivity.TAb_INDEX, 0);
		Tab_Fragment tab_Fragment =  (Tab_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment_tab);
		tab_Fragment.selectTab(nIndex);
	}
	
}
