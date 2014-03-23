package modles;

import java.util.List;

public class TuitangJson
{
	Data	data;
	boolean	success;
	
	public class Data
	{
		private List<ImageInfo>	blogs;
		private ImageInfo		first_blog;
		
		public List<ImageInfo> getBlogs()
		{
			return blogs;
		}
		
		public void setBlogs(List<ImageInfo> blogs)
		{
			this.blogs = blogs;
		}
		
		public ImageInfo getFirst_blog()
		{
			return first_blog;
		}
		
		public void setFirst_blog(ImageInfo first_blog)
		{
			this.first_blog = first_blog;
		}
		
	}
	
	public Data getData()
	{
		return data;
	}
	
	public void setData(Data data)
	{
		this.data = data;
	}
	
	public boolean isSuccess()
	{
		return success;
	}
	
	public void setSuccess(boolean success)
	{
		this.success = success;
	}
	
}
