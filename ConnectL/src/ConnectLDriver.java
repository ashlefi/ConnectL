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
		
		//Loop that manages player turns
		while(a.getGameState() != 2 || a.getGameState() != 3 || a.getGameState() != 4)
		{
			handleTurn(Player1);
			if(a.getGameState() != 1 || a.getGameState() != 4)
				handleTurn(Player2);
		}
		
	}
	
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
