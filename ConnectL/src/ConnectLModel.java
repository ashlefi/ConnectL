import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class ConnectLModel extends JApplet implements MouseListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Canvas canvas;
	JTextField gameStatus;
	JLabel gameLabel = new JLabel("Let the game begin!", SwingConstants.LEFT);
	ConnectLGame game;
	int rows = 6; //game.getRows();
	int cols = 8; //game.getColumns(); // number of rows and columns for the board
	int w, h;
	Color playerOneColor = Color.WHITE, playerTwoColor = Color.BLACK; //Set default colors for player pieces

	private class Canvas extends JPanel 
	{

		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);

			w = getWidth();
			h = getHeight();

			g.setColor( Color.BLUE);
			g.drawLine(10,10,200,200);

			int sqWid = w/cols;
			int sqHgt = h/rows;

			for (int i=0; i < rows; i++) 
			{
				for (int j = 0; j < cols; j++) 
				{
					if ( (i+j)%2 == 0)  g.setColor(Color.blue);
					else g.setColor(Color.red);
					g.fillRect(j*sqWid, i*sqHgt, sqWid, sqHgt);

				}
			}

			for (int r = 0; r < 6; r++) {
				for ( int c = 0; c < 8; c++) {
					if ( game.getValueinLoc(r, c) == ConnectLGame.MARK_BLACK) {
						g.setColor(playerTwoColor);
						g.fillOval(c*w/8, r*h/6, w/8, h/6);
					}
					if (game.getValueinLoc(r, c) == ConnectLGame.MARK_RED) {
						g.setColor(playerOneColor);
						g.fillOval(c*w/8, r*h/6, w/8, h/6);
					}
				}
			}
		}
	} // end Canvas class

	private class uiHandler implements ActionListener 
	{ // The event// listener.
		public void actionPerformed(ActionEvent e) 
		{
			String aC = e.getActionCommand();
			if (aC.equals("New Game")) 
			{
				game = new ConnectLGame();
				updateStatus();
				canvas.repaint();
				gameLabel.setText("Let the game begin!");
				setPlayerColors();
			}
			// invoke repaint command here
		}
	} // end uiHandler class

	public void init()
	{ 
		game = new ConnectLGame();
		createComponents();
		updateStatus();
		setPlayerColors();
	}
	void updateStatus()
	{
		String status;
		if ( game.getGameState() == ConnectLGame.GAME_STATE_BLACK_TURN)
			status = "O's Turn!";
		else if (game.getGameState() == ConnectLGame.GAME_STATE_RED_TURN)
			status = " X's Turn";
		else if (game.getGameState() == ConnectLGame.GAME_STATE_BLACK_WON )
			status = "O is the winner";
		else if (game.getGameState() == ConnectLGame.GAME_STATE_RED_WON )
			status = "X is the winner"; 
		else
			status = "Tie Game";
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


	public void mouseClicked(MouseEvent arg0)
	{
		if(game.getGameState() == ConnectLGame.GAME_STATE_BLACK_WON || game.getGameState() == ConnectLGame.GAME_STATE_RED_WON || game.getGameState() == ConnectLGame.GAME_STATE_TIE){
			gameLabel.setText("Game is over. Please click 'New Game'.");
		}
		else{
			int x = arg0.getX();

			int col = (int)(x / (w/8));
			int row = -1;
	
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
				else if (goof % 14 == 0)
					gameLabel.setText("Gegen Dummheit gibt es keine Pillen."); //"No pills can cure stupidity."
				else if (goof % 6 == 0)
					gameLabel.setText("Spiel im Gange."); //"Game in progress."
				else
					gameLabel.setText("Game in progress.");
			}
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
	
	public void setPlayerColors(){
		playerOneColor = JColorChooser.showDialog(model.this, "Player 1 Color Choice", playerOneColor);
		while (playerOneColor == null) //Prevents player from cancelling out
			playerOneColor = JColorChooser.showDialog(model.this, "Player 1 Color Choice", playerOneColor);
		playerTwoColor = JColorChooser.showDialog(model.this, "Player 2 Color Choice", playerTwoColor);
		while (playerTwoColor == null) //Prevents player from cancelling out
			playerTwoColor = JColorChooser.showDialog(model.this, "Player 2 Color Choice", playerTwoColor);
		while (playerOneColor.equals(playerTwoColor)) //Disallows the same colors for players
			playerTwoColor = JColorChooser.showDialog(model.this, "Player 1 has already chosen that color. Please select another.", playerTwoColor);
	}
}