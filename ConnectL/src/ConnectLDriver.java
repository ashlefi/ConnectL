/* Program 3, part II
 * Ashley Fish, KJ Jablonowski
 * CS 3010 TR 1PM, S14
 * Due: 4/2/2014
 * ConnectLDriver.java
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConnectLDriver {

	private static ConnectLGame a;
	private static Scanner r = new Scanner(System.in);

	public static void main(String[] args)
	{
		String Player1 = getPlayerName();
		String Player2 = getPlayerName();

		a = new ConnectLGame();
		int row = a.getRows() -1;
		int col = a.getColumns() -1;
		boolean validMove = true;
		int uc = -1;

		//draws the initial game board
		drawGame(row, col);

		//While neither player has won and there is no tie
		while(a.getGameState() != a.GAME_STATE_RED_WON && a.getGameState() != a.GAME_STATE_BLACK_WON && a.getGameState() != a.GAME_STATE_TIE)
		{
			uc = -1;
			System.out.println("It is " + Player1 + "'s turn!");
			
			System.out.println("These are the available colums for checker placement: "
					+ a.getAllPossibleMoves());
			
			uc = enterInt();

			System.out.println("These are the available colums for checker placement: "
					+ a.getAllPossibleMoves());

			uc = enterInt();

			//checking for a valid move
			if(a.getValueinLoc(0, uc) != a.MARK_NONE)
				validMove = false;
			while (!validMove)
			{
				System.out.println("That move is not valid.");
				System.out.println("These are the available colums for checker placement: "
						+ a.getAllPossibleMoves());

				uc = enterInt();

				if(a.getValueinLoc(0, uc) == a.MARK_NONE)
					validMove = true;
			}

			for(int i = row; i >= 0; i--)
			{
				System.out.println(a.getValueinLoc(i, uc));
				if(a.getValueinLoc(i,uc) == a.MARK_NONE)
				{
					a.placeChecker(i, uc);
					drawGame(row, col);
					break;
				}
			}

			if(a.getGameState() != a.GAME_STATE_RED_WON && a.getGameState() != a.GAME_STATE_TIE) //check if player1 has won before player 2 gets to move.
			{
				System.out.println("It is " + Player2 +"'s turn!");
				 
				uc = enterInt();
				
				//checking for a valid move
				if(a.getValueinLoc(0, uc) != a.MARK_NONE)
					validMove = false;
				while (!validMove){
					System.out.println("That move is not valid.");
					System.out.println("These are the available colums for checker placement: "
							+ a.getAllPossibleMoves());
	
					uc = enterInt();

					if(a.getValueinLoc(0, uc) == a.MARK_NONE)
						validMove = true;
				}

				for(int i = row; i >= 0; i--)
				{
					if(a.getValueinLoc(i,uc) == a.MARK_NONE)
					{
						a.placeChecker(i, uc);
						drawGame(row, col);
						break;
					}
				}
			}

		} //End of while loop, winner or tie

		//Print winner or tie status at end of game
		if(a.getGameState() == a.GAME_STATE_RED_WON)
			System.out.println(Player1 + " wins!");
		else if (a.getGameState() == a.GAME_STATE_BLACK_WON)
			System.out.println(Player2 + " wins!");
		else
			System.out.println("The game is a tie. You both lose.");

		r.close();

	} // end of main

	/*Allows players to enter a customized name. No Error Checking*/
	public static String getPlayerName()
	{
		String playername;
		System.out.println("Please enter your name");
		playername = r.next();
		return playername;
	}

	public static void drawGame(int r, int c)
	{
		for(int i = 0; i <= r; i++)
		{
			System.out.println("----------------------------------------");
			for(int j = 0; j <= c; j++)
			{
				if (a.getValueinLoc(i,j) == a.MARK_RED)
					System.out.print("| R |");
				if(a.getValueinLoc(i,j) == a.MARK_BLACK)
					System.out.print("| B |");
				if(a.getValueinLoc(i,j) == a.game.MARK_NONE)
					System.out.print("|   |");
			}
			System.out.println(" ");
		}
		System.out.println("----------------------------------------");
	}
	public static int enterInt()
	{
		boolean enterint = false;
		int myint = -1;
		while(!enterint)
		{
			try
			{
				System.out.println("In which column would you like to place the checker? 0-7");
				myint = r.nextInt();
				enterint = true;
			}
			catch(InputMismatchException e)
			{
				enterint = false;
				r.nextLine();
			}
		}
		enterint = false;

		return myint;
	}

}