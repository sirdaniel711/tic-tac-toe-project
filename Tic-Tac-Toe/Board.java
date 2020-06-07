// sirdaniel711
// Board.java
//
// This program rerpesents a typical game board of specified size,
// where characters can be used for each board space. 
// Disclaimer: These boards are usually grids, which means all 
// rows are the same lenght and all columns are the same length.

public class Board
{
    public static final int DEFAULT_SIZE = 3;			// default 3x3 board size
    public static final int MAX_SIZE = 100;			// 100x100 is chosen as maximum board size capacity
    public static final char OPEN = ' ';			// open or blank spaces are represented as a blank char
    public static final char ERROR_VALUE = '#';			// represents an error value of a space, a substitute
    
    private char[][] board;					// actual board
    private int open;
    

    public Board()						// dimension 3x3 is used as default if no size is given
    {
        board = new char[DEFAULT_SIZE][DEFAULT_SIZE];		// initializing size of board
        for (int i = 0; i < board.length; i++)
        {
	        for (int j = 0; j < board[i].length; j++)
                board[i][j] = OPEN;				// initializing each space to a blank char
        }
        open = (DEFAULT_SIZE * DEFAULT_SIZE);
    }

    public Board(int row, int col)				// for if dimensions are given
    {
        if ((0 < row && row <= MAX_SIZE) && (0 < col && 	// error checking on size
            col <= MAX_SIZE))
        {
	        board = new char[row][col];
            open = (row * col);
        }
        else
        {
            board = new char[DEFAULT_SIZE][DEFAULT_SIZE];	// use default size if invalid size is given
            open = (DEFAULT_SIZE * DEFAULT_SIZE);
        }
        for (int i = 0; i < board.length; i++)
        {
	    for (int j = 0; j < board[i].length; j++)
                board[i][j] = OPEN;				
        }
    }

    public char getSpace(int row, int col)			// returns char of disignated space
    {
        if ((0 <= row && row < board.length) && (0 <= col &&	// error checking of designated space
	    col < board[0].length))				// Explaining (( board[0].lenght)): since all columns are of the same length, any column can be used here, but the first column 0 is used since it exists for any size of board
	    return board[row][col];
	else
	    return ERROR_VALUE;					// returns the error character if the designated space DNE
    }

    public int numOpen()
    {
        return open;
    }
    
    public boolean isOpen(int row, int col)			// checks if disignated space is open
    {
        if ((0 <= row && row < board.length) && (0 <= col &&	
	    col < board[0].length))
	    return (board[row][col] == OPEN);
	else
	    return false;					// note: returns false if designated space DNE
    }

    public void setSpace(int row, int col, char value)		// sets designated space to a designated char
    {
        if ((0 <= row && row < board.length) && (0 <= col &&	
	    col < board[0].length))
	    board[row][col] = value;
        open -= 1;
    }								// if disignated space DNE, do nothing

    public void reset()						// resets board by setting all spaces to blank
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = OPEN;
        }
        open = (board.length * board[0].length);
    }

    public boolean isEqual(int row1, int col1, int row2, int col2)
    {
        return (board[row1][col1] == board[row2][col2]);
    }

    public String toString()					// returns the board as a string of characters
    {
        String result = "";
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
                result += board[i][j] + " ";
            result += "\n";
        }
        return result;
    }
}

            