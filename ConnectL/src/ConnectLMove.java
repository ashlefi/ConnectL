/* Program 3, part III
 * Ashley Fish, KJ Jablonowski
 * CS 3010 TR 1PM, S14
 * Due: 4/18/2014
 * ConnectLMove.java
 */

public class ConnectLMove 
{
	//Describes the column and minimax value of a move
	public int col;
	public int value;

	public ConnectLMove() 
	{
		col = 0;
		value = 0;
	}
	public ConnectLMove( int COL, int VALUE)
	{
		col = COL;
		value = VALUE;
	}

}
