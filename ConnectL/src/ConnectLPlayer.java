import java.awt.Color;


abstract class ConnectLPlayer 
{
	private String name;
	private Color color;
	
	public ConnectLPlayer()
	{
		name = null;
		color = null;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setColor(Color c)
	{
		color = c;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Color getColor()
	{
		return color;
	}
}
