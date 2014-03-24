import java.util.Scanner;

public class ConnectLDriver {

	public static void main(String[] args)
	{
		String Player1 = getPlayerName();
		String Player2 = getPlayerName();
		
		ConnectLGame a = new ConnectLGame();
		int row = a.getRows() - 1;
		int col = a.getColumns() - 1;
		Scanner r = new Scanner(System.in);
		
		while(a.getGameState() != 2 || a.getGameState() != 3 || a.getGameState() !=4)
		{
			System.out.println("It is " + Player1 + "'s turn!");
			
			int uc = -1; //variable for the user column input
			
			System.out.println("These are the available colums for checker placement: "
					+ a.getAllPossibleMoves());
			System.out.println("In which column would you like to place the checker? 0-7");
			uc = r.nextInt();
			
			for(int i = row; i >= 0; i--)
			{
				
				a.placeChecker(i, uc);
			}
			
			if(a.getGameState() != 2 || a.getGameState() !=4) //check if player1 has won before player 2 gets to move.
			{
				System.out.println("It is " + Player2 +"'s turn!");
				 
				System.out.println("In which column would you like to place the checker? 0-7");
				uc = r.nextInt();
				
				for(int i = row; i >= 0; i--)
				{
					a.placeChecker(i, uc);
				}
			}
		
		}
		r.close();
		
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
}// end of class ConnectLDriver