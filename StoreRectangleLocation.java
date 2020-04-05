package dotgameapp;
/**
 * Creates a 2-dimensional array with information about rectangles that will be drawn in the game-board. 
 * There will be vertical and horizontal rectangles. 
 * The index of row will be the number assigned to rectangles.
 * The array will have nine columns:
 * [0] and [2] store coordinates (x0,y0) used to draw and fill the rectangle.
 * [1] and [3] store coordinates (x1,y1) that corresponds the other end of the rectangle, and it is used to validate that a click is inside of the rectangle when a player wants to claim it.
 * [4] and [5] store width and height of rectangles respectively. 
 * [6] stores the number assigned to a square adjacent to the rectangle. @see SquareNum
 * [7] stores the number assigned to a second square adjacent to the same rectangle. 
 * [8] stores the number assigned to a player that claims the rectangle.       
 * @author Jaime
 *
 */
public class StoreRectangleLocation 
{
	public StoreRectangleLocation() {}
	
    private int[][] arrayOfRectangles = new int[181][10]; // Store parameters of rectangles
    
    /**
     * Returns the 2-dimensional array with the information about rectangles to be drawn or to be filled in the game-board . 
     * @return the arrayOfRectangles.
     */
    public int[][] getArrayForRectangles()
    {
        return arrayOfRectangles;
    }
    /**
     * Fills the array with the coordinates (x0,y0), (x1,y1), and width and height.
     */
    public void storeLocation() 
    {
        int limit = 18;
        int index = 0;
        
        // Stores information for vertical rectangles
        for (int x = 105; x <= 505; x+=50) 
        {
            for (int y = 120; y <= 520; y+=50)
            {
                if (index == limit)
                {
                    limit +=19;
                    index = index + 3;
                }
                else
                {
                    index +=2;
                }
                arrayOfRectangles[index][0] = x;
                arrayOfRectangles[index][1] = x + 10;
                arrayOfRectangles[index][2] = y;
                arrayOfRectangles[index][3] = y + 30;
                arrayOfRectangles[index][4] = 10;
                arrayOfRectangles[index][5] = 30;
            }                
        }
        limit = 19;
        index = -1;
        
        // Stores information for horizontal rectangles
        for (int x = 120; x <= 520; x+=50) 
        {
            for (int y = 105; y <= 555; y+=50)
            {
                if (index == limit)
                {
                    limit +=19;
                    index = index + 1;
                }
                else
                {
                    index +=2;
                }
                arrayOfRectangles[index][0] = x;
                arrayOfRectangles[index][1] = x + 30;
                arrayOfRectangles[index][2] = y;
                arrayOfRectangles[index][3] = y + 10;                
                arrayOfRectangles[index][4] = 30;
                arrayOfRectangles[index][5] = 10;
            }                
        }        
        index = 172;
        
        // For-loops before do not work for the last column of rectangles, so this for-loop fixes them.
        for (int y = 120; y <= 520; y+=50) 
        {
        	arrayOfRectangles[index][0] = 555;
        	arrayOfRectangles[index][1] = 555 + 10;
        	arrayOfRectangles[index][2] = y;
        	arrayOfRectangles[index][3] = y + 30;
        	arrayOfRectangles[index][4] = 10;
        	arrayOfRectangles[index][5] = 30;
            index +=1;
        }
    }
}
