/*
This class print the results of the game in a new window:
Print the amount of squares claimed by every player.
Show who is the winner.
*/
package dotgameapp;
import java.awt.*;
import javax.swing.*;
/**
 * Shows the score of players and who is the winner.
 * @author Jaime
 *
 */

public class Winner  extends JFrame
{

	private static final long serialVersionUID = 5251544644318136933L;
	private String labelWinner;
    private String countPlayer1;
    private String countPlayer2;
    
    public Winner(int totalSquaresPlayer1, int totalSquaresPlayer2) {
        if(totalSquaresPlayer1 < 10) {
        	countPlayer1 = "0" + totalSquaresPlayer1; //Integer.toString(totalSquaresPlayer1);
        }else {
        	countPlayer1 = "" + totalSquaresPlayer1;          	
        }
        
        if(totalSquaresPlayer1 < 10) {
        	countPlayer2 = "0" + totalSquaresPlayer2; 
        }else {	
        	countPlayer2 = "" + totalSquaresPlayer2; 
        }
        
        if(totalSquaresPlayer1 == totalSquaresPlayer2) {
        	labelWinner = "Match ended in a tie";
        }else  if(totalSquaresPlayer1 > totalSquaresPlayer2) {  	
        	labelWinner = "The winner is Player 1";
        }else{
        	labelWinner = "The winner is Player 2";
        }    	
    }
/**
 * Sets the window where the score is shown.
 */
    public void theWinner()
    {    
    	
        setTitle("**********    THE WINNER    **********");
        setSize(400,400);
        setBackground(Color.LIGHT_GRAY);
        setResizable(false);
        setLocation(1000, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Painter painter = new Painter(labelWinner, countPlayer1, countPlayer2);
        add(painter);
    }
}    
/**
 * Creates the panel where the score is shown.
 * @author Jaime
 *
 */
class Painter extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6308716336060862107L;
	private String winner;
	private String count1;
	private String count2;
	public Painter(String labelWinner, String count1,  String count2) {
	winner = labelWinner;
	this.count1 = count1;
	this.count2 = count2;
	}
	
	public void paintComponent(Graphics g) {
		
		Font letterNoteWorthy = new Font("NoteWorthy", Font.BOLD, 24);
		Font letterArial = new Font("Arial", Font.BOLD, 30);
		
 		super.paintComponent(g);
 		Graphics2D g2 = (Graphics2D) g;
 		
 		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 400, 400);

 		if (winner.equalsIgnoreCase("The winner is Player 1")) {
    		g2.fillRect(0, 0, 400, 400);
    		g2.setColor(Color.YELLOW);
    		g2.fillOval(250, 45, 100, 100);
    		
	     	g2.setColor(Color.YELLOW.darker());
	     	g2.fillRoundRect(275,70,50,50, 5, 5);
	    	
			g2.setColor(Color.BLACK);
			g2.setFont(letterArial);
			g2.drawString(count1, 283, 105);

    		g2.setColor(Color.GREEN);
	     	g2.fillOval(250, 190, 100, 100);
    		
    		g2.setColor(Color.GREEN.darker());
	     	g2.fillRoundRect(275, 215, 50, 50, 5, 5);
	     	
			g2.setColor(Color.BLACK);
			g2.setFont(letterArial);
			g2.drawString(count2, 283, 250);
			
	     	g2.setColor(Color.WHITE);
	     	g2.setFont(letterNoteWorthy);
			g2.drawString(winner, 20, 105);
	     	
    	}else if (winner.equalsIgnoreCase("The winner is Player 2")){
    		g2.setColor(Color.YELLOW);
    		g2.fillOval(250, 45, 100, 100);
    		
	     	g2.setColor(Color.YELLOW.darker());
	     	g2.fillRoundRect(275,70,50,50, 5, 5);
	    	
			g2.setColor(Color.BLACK);
			g2.setFont(letterArial);
			g2.drawString(count1, 283, 105);

    		g2.setColor(Color.GREEN);
	     	g2.fillOval(250, 190, 100, 100);
    		
    		g2.setColor(Color.GREEN.darker());
	     	g2.fillRoundRect(275, 215, 50, 50, 5, 5);
	     	
			g2.setColor(Color.BLACK);
			g2.setFont(letterArial);
			g2.drawString(count2, 283, 250);
			
					
			g2.setColor(Color.WHITE);
	     	g2.setFont(letterNoteWorthy);
			g2.drawString(winner, 20, 255);
			
     	}else {
    		g2.setColor(Color.YELLOW);
    		g2.fillOval(250, 45, 100, 100);
    		
	     	g2.setColor(Color.YELLOW.darker());
	     	g2.fillRoundRect(275,70,50,50, 5, 5);
	    	
			g2.setColor(Color.BLACK);
			g2.setFont(letterArial);
			g2.drawString(count1, 283, 105);

    		g2.setColor(Color.GREEN);
	     	g2.fillOval(250, 190, 100, 100);
    		
    		g2.setColor(Color.GREEN.darker());
	     	g2.fillRoundRect(275, 215, 50, 50, 5, 5);
	     	
			g2.setColor(Color.BLACK);
			g2.setFont(letterArial);
			g2.drawString(count2, 283, 250);
			
					
			g2.setColor(Color.WHITE);
	     	g2.setFont(letterNoteWorthy);
			g2.drawString(winner, 20, 150);
     	}
       
	}

}


