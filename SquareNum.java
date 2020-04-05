package dotgameapp;
/**
 * Creates a 2-dimensional array with information about 81 square that will be drawn in the game-board. 
 * And they will be filled with a color when they are claimed by a player.
 * The game-board will have a matrix of squares (9x9), and every square will have a number assigned.
 * The number assigned to squares will be the index of the array's row.
 * The array will have eight columns:
 * [0] and [1] store coordinates (x,y) to draw and fill the square.
 * [2] stores width and height of the square. width=height=39. It is not needed but it could be useful for future versions.
 * [3] to [6] store the number of every of four rectangles that surrounds the square.
 * [7] stores the number assigned to the player that claims the square. 
 * @author Jaime
 *
 */

public class SquareNum 
{
    private int[][] arrayForSquares = new int[82][8]; // Store parameters of squares     
    private int[][] arrayForRectangles; 

    public SquareNum(int[][] arrayForRectangles) 
    {
        this.arrayForRectangles = arrayForRectangles;
    }
    
    public SquareNum() {}
    
    /**
     * Assigns a number to every square in the game-board. The numbers are from the top to bottom and from the left to right.
     * The index of the row will be the number of the squares
     */
    public void numSquares() // enumerate squares from 1 to 81. The index is the number.
    { 
        int limit = 9;
        int index = 0;

        for (int x = 116; x <= 516; x+=50) 
        // Capture the squares position to store it in the Squares Array
        {
            for (int y = 116; y <= 516; y+=50)
            {
                if (index == limit)
                {
                    limit +=9;
                    index = index + 1;
                }
                else
                {
                    index +=1;
                }
                arrayForSquares[index][0] = x;
                arrayForSquares[index][1] = y;
            }                
        }
    }

    public int[][] getArrayForSquares() 
    {
        return arrayForSquares;
    }
    
    /**
     * Assigns to every squares, the number corresponding to every rectangles that surround that square.
     * Every square is surrounded by four rectangles.
     */
    public void assignRectangles() 
    {
    	// The variable numRectangles represents the index in the array "arrayOfRectangles", 
    	// and follows the logic that rectangles were created.
        int numRectangles = 1;
        int stopFor = 0;
        int indexSquare = 1;
        while(numRectangles <= 171)
        {
            for (int i = indexSquare; i <= stopFor + 9; i++)
            {
            	arrayForSquares[i][2] = 39;
            	arrayForSquares[i][3] = numRectangles;
            	arrayForSquares[i][4] = numRectangles+1;
            	arrayForSquares[i][5] = numRectangles+2;
            	arrayForSquares[i][6] = numRectangles + 20;
            	arrayForSquares[i][7] = 0;
            numRectangles +=2;     
            }
            numRectangles += 1;
            indexSquare += 9;
            stopFor += 9;
        }
        
        numRectangles = 172;
        for (int i = 73; i <= 81; i++)
        // For-loop before does not work for last columns of 9 squares.
        // This for-loop fixes the the number assigned to squares.
        {
        	arrayForSquares[i][6] = numRectangles;
            numRectangles +=1;     
        }
    }
	/**
	 * Assigns the square's number to every rectangle where this square is adjacent, 
	 * and stores the information in columns-indexes [6] and [7] of "arrayForRectangles".
	 * @see StoreRectangleLocation
	 */
    public void assignSquaresToRectangles()   
    {
        for (int i = 1; i < this.arrayForSquares.length; i++)
        {
            for(int j = 3; j <= 6; j++)
            {
                if (arrayForRectangles[this.arrayForSquares[i][j]][6] == 0)
                	arrayForRectangles[this.arrayForSquares[i][j]][6] = i;
                else
                {
                	arrayForRectangles[this.arrayForSquares[i][j]][7] = i;
                }
            }
        }

    }    
}
