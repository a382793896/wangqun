package ui;

import java.io.InputStream;

import com.loch.yh_android.R;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class StartActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	       ImageView image;
	        image = new ImageView(this);
	        BitmapFactory.Options opt = new BitmapFactory.Options();
	        opt.inPreferredConfig = Bitmap.Config.RGB_565;  
	        opt.inPurgeable = true;
	        opt.inInputShareable = true;
	        //获取资源图片
	        InputStream is = getResources().openRawResource(R.drawable.qidongtu);
	        // BitmapFactory.decodeStream(is,null,opt);
	        image.setImageBitmap(BitmapFactory.decodeStream(is,null,opt));
	        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
	        setContentView(image);
	        new synTask().execute();
	}
	
	private void StartMainActivity()
	{
		Intent intent = new Intent(StartActivity.this,MainActivity.class);
		startActivity(intent);
		finish();
	}
	public class synTask extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... params)
		{
			// TODO Auto-generated method stub
			//初始化
			
			return null;
		}

		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			
			
		}

		@Override
		protected void onPostExecute(Void result)
		{
			//进入主要Activity
			StartMainActivity();
		
		}
		
	}
}
