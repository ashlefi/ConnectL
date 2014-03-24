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
		drawGame(rows, columns);
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
		if ( r < 0 || r > rows|| c < columns|| c > columns) 
				return MARK_ERROR;

		return data [r][c];
	}
	
	/*Leaves an player identification mark in the 
	 * row and column given. If it is not a valid 
	 * move, nothing is marked. */
	public boolean placeChecker (int r, int c) {
		if ( r < 0 || r > rows || c < 0 || c > columns) 
			return false;
		if ( data[r][c] != MARK_NONE) 
			return false;

		if ( gameState == GAME_STATE_BLACK_TURN) 
		{
			data[r][c] = MARK_BLACK;
			gameState = GAME_STATE_RED_TURN;
		}
		else if ( gameState == GAME_STATE_RED_TURN) 
		{
			data[r][c] = MARK_RED;
			gameState = GAME_STATE_BLACK_TURN;
		}
		
		System.out.println("Bitch nigga");
		drawGame(rows, columns);
		updateGame();// winner?, tie?
		return true;  // move was successfully made
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
					if(data[i-1][j+2] == player || data[i+1][j] == player)
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
						|| (data[i-11][j+1] == player && data[i-2] [j+1] == player))
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

	public ArrayList<Integer> getAllPossibleMoves()
	{
		ArrayList<Integer> possiblemoves = new ArrayList<Integer>();
		
		for(int j = 0; j < columns; j++)
		{
			if(data[0][j] == MARK_NONE)
				possiblemoves.add(j);
		}
		
		return possiblemoves;
	}
	
	private void drawGame(int r, int c)
	{
		for(int i = 0; i < r; i++)
		{
			System.out.println("----------------------------------------");
			for(int j = 0; j < c; j++)
			{
				if(data[i][j] == MARK_RED)
					System.out.print("| R |");
				if(data[i][j] == MARK_BLACK)
					System.out.print("| B |");
				if(data[i][j] == MARK_NONE)
					System.out.print("|   |");
			}
			System.out.println(" ");
		}
		System.out.println("----------------------------------------");
	}
	
}// end of class ConnectLGame
	