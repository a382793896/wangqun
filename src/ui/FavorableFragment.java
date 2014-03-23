package ui;

import com.loch.yh_android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FavorableFragment extends Fragment
{
	View mainView;
	ListView mainListView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		mainView = inflater.inflate(R.layout.favorable_fragment, null);
		mainListView = (ListView) mainView.findViewById(R.id.listView);
		return mainView;
	}
	
}
