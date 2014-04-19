/* Program 3, part III
 * Ashley Fish, KJ Jablonowski
 * CS 3010 TR 1PM, S14
 * Due: 4/18/2014
 * CLaiPlayer.java
 */

import java.util.ArrayList;

public class CLaiPlayer extends ConnectLPlayer
{
	//once initialized, the class cannot change who is min or max.
	static int whosMax, whosMin;

	public static void play(ConnectLGame game, int depth) 
	{
		
		//Determine who is min and who is max based on current gamestate
		if(game.getGameState() ==  ConnectLGame.GAME_STATE_RED_TURN) 
		{
			whosMax = ConnectLGame.MARK_RED;
			whosMin = ConnectLGame.MARK_BLACK;// Red is the max
		}
		
		if(game.getGameState() ==  ConnectLGame.GAME_STATE_BLACK_TURN) 
		{
			whosMax = ConnectLGame.MARK_BLACK;
			whosMin = ConnectLGame.MARK_RED;//  Black is the max
		}

		//variables needed for the class
		int c;
		ConnectLMove best = new ConnectLMove(-1,Integer.MIN_VALUE);
		
		ArrayList<ConnectLMove> mvs = game.getAllPossibleMoves();
		
		for (int i = 0; i < mvs.size(); i++) 
		{

			c = mvs.get(i).col;
			ConnectLGame gcopy = new ConnectLGame(game);
			gcopy.placeChecker(c);  // make the move

			int score = minimax(gcopy, depth);
			if (score > best.value) 
			{
				best.value = score;
				best.col = c;
	
			}
		}

		game.placeChecker(best.col);
	}

	//minimax borrowed from code provided by instructor
	public static int minimax(ConnectLGame game, int depth) 
	{

		if ( game.getGameState() == ConnectLGame.GAME_STATE_BLACK_WON ||
				game.getGameState() == ConnectLGame.GAME_STATE_RED_WON ||
				game.getGameState() == ConnectLGame.GAME_STATE_TIE ||
				depth == 0 )
		{
			int val = game.staticEvaluation(whosMax, whosMin,depth+1);
			return val;
		}
	
		ArrayList<ConnectLMove> mvs = game.getAllPossibleMoves();

		int c;
		int score;

		for (int i = 0; i < mvs.size(); i++) 
		{
		
			c = mvs.get(i).col;
			ConnectLGame gcopy = new ConnectLGame(game);

			gcopy.placeChecker(c);  // make the move

			score = minimax( gcopy, depth-1);

			mvs.get(i).value = score;

		}
		
		int mmPos = 0;
		if (game.getGameState() != whosMax) { 
			//	find min
			for (int i=1; i < mvs.size(); i++) 
				if ( mvs.get(i).value < mvs.get(mmPos).value) mmPos = i;
		}
		else { 
			// find max
			for (int i=1; i < mvs.size(); i++) 
				if ( mvs.get(i).value > mvs.get(mmPos).value) mmPos = i;
		}
		return mvs.get(mmPos).value;
	}
}
