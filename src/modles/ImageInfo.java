package modles;

import java.io.Serializable;



public class ImageInfo extends Item implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1555788190306873879L;

	public ImageInfo(String srcString, String titile, int height)
	{
		super();
		this.isrc = srcString;
		this.msg = titile;
		this.iht = height;
	}
	public ImageInfo()
	{
		// TODO Auto-generated constructor stub
	}
	private String isrc;
	private String msg;
	private int      iht;
	
	public String getIsrc()
	{
		return isrc;
	}
	public void setIsrc(String isrc)
	{
		this.isrc = isrc;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public int getIht()
	{
		return iht;
	}
	public void setIht(int iht)
	{
		this.iht = iht;
	}
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "titile:"+msg + "srcString:"+  isrc+"height:"+iht;
	}
	
	
}
