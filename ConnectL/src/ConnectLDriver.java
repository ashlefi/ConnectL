import java.util.ArrayList;
import java.util.Scanner;

public class ConnectLDriver {

	public static void main(String[] args)
	{
		String Player1 = getPlayerName();
		String Player2 = getPlayerName();
		
		ConnectLGame a = new ConnectLGame();
		int row = a.getRows();
		int col = a.getColumns();
		
		
		int c = -1;
		int r = -1;
		
		Scanner r = new Scanner();
		System.out.println("In which column would you like to place the checker? 0-" + col -1);
		r.nextInt();
		for(int i = row - 1; i >= 0; i--)
		{
			if(a.getValinLoc(i, c) == 0)
				a.placeChecker(i, c);
				
		}
		
	} // end of main
	
	/*Allows players to enter a customized name. No Error Checking*/
	public static String getPlayerName()
	{
		String playername;
		Scanner s = new Scanner(System.in);
	
		System.out.println("Please enter your name");
		playername = s.next();
		
		return playername;
	}
	
	/*Draws a visual representation of the gameboard*/
	public static void drawGame(int r, int c)
	{
		for(int i = 0; i < r; i++)
		{
			
		}
	}
	
	

}
;
