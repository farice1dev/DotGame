package dotgameapp;
/**
 * Let a player claims a rectangle, and adjacent squares.
 * Checks if the rectangle has one or two adjacent squares, and claims the adjacent squares 
 * when all rectangles surrounding each square have been claimed.
 * If no one square is claimed, then changes the player.
 * Increments the total of squares claimed by each player. 
 * Increments the total squares claimed to control when the game is over
 * Checks if the game is over.
 * Creates getters for coordinates corresponding to the squares claimed.
 * 
 * @author Jaime
 *
 */
public class ClaimRectangleAndSquares 
{

    private int currentRect; // rectangle's number clicked.
    private int xS1 = 0; // coordinate x of the first square adjacent to the rectangle. 
    private int yS1 = 0; // coordinate y of the first square adjacent to the rectangle.
    private int xS2 = 0; // coordinate x of the second square adjacent to the rectangle. 
    private int yS2 = 0; // coordinate y of the second square adjacent to the rectangle.   
    private int currentIndex;  // index in the "arrayOfRectangles" corresponding to the rectangle clicked.
    private boolean claimed = false; // lets to know if the rectangle has been claimed or not.
    private int[][]  arrayOfRectangles; 
    private int[][]  arrayForSquares; 
    private boolean paintSQ1;  // lets to know if the first adjacent square must to be painted or not.
    private boolean paintSQ2;  // lets to know if the second adjacent square must to be painted or not.
    private boolean paintRect; // lets to know if the rectangle must to be painted or not.
    private boolean gameIsOver = false; // lets to stop the claiming process and continues to show the score process in other class.

    
    public ClaimRectangleAndSquares(){}
    DotGameFrame currentPlayer = new DotGameFrame();
    
    public ClaimRectangleAndSquares(int index, int[][] arrayOfRectangles, int[][]arrayForSquares) 
    {
    	currentIndex = index;
        this.arrayOfRectangles = arrayOfRectangles;
        this.arrayForSquares = arrayForSquares;

    }
    
    public void claiming()
    {		
        int maxSquareClaimed = 81;  // This variable is used just to test the game with less squares to be claimed.
        						   // For example, it can be tested with the first nine squares (3x3). So 81 must to be changed to 9.
      	
        	switch(DotGameFrame.player) 
            
            {
                case(1):
                    xS1 = 0;
                    yS1 = 0;
                    xS2 = 0;
                    yS2 = 0;
                                        
                    claimRectangleAndSquare();

                    if (xS1 != 0 && yS1 != 0) 	// The player 1 claimed an adjacent squares.
                    {
                        paintSQ1 = true;                        
                        DotGameFrame.squaresPlayer1++;	// Increments the amount of squares gained by the player.                 
                        DotGameFrame.totalSquares++;	// Increments the total squares claimed to know when the game is over.
                        
                        if (DotGameFrame.totalSquares == maxSquareClaimed)
                        {
                        	gameIsOver = true;
                        }
                        
                    }
                    
                    if (xS2 != 0 && yS2 != 0) 	// The player 1 claimed the second adjacent squares.
                    {
                        paintSQ2 = true; 
                        
                  		DotGameFrame.squaresPlayer1++;
	               		DotGameFrame.totalSquares++;

                        if (DotGameFrame.totalSquares == maxSquareClaimed)
	                    {
	                    	gameIsOver = true;
	                    }
                    }
                    // if the player did not claim any square, the player changes.
                    if (xS1 == 0 && yS1 == 0 && xS2 == 0 && yS2 == 0)	
                    {
                    	if(DotGameFrame.player == 1) {
        				   DotGameFrame.player = 2;
        				}else {
        				   DotGameFrame.player = 1;
        				}	
                    }
                    
                    if (claimed == false)
                    {
                        paintRect = true; 
                    }
                    break;
                case(2):
                    xS1 = 0;
                    yS1 = 0;
                    xS2 = 0;
                    yS2 = 0;
                    
                    claimRectangleAndSquare();
                    if (xS1 != 0 && yS1 != 0)
                    {
                        paintSQ1 = true; 
                        DotGameFrame.squaresPlayer2++;                        
                        DotGameFrame.totalSquares++;

                        if (DotGameFrame.totalSquares == maxSquareClaimed)
                        {
                        	gameIsOver = true;
                        }
                    }
                    if (xS2 != 0 && yS2 != 0)
                    {
                    	paintSQ2 = true;

                    	DotGameFrame.squaresPlayer2++;                        
                        DotGameFrame.totalSquares++;
                    	
                        if (DotGameFrame.totalSquares == maxSquareClaimed)
                        {
                        	gameIsOver = true;
                        }
                    }
                    if (xS1 == 0 && yS1 == 0 && xS2 == 0 && yS2 == 0)
                    {	
                    	if(DotGameFrame.player == 1) {
                    	   DotGameFrame.player = 2;
        				}else {
        				   DotGameFrame.player = 1;
        				}	                    	
                    }

                    if (claimed == false)
                    {
                    	paintRect = true; 
                    } 
                    break;
                default:
                    break;
            }  
    }
    /**
     * Verifies if the rectangle has one or two adjacent squares.
     * If it has one adjacent square @see {@link #claimingOneSquare()} 
     * if it has two adjacent squares @see {@link #claimingTwoSquare()}
     */
    
    public void claimRectangleAndSquare()
    {    	 
        if(arrayOfRectangles[currentIndex][8] == 0) // if current rectangle has not been claimed yet
        {
            claimed = false;  
            arrayOfRectangles[currentIndex][8] = DotGameFrame.player;  // Stores the player's number in column [8], so the rectangle is claimed.
            currentRect= currentIndex; // assign the index of the current claimed rectangle
                    
            if ( arrayOfRectangles[currentIndex][7] != 0) // The rectangle has a two adjacent squares.
            {   
                claimingTwoSquare();
                currentIndex = 0;
 
            }else{  
                claimingOneSquare();
                currentIndex = 0;           
            }
        }else{
            claimed = true; 
        }
    }
    /**
     * The square stored in the column [6] of the rectangle claimed has four rectangles surrounding it.
     * These four rectangles are stored in "arrayForSquares", columns [3],[4],[5],[6].
     * This method checks which one of these is equal to the current rectangle and then check if the rest of rectangle surrounding has been claimed.
     * If the rest of rectangles has been claimed then the player has to claim the square too @see {@link #claimTheSquare()}  
     */
    public void claimingOneSquare()
    {   // r1,r2,r3,r4 contain the index of every rectangle surrounding the square to be claimed.
        int r1;
        int r2;
        int r3;
        int r4;
        r1 = arrayForSquares[arrayOfRectangles[currentIndex][6]][3];   
        r2 = arrayForSquares[arrayOfRectangles[currentIndex][6]][4];
        r3 = arrayForSquares[arrayOfRectangles[currentIndex][6]][5];
        r4 = arrayForSquares[arrayOfRectangles[currentIndex][6]][6];
        if (currentRect== r1)
        {   
            if (arrayOfRectangles[r2][8] != 0 && arrayOfRectangles[r3][8] != 0 && arrayOfRectangles[r4][8] != 0) 
            // Checks if the rest of rectangles has been claimed.
            {
                claimTheSquare();
            }
        }else if (currentRect== r2)
        {
            if (arrayOfRectangles[r1][8] != 0 && arrayOfRectangles[r3][8] != 0 && arrayOfRectangles[r4][8] != 0) 
                // Checks if the rest of rectangles has been claimed.
            {
                claimTheSquare();
            }

        }else if (currentRect== r3)
        {  
            if (arrayOfRectangles[r2][8] != 0 && arrayOfRectangles[r1][8] != 0 && arrayOfRectangles[r4][8] != 0) 
                // Checks if the rest of rectangles has been claimed.
            { 
               claimTheSquare();
            }
        }else if (currentRect== r4)
        {   
            if (arrayOfRectangles[r2][8] != 0 && arrayOfRectangles[r3][8] != 0 && arrayOfRectangles[r1][8] != 0) 
                // Checks if the rest of rectangles has been claimed.
            {
               claimTheSquare();        
            }
        }
    }
    /**
     * The square stored in the column [7] (the second adjacent square) of the rectangle claimed has four rectangles surrounding it.
     * These four rectangles are stored in "arrayForSquares", columns [3],[4],[5],[6].
     * This method checks which one of these is equal to the current rectangle and then check if the rest of rectangle surrounding have been claimed.
     * If the rest of rectangles has been claimed then the player has to claim both 
     * squares  @see {@link #claimingTheFirstSquare()} and  @see {@link #claimTheSecondSquare()}
     */
    public void claimingTwoSquare()
    {   // r1,r2,r3,r4 contain the index of every rectangle surrounding the second square to be claimed.
        int r1;
        int r2;
        int r3;
        int r4;
        r1 = arrayForSquares[arrayOfRectangles[currentIndex][7]][3];
        r2 = arrayForSquares[arrayOfRectangles[currentIndex][7]][4];
        r3 = arrayForSquares[arrayOfRectangles[currentIndex][7]][5];
        r4 = arrayForSquares[arrayOfRectangles[currentIndex][7]][6];     
        if (currentRect== r1) // 3 == 3
        {   
            if (arrayOfRectangles[r2][8] != 0 && arrayOfRectangles[r3][8] != 0 && arrayOfRectangles[r4][8] != 0) 
            // if the rest of rectangles, surrounding the second square, has been claimed then claim both squares.
            {
                claimingTheFirstSquare();
            	claimTheSecondSquare();
            }else{
            	claimingTheFirstSquare();
            }
        }else if (currentRect== r2)
        {
            if (arrayOfRectangles[r1][8] != 0 && arrayOfRectangles[r3][8] != 0 && arrayOfRectangles[r4][8] != 0) 
                // if the rest of rectangles, surrounding the second square, has been claimed then claim both squares.
            {
                claimingTheFirstSquare();
            	claimTheSecondSquare();
            }else{
            	claimingTheFirstSquare();
            }
        }else if (currentRect== r3)
        {   
            if (arrayOfRectangles[r2][8] != 0 && arrayOfRectangles[r1][8] != 0 && arrayOfRectangles[r4][8] != 0) 
                // if the rest of rectangles, surrounding the second square, has been claimed then claim both squares.
            {
                claimingTheFirstSquare();
            	claimTheSecondSquare();
            }else{
            	claimingTheFirstSquare();
            }
        }else if (currentRect== r4)
        {   
            if (arrayOfRectangles[r2][8] != 0 && arrayOfRectangles[r3][8] != 0 && arrayOfRectangles[r1][8] != 0) 
                // if the rest of rectangles, surrounding the second square, has been claimed then claim both squares.
            {
                claimingTheFirstSquare();
            	claimTheSecondSquare();
            }else{
            	claimingTheFirstSquare();
            }
        } 
    
    }
    
 public void claimingTheFirstSquare()
    {	
	 claimingOneSquare(); 
    }    
 
    /**
     * Stores the player's number in the "arrayForSquares" to set it as claimed by the player.
     * Assigns the the coordinates (x,y) corresponding to the square to be painted in @see {@link DotGameFrame#paintSQ1Claimed()}
     */
    public void claimTheSquare()
    {    	
    	arrayForSquares[arrayOfRectangles[currentIndex][6]][7] = DotGameFrame.player; // Square claimed 
    	// [arrayOfRectangles[currentIndex][6]] contains the square's number adjacent to the rectangle.
    	// Second index, [7] corresponds to the column that stores the player who claim the square. 
        xS1 = arrayForSquares[arrayOfRectangles[currentIndex][6]][0];
        yS1 = arrayForSquares[arrayOfRectangles[currentIndex][6]][1];
    }
    
    /**
     * Stores the player's number in the "arrayForSquares" to set the second square as claimed by the player.
     * Assigns the the coordinates (x,y) corresponding to the square to be painted in @see {@link DotGameFrame#paintSQ2Claimed()}
     */
    public void claimTheSecondSquare()
    {    
    	this.arrayForSquares[arrayOfRectangles[currentIndex][7]][7] = DotGameFrame.player; // Square claimed    
       	// [arrayOfRectangles[currentIndex][7]] contains the second square's number adjacent to the rectangle.
    	// Secind index, [7] corresponds to the column that stores the player who claim the square. 
        xS2 = this.arrayForSquares[arrayOfRectangles[currentIndex][7]][0];
        yS2 = this.arrayForSquares[arrayOfRectangles[currentIndex][7]][1];
    }                  
    /**
     * Returns paintSQ1 as true or false to paint or not the first square.
     * @return the paintSQ1
     */
    public boolean getPaintSQ1() {
		return paintSQ1;
    }
    /**
     * Returns paintSQ2 as true or false to paint or not the second square.
     * @return the paintSQ2
     */
    public boolean getPaintSQ2() {
		return paintSQ2;
    }
    /**
     * Returns paintRect as true or false to paint or not the rectangle.
     * @return the paintRect
     */
    public boolean getPaintRect() {
		return paintRect;
    }
    /**
     * Returns coordinate x corresponding to the first square claimed.
     * @return the xS1
     */
    public int get_xS1() {
    	return xS1;
    }
    /**
     * Returns coordinate y corresponding to the first square claimed.
     * @return the yS1
     */
    public int get_yS1() {
    	return yS1;
    }
    /**
     * Returns coordinate x corresponding to the second square claimed.
     * @return xS2
     */
    public int get_xS2() {
    	return xS2;
    }
    /**
     * Returns coordinate y corresponding to the second square claimed.
     * @return the yS2
     */
    public int get_yS2() {
    	return yS2;
    }
    /**
     * Returns gameIsOver as true or false to continue or not the game.
     * @return the gameIsOver
     */
    public boolean getGameIsOver() {
 		return gameIsOver;
     }
     
}	// ---------- End of FillRectangle Class  -------------------