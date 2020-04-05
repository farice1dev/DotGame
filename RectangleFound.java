package dotgameapp;
/**
 * Finds the rectangle that the player clicked.
 * When a player click the mouse to claim a rectangle, the click must to be done inside the limits of the rectangle selected.
 * This class check this and it uses coordinates (x0,y0) and (x1,y1) to do it. @see StoreRectangleLocation
 * 
 * @author Jaime
 *
 */
public class RectangleFound {

	private int x;
	private int y;   
    private int[][]  arrayOfRectangles;
    private int index = 0;
   
    public RectangleFound(int x, int y, int[][] arrayOfRectangles) {
	        this.x = x;
	        this.y = y;
	        this.arrayOfRectangles = arrayOfRectangles;  
	}
    /**
     * Returns the number of the rectangle (index of the row) to continue the process of claiming or
     * return 0, if click were out of any rectangle.
     * @return the index.
     */
    public int getIndexOfArray()
    {
        for (int i = 1; i < arrayOfRectangles.length; i++)
        {
        	int x0 = arrayOfRectangles[i][0];
        	int x1 = arrayOfRectangles[i][1];
        	int y0 = arrayOfRectangles[i][2];
        	int y1 = arrayOfRectangles[i][3];
        	int playerHasClaimed = arrayOfRectangles[i][8];
        	
            if (x > x0 && x < x1 && 
            	y + 20 > y0 && y + 20  < y1)
            {
                if (playerHasClaimed == 0) 
                {
                    index = i; 
                    break; 	// the rectangle has been found. So the sentence "for" must stop
                }
            }
        }
        return index;
    }  
}
