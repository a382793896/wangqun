package Adapters;



import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class AdapterWrapper extends BaseAdapter
{
	protected ListAdapter _wrapped = null;
	
	
	public AdapterWrapper(ListAdapter _wrapped)
	{
		super();
		this._wrapped = _wrapped;
		_wrapped.registerDataSetObserver(new DataSetObserver()
			{

				@Override
				public void onChanged()
				{
					// TODO Auto-generated method stub
					notifyDataSetChanged();
				}

				@Override
				public void onInvalidated()
				{
					// TODO Auto-generated method stub
					notifyDataSetInvalidated();
				}
			
			});
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		
		return _wrapped.getCount();
	}
	
	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return _wrapped.getItem(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return _wrapped.getItemId(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		return _wrapped.getView(position, convertView, parent);
	}
	public ListAdapter getwrapper()
	{
		return _wrapped;
	}
}
