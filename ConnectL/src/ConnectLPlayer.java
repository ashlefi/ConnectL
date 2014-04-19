/* Program 3, part III
 * Ashley Fish, KJ Jablonowski
 * CS 3010 TR 1PM, S14
 * Due: 4/18/2014
 * ConnectLPlayer.java
 */

import java.awt.Color;

//Abstract class provides means for accessing the name and checker color for a player
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
