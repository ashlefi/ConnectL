/* Program 3, part II
 * Ashley Fish, KJ Jablonowski
 * CS 3010 TR 1PM, S14
 * Due: 4/2/2014
 * ConnectLModel.java
 */

/* Program 3, part II
 * Ashley Fish, KJ Jablonowski
 * CS 3010 TR 1PM, S14
 * Due: 4/2/2014
 * ConnectLModel.java
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class ConnectLModel extends JApplet implements MouseListener
{

	/**
	 * 
	 */
	//Global variables
	private static final long serialVersionUID = 1L;
	Canvas canvas;
	JTextField gameStatus;
	JLabel gameLabel = new JLabel("Let the game begin!");
	ConnectLGame game = new ConnectLGame();
	
	int rows = game.getRows();
	int cols = game.getColumns(); // number of rows and columns for the board
	int w, h;
	
	CLhumanPlayer p1 = new CLhumanPlayer();
	CLhumanPlayer p2 = new CLhumanPlayer();

	private class Canvas extends JPanel 
	{
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);

			w = getWidth();
			h = getHeight();

			g.setColor( Color.BLUE);
			g.drawLine(10,10,200,200);

			int sqWid = w/cols;
			int sqHgt = h/rows;

			//draws boards
			for (int i=0; i < rows; i++) 
			{
				for (int j = 0; j < cols; j++) 
				{
					if ( (i+j)%2 == 0)  g.setColor(Color.blue);
					else g.setColor(Color.red);
					g.fillRect(j*sqWid, i*sqHgt, sqWid, sqHgt);

				}
			}

			//draws player pieces, when applicable
			for (int r = 0; r < 6; r++) {
				for ( int c = 0; c < 8; c++) {
					if ( game.getValueinLoc(r, c) == ConnectLGame.MARK_BLACK) {
						g.setColor(p2.getColor());
						g.fillOval(c*w/8, r*h/6, w/8, h/6);
					}
					if (game.getValueinLoc(r, c) == ConnectLGame.MARK_RED) {
						g.setColor(p1.getColor());
						g.fillOval(c*w/8, r*h/6, w/8, h/6);
					}
				}
			}
		}
	} // end Canvas class

	private class uiHandler implements ActionListener 
	{ // The event listener.
		public void actionPerformed(ActionEvent e) 
		{
			String aC = e.getActionCommand();
			//resets the board
			if (aC.equals("New Game")) 
			{
				game = new ConnectLGame();
				p1 = new CLhumanPlayer();
				p2 = new CLhumanPlayer();
				canvas.repaint();
				gameLabel.setText("Let the game begin!");
				setPlayerNames();
				setPlayerColors();
				updateStatus();
			}
			// invoke repaint command here
		}
	} // end uiHandler class

	public void init()
	{ 
		game = new ConnectLGame();
		createComponents();
		setPlayerNames();
		setPlayerColors();
		updateStatus();
	}
	
	//updates the game status for the board based on the game state
	void updateStatus()
	{
		String status;
		if ( game.getGameState() == ConnectLGame.GAME_STATE_BLACK_TURN)
			status = p2.getName() + "'s Turn!";
		else if (game.getGameState() == ConnectLGame.GAME_STATE_RED_TURN)
			status = p1.getName() + "'s Turn";
		else if (game.getGameState() == ConnectLGame.GAME_STATE_BLACK_WON )
			status = p2.getName() + " is the winner!";
		else if (game.getGameState() == ConnectLGame.GAME_STATE_RED_WON )
			status = p1.getName() + " is the winner!"; 
		else{
			status = "Tie Game";
			//gameLabel.setText("You both lose.");
		}
		gameStatus.setText(status);
	}

	public void createComponents() 
	{
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		this.setSize(800,600);
		JPanel uip = new JPanel();
		uip.setLayout(new FlowLayout()); 
		gameStatus  = new JTextField(12);

		JButton b1 = new JButton("New Game");
		uip.add(gameLabel);
		uip.add( new JLabel("status"));
		uip.add(gameStatus);
		uip.add(b1);

		b1.addActionListener( new uiHandler());

		canvas = new Canvas();
		canvas.setBackground(new Color(210, 180, 140));
		canvas.addMouseListener(this);
		content.add(canvas, BorderLayout.CENTER);
		content.add(uip, BorderLayout.SOUTH);
		setContentPane(content);
	}

	//Checks if a move is valid and places a piece if so
	public void mouseClicked(MouseEvent arg0)
	{
		//Error message for clicking after game is over
		if(game.getGameState() == ConnectLGame.GAME_STATE_BLACK_WON || game.getGameState() == ConnectLGame.GAME_STATE_RED_WON || game.getGameState() == ConnectLGame.GAME_STATE_TIE){
			gameLabel.setText("Game is over. Please click 'New Game'.");
		}
		else{
			int x = arg0.getX();

			int col = (int)(x / (w/8));
			int row = -1;
	
			//Checks for next valid spot in column
			//Remains -1 (invalid) if no valid spot is found
			for (int i = 0; i < game.getRows(); i++){
				if(game.getValueinLoc(i, col) == 0)
					row = i;
			}
			
			if (game.placeChecker(row, col)){
				updateStatus();
				canvas.repaint();
				
				//Code for snarky gameLabels
				Random rand = new Random();
				int goof = rand.nextInt(100) + 1;
				if (goof % 7 == 0)
					gameLabel.setText("Are you SURE you want to put that there?");
				else if (goof % 13 == 0)
					gameLabel.setText("*yawn* Are we done yet...?");
				else if (goof % 15 == 0)
					gameLabel.setText("Gegen Dummheit gibt es keine Pillen."); //"No pills can cure stupidity."
				else if (goof % 6 == 0)
					gameLabel.setText("Spiel im Gange."); //"Game in progress."
				else
					gameLabel.setText("Game in progress.");
			}
			//If row remains -1, error message given for invalid move
			else
			{
				gameLabel.setText("Invalid move. Please try another column.");
			}
		}
	}	


	public void mouseEntered(MouseEvent arg0) 
	{}


	public void mouseExited(MouseEvent arg0) 
	{}

	public void mousePressed(MouseEvent arg0) 
	{}


	public void mouseReleased(MouseEvent arg0) 
	{}
	
	//Function to pull up color setter dialog window
	public void setPlayerColors(){
		while (p1.getColor() == null) //Prevents player from cancelling out
			p1.setColor(JColorChooser.showDialog(ConnectLModel.this, "Player 1 Color Choice", p1.getColor()));
		while (p2.getColor() == null) //Prevents player from cancelling out
			p2.setColor(JColorChooser.showDialog(ConnectLModel.this, "Player 2 Color Choice", p2.getColor()));
		while ((p1.getColor()).equals(p2.getColor()) || p2.Color()) //Disallows the same colors for players
			p2.setColor(JColorChooser.showDialog(ConnectLModel.this, "Player 1 has already chosen that color. Please select another.", p2.getColor()));
	}
	
	//Allows players to choose names using a dialog window
	public void setPlayerNames(){
		while (p1.getName() == null || (p1.getName()).equals(""))
			p1.setName((String)JOptionPane.showInputDialog(ConnectLModel.this, "Please enter Player 1's name:", "Player 1"));
		while (p2.getName() == null || (p2.getName()).equals(""))
			p2.setName((String)JOptionPane.showInputDialog(ConnectLModel.this, "Please enter Player 2's name:", "Player 2"));
	}
}