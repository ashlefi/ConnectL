/* Program 3, part III
 * Ashley Fish, KJ Jablonowski
 * CS 3010 TR 1PM, S14
 * Due: 4/18/2014
 * ConnectLGame.java
 */

import java.util.ArrayList;

public class ConnectLGame 
{
	// Constants that keep track of where the game is at
	public static  final int GAME_STATE_RED_TURN = 0;
	public static  final int GAME_STATE_BLACK_TURN = 1;
	public static  final int GAME_STATE_RED_WON =  2;
	public static  final int GAME_STATE_BLACK_WON =  3;
	public static  final int GAME_STATE_TIE =    4;

	public static  final int MARK_ERROR = -1;
	public static  final int MARK_NONE = 0;
	public static  final int MARK_RED =  1;
	public static  final int MARK_BLACK =  2;

	private int rows = 6;	//number of rows in the board
	private int columns = 8; //number of columns in the board
	private int [][] data = new int [rows][columns];  //ConnectL Board
	private int gameState;  //Keeps track of the different states of the game


	/*Constructor calls reset to reset 
	 * the board to a blank state.  */
	public ConnectLGame() 
	{
		reset (); // resets the gameboard
	}
	
	
	/*Copy constructor that allows
	 * the AI player to make decisions
	 * based on a copied version  of the
	 * gameboard.  
	 */
	public ConnectLGame(ConnectLGame copy) 
	{
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				data [x][y] = copy.data[x][y];
			}
		}
		gameState = copy.gameState;
	}

	/**
	 * board array is cleared
	 * State is set to X's turn
	 */
	public void reset () {
		for (int x = 0; x < rows; x++) 
		{
			for (int y = 0; y < columns; y++)
			{
				data [x][y] = MARK_NONE;
			}
		}
		gameState = GAME_STATE_RED_TURN;
	}

	/*gets the state of the game and returns
	 * one of the constants */
	public int	 getGameState(){
		return gameState;
	}

	/*Rows and Columns getters*/
	public int getRows()
	{
		return rows;
	}

	public int getColumns()
	{
		return columns;
	}

	/*Gets the Mark. Allows determination of what
	 * is in a given square in the game  board */
	public int getValueinLoc(int r, int c) {
		if ( r < 0 || r >= rows|| c < 0|| c >= columns) 
				return MARK_ERROR;

		return data [r][c];
	}

	/*Leaves a player identification mark in the 
	 * row and column given. If it is not a valid 
	 * move, nothing is marked. */
	public boolean placeChecker (int c) 
	{
		if(c < 0 || c > columns)
		{
			return false;
		}
		int ro = rows - 1; 
		while(ro >= 0)
		{
			if(getValueinLoc(ro,c) == ConnectLGame.MARK_NONE)
			{
				if ( data[ro][c] != MARK_NONE) 
				{
					return false;
				}
				
				if ( gameState == GAME_STATE_BLACK_TURN) 
				{
					data[ro][c] = MARK_BLACK;
					gameState = GAME_STATE_RED_TURN;
					updateGame();
					return true;
				}
				
				else if ( gameState == GAME_STATE_RED_TURN) 
				{
					data[ro][c] = MARK_RED;
					gameState = GAME_STATE_BLACK_TURN;
					updateGame();
					return true;
				}
			}
			ro--;
		}
		return false; //the move was not valid
	}


	/*Checks for an L that looks like this:
	 * 
	 *         XXX     or   XXX
	 *           X          X
	 *           
	 * Also updates the game state
	 */
	private boolean check3A1D(int player)
	{
		for(int i = 0; i < rows - 1; i++)
		{
			for(int j = 0; j < columns - 2; j++)
			{		
				if (data[i][j] == player && data[i][j+1] == player && data[i][j+2] == player) 
				{
					if(data[i+1][j+2] == player || data[i+1][j] == player)
					{
						if ( player == MARK_RED)
							gameState = GAME_STATE_RED_WON;
						else if ( player == MARK_BLACK)
							gameState =GAME_STATE_BLACK_WON;
						return true;
					}
				}
			}
		}
		return false;
	}

	/*Checks for an L that looks like this:
	 *  
	 *           X			X    
	 *         XXX     OR   XXX
	 *  
	 * Also updates the game state
	 */
	private boolean check3A1U(int player)
	{
		for(int i = 1; i < rows; i++)
		{
			for(int j = 0; j < columns - 2; j++)
			{		
				if (data[i][j] == player && data[i][j+1] == player && data[i][j+2] == player) 
				{
					if(data[i-1][j+2] == player || data[i-1][j] == player)
					{
						if ( player == MARK_RED)
							gameState = GAME_STATE_RED_WON;
						else if ( player == MARK_BLACK)
							gameState =GAME_STATE_BLACK_WON;
						return true;
					}
				}
			}
		}
		return false;
	}

	/*Checks for an L that looks like this:
	 * 
	 *    XX			XX
	 *    X		OR		 X
	 *    X				 X
	 *    
	 * Also updates the game state
	 */
	private boolean check2A3D(int player)
	{
		for(int i = 0; i < rows - 2; i++)
		{
			for(int j = 0; j < columns - 1; j++)
			{		
				if (data[i][j] == player && data[i][j+1] == player) 
				{
					if((data[i+1][j] == player && data[i+2][j] == player)
						|| (data[i+1][j+1] == player && data[i+2] [j+1] == player))
					{
						if ( player == MARK_RED)
							gameState = GAME_STATE_RED_WON;
						else if ( player == MARK_BLACK)
							gameState =GAME_STATE_BLACK_WON;
						return true;
					}
				}
			}
		}
		return false;
	}

	/*Checks for an L that looks like this:
	 * 
	 *    X			     X
	 *    X		OR		 X
	 *    XX		    XX
	 *    
	 * Also updates the game state
	 */
	private boolean check2A3U(int player)
	{
		for(int i = 2; i < rows; i++)
		{

			for(int j = 0; j < columns - 1; j++)
			{		
				if (data[i][j] == player && data[i][j+1] == player) 
				{
					if((data[i-1][j] == player && data[i-2][j] == player)
						|| (data[i-1][j+1] == player && data[i-2] [j+1] == player))
					{
						if ( player == MARK_RED)
							gameState = GAME_STATE_RED_WON;
						else if ( player == MARK_BLACK)
							gameState =GAME_STATE_BLACK_WON;
						return true;
					}
				}
			}
		}
		return false;
	}

	/*Checks for a winner or a tie. Updates
	 * the game state based on whether or not
	 * a player made a winning move or there are
	 * no possible moves left */


	private void updateGame()
	{
		int  count = 0;

		if(check3A1D(MARK_RED)) 
			return;
		if(check3A1U(MARK_RED))
			return;
		if(check2A3D(MARK_RED)) 
			return;
		if (check2A3U(MARK_RED)) 
			return;

		if(check3A1D(MARK_BLACK)) 
			return;
		if(check3A1U(MARK_BLACK))
			return;
		if(check2A3D(MARK_BLACK)) 
			return;
		if (check2A3U(MARK_BLACK)) 
			return;

		// check for a tie - if all cells are Marked.
		for (int i = 0; i < rows; i++)
		{
			for ( int j = 0; j < columns; j++ )
			{
				if ( data[i][j] == MARK_NONE)
					count++;	//if there's an available space, then not all are used
			}
		}
		if (count == 0) gameState = GAME_STATE_TIE;

		return;
	}

	//Returns all valid moves
	ArrayList<ConnectLMove> getAllPossibleMoves()
	{
		ArrayList<ConnectLMove> possiblemoves = new ArrayList<ConnectLMove>();

		for(int j = 0; j < columns; j++)
		{
			if(data[0][j] == MARK_NONE)
				possiblemoves.add(new ConnectLMove(j,0));
		}

		return possiblemoves;
	}
	
	public int staticEvaluation(int maxPlayer, int minPlayer, int depth) 
	{
		int score =  0;

		if (gameState == GAME_STATE_TIE)
			return 0;
		
		if (gameState == GAME_STATE_BLACK_WON)
		{
			if (maxPlayer == MARK_BLACK)
				return 1000;
			else 
				return -1000;
		}
		if (gameState == GAME_STATE_RED_WON)
			if (maxPlayer == MARK_RED)
				return 1000;
			else
				return -1000;

		if(gameState == GAME_STATE_RED_TURN)
		{
			if(maxPlayer == MARK_RED)
				score = evaluateMinis(maxPlayer, minPlayer);
			else
				score = score*-1;
		}
		if(gameState == GAME_STATE_BLACK_TURN)
		{
			if(maxPlayer == MARK_BLACK)
				score = evaluateMinis(maxPlayer, minPlayer);
			else
				score = score*-1;
		}
		return score;
	}

	
	public int evaluateMinis(int maxplayer, int minplayer)
	{
		ArrayList<Integer> slist = new ArrayList<Integer>();
		int s =0;
		int bestscore;
		
		int[][] temp = new int[2][3];
		for(int w = 0; w < rows - 2; w++)
		{
			for(int x = 0; x < columns - 3; x++)
			{
				for(int i = 0; i < 2; i++)
				{
					for(int j = 0; j < 3; j++)
					{
						temp[i][j] = data[i][j];
					}
				
				}
				if (temp[0][0] == maxplayer && temp[0][1] == maxplayer && temp[0][2] == maxplayer) s = 100;
				
				slist.add(s);
				s = 0;
			}
		}
		bestscore = search(slist);
		return bestscore; 
	}
	
	public int search(ArrayList<Integer> moveScores) {
		int holder = -1;
		
		for(int i = 0; i < moveScores.size(); i++)
			if (moveScores.get(i) > holder)
				holder = moveScores.get(i);
		
		return holder;
	}


}// end of class ConnectLGame
