/*
Team Project: Dot Game
Jaime Cisternas-Riquelme
Jamie Batchelor
Alex Lawrence
Due Date : 12/02/2019
Assignment: A10 "Team Project"
Course Description: Fundamental concepts of programming with Java as tool.
*/
package dotgameapp;
import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
// Draw the Game Board. Circles that represents dots. Rectangles that represents lines. And Squares to be claimed.
// Store parameters (position) of every rectangle.
// Enumerate every square and assign every rectangle surrounding it
// Activate the MouseListener.

/**
 * Starts the game.
 * @author Jaime
 *
 */
public class DotGameApp
{     	
    public static void main(String[] args) 
    {  
    	int[][] arrayForRectangles;
        int[][] arrayForSquares;

        StoreRectangleLocation setRectangles = new StoreRectangleLocation();
        setRectangles.storeLocation();
        arrayForRectangles = setRectangles.getArrayForRectangles();  
        
        SquareNum setSquares = new SquareNum(arrayForRectangles);
        setSquares.numSquares(); 
        setSquares.assignRectangles();  
        setSquares.assignSquaresToRectangles();
        arrayForSquares = setSquares.getArrayForSquares();
        
    	DotGameFrame miMarco = new DotGameFrame(arrayForRectangles, arrayForSquares);
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
/**
 * Controls the game
 * @author Jaime
 *
 */
class DotGameFrame extends JFrame implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	private int x = 0;
	private int y = 0;
	private int w = 0;
	private int h = 0;
	private int xS1;
    private int yS1;
    private int xS2;
	private int yS2; 
	private static String nextStep = "Draw the GameBoard";
	private int[][] arrayForRectangles;
	private int[][] arrayForSquares;
	private int index = 0;
	private Color color;
	public static int player = 1;
	public static int squaresPlayer1 = 0;
	public static int squaresPlayer2 = 0;
	public static int totalSquares = 0;
	
	public DotGameFrame() { }
	
	public DotGameFrame(int[][] arrayForRectangles, int [][]arrayForSquares) {
		this.arrayForRectangles = arrayForRectangles;
		this.arrayForSquares = arrayForSquares;
		
		setVisible(true);
		setTitle("DOT GAMEÍ");
		setBounds(400,100,700,800);
		setResizable(false);
		
		Container c = this.getContentPane();
		c.addMouseListener(this);
		c.addMouseMotionListener(this);	
	}
	/**
	 * Triggers the class that draws the game-board @see DrawGameBoard
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (nextStep.equals("Draw the GameBoard")){
			// Does this just the first time the mouse is moved inside the game-board.
			DrawGameBoard drawGameBoard = new DrawGameBoard();
			drawGameBoard.paint(super.getGraphics());
	        nextStep = "Continue Playing";
		} 
	}
	/**
	 * Checks if an unpainted rectangle is clicked.
	 * Activates the process to claim rectangles and squares.
	 * Triggers methods to paint rectangles and squares.
	 * Triggers the class Winner to show the score when the game is over. @see Winner
	 */
	//@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		RectangleFound rectFound = new RectangleFound(x,y,this.arrayForRectangles);
		index = rectFound.getIndexOfArray();
		
		if(index != 0) { // click was out of any Rectangle
			ClaimRectangleAndSquares claiming = new ClaimRectangleAndSquares(index,arrayForRectangles,arrayForSquares);
			claiming.claiming();
			
			xS1 = claiming.get_xS1();
			yS1 = claiming.get_yS1();
			xS2 = claiming.get_xS2();
			yS2 = claiming.get_yS2();
			
			if(claiming.getPaintRect()) {
				paintRectanguloClaimed();
			}
			if(claiming.getPaintSQ1()) {
				paintSQ1Claimed();
			}
			if(claiming.getPaintSQ2()) {
				paintSQ2Claimed();
			}
			if(claiming.getGameIsOver()) {
				Winner winner = new  Winner(DotGameFrame.squaresPlayer1,DotGameFrame.squaresPlayer2);
				winner.theWinner();
			}
		}
	}
	/**
	 * Triggers the method to paints Rectangle claimed by a player.
	 */
	public void paintRectanguloClaimed() {
		
		x = this.arrayForRectangles[index][0];
		y = this.arrayForRectangles[index][2];
		w = this.arrayForRectangles[index][4];
		h = this.arrayForRectangles[index][5];

		if (this.arrayForRectangles[index][8] == 1) {
			color = Color.YELLOW;
		}else {
			color = Color.GREEN;
		}
		RectangleFiller paintReactangle = new RectangleFiller(color,x,y,w,h);
		paintReactangle.paint(super.getGraphics());
	}
	/**
	 * Triggers the method to paint a square claimed by a player.
	 */
	public void paintSQ1Claimed() {
		if (player == 1) {
			color = Color.YELLOW;
		}else {
			color = Color.GREEN;
		}
		SquareFiller paintFirstSqrAdjacent = new SquareFiller(color,xS1,yS1);
		paintFirstSqrAdjacent.paint(super.getGraphics());
	}
	/**
	 * Triggers the method to paint a second adjacent square claimed by a player.
	 */
	public void paintSQ2Claimed() {

		if (player == 1) {
			color = Color.YELLOW;
		}else {
			color = Color.GREEN;
		}
		SquareFiller paintSecondSqrAdjacent = new SquareFiller(color,xS2,yS2);
		paintSecondSqrAdjacent.paint(super.getGraphics());
	}
	public int getTotalSquares() {
		return totalSquares;
	}
	
  
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}	
}

//  -------  End class DotGameFrame   -------

/**
 * Paints a rectangle with the color corresponding to the player that claims the rectangle.
 * 
 */
class RectangleFiller{
	private int x;
	private int y;
	private int w;
	private int h;
	private Color color;
/**
 * 
 * @param color Color assigned to the current player who selected the rectangle.
 * @param x Coordinate x in the game board of the rectangle.
 * @param y Coordinate y in the game board of the rectangle.
 * @param w Width of the rectangle claimed.
 * @param h Height of the rectangle claimed.
 */
	public RectangleFiller(Color color, int x, int y, int w, int h) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void paint(Graphics g) {
	
		g.setColor(color);
		g.fillRect(this.x, this.y, this.w, this.h);	
	}	
}
/**
 * Paints a square with the color corresponding to the player that claims the square.
 * 
 */

class SquareFiller{
	private int x;
	private int y;
	private Color color;
	/**
	 * 
	 * @param color Color assigned to the current player who claimed the square.
	 * @param x Coordinate x in the game board-game for the square.
	 * @param y Coordinate y in the game board-game for the square.
	 */
	public SquareFiller(Color color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g) {
	
		g.setColor(color);
		g.fillRect(this.x, this.y, 39, 39);
	}
}

