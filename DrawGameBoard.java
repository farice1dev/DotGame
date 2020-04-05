package dotgameapp;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Draws the game-board. Draws circles, horizontal and vertical rectangles, and squares.
 * @author Jaime
 *
 */
public class DrawGameBoard {
	public DrawGameBoard() {}
	
	public void paint(Graphics g) {		
			
 		
		g.setColor(new Color(0,102,51));
		for (int i = 100; i <= 550; i+=50){
			for (int j = 100; j <= 550; j+=50) {
				g.fillOval(i, j, 20, 20);
			}
		}	      
		
		g.setColor(Color.BLACK);
		for (int i = 120; i <= 520; i+=50) 
		{
		    for (int j = 105; j <= 555; j+=50)
		  	  g.drawRect(i, j, 30, 10);
		}
		for (int i = 105; i <= 555; i+=50)
		{
		    for (int j = 120; j <= 520; j+=50)
		  	  g.drawRect(i, j, 10, 30);
		}      
		
		g.setColor(Color.BLACK);
          for (int i = 116; i <= 516; i+=50)
          {
              for (int j = 116; j <= 516; j+=50)
		 		g.drawRect(i, j, 39, 39);
          }                 
	}
	
	
}
