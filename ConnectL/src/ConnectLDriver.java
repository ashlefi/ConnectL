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
		
		//While neither player has won and there is no tie
		while(a.getGameState() != 2 && a.getGameState() != 3 && a.getGameState() !=4)
		{
			System.out.println("It is " + Player1 + "'s turn!");
			
			int uc = -1; //variable for the user column input
			
			System.out.println("These are the available colums for checker placement: "
					+ a.getAllPossibleMoves());
			System.out.println("In which column would you like to place the checker? 0-7");
			uc = r.nextInt();
			
			for(int i = row; i >= 0; i--)
			{
				System.out.println(a.getValueinLoc(i, uc));
				if(a.getValueinLoc(i,uc) == 0)
				{
					a.placeChecker(i, uc);
					break;
				}
			}
			
			if(a.getGameState() != 2 && a.getGameState() !=4) //check if player1 has won before player 2 gets to move.
			{
				System.out.println("It is " + Player2 +"'s turn!");
				 
				System.out.println("In which column would you like to place the checker? 0-7");
				uc = r.nextInt();
				
				for(int i = row; i >= 0; i--)
				{
					if(a.getValueinLoc(i,uc) == 0)
					{
						a.placeChecker(i, uc);
						break;
					}
				}
			}
		
		} //End of while loop, winner or tie
		
		//Print winner or tie status at end of game
		if(a.getGameState() == 2)
			System.out.println(Player1 + " wins!");
		else if (a.getGameState() == 3)
			System.out.println(Player2 + " wins!");
		else
			System.out.println("The game is a tie. You both lose.");
				
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

	

}