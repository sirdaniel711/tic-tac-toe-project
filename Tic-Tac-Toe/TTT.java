// sirdaniel711
// TTT.java
//
// This program represents a typical game of Tic-Tac-Toe, using both the board 
// and player classes.  

public class TTT
{
    public static final char X = 'X';
    public static final char O = 'O';
    public static final char DEFAULT_WINNER = ' ';
    public static final int INITIAL_TURN = 1;

    private TTTPlayer player1;
    private TTTPlayer player2;
    private Board myBoard;
    private int currentTurn;				// used for determining if game is a cat
    private int side;                       // size for each side of the board
    private int maxTurns;
    private char winner;
    private boolean isXTurn;				// for clarity
    private boolean gameOver;
    
    public TTT()		
    {
        player1 = new TTTPlayer("Player 1", X);
        player2 = new TTTPlayer("Player 2", O);
        myBoard = new Board();
        currentTurn = INITIAL_TURN;				// initial turn is 1, last turn is 9 by default
        side = Board.DEFAULT_SIZE;
        maxTurns = Board.DEFAULT_SIZE * Board.DEFAULT_SIZE;
        winner = DEFAULT_WINNER;			    // ' ' indicates no current winner
        isXTurn = true;				            // X goes first
        gameOver = false;
    }

    public char getSpace(int row, int col)
    {
        return myBoard.getSpace(row, col);
    }

    public int getCurrentTurn()
    {
        return currentTurn;
    }
    
    public int getCurrentSize()
    {
        return side;
    }

    public char getCurrentWinner()
    {
        return winner;
    }

    public char getCurrentPlayer()
    {
        char currentPlayer = ' ';
        if (isXTurn)
            currentPlayer = player1.getSym();
        else
            currentPlayer = player2.getSym();
        return currentPlayer;
    }       

    public boolean isXPlayerTurn()
    {
        return isXTurn;
    }

    public boolean isOPlayerTurn()
    {
        return !isXTurn;
    }

    public boolean isCat()
    {
        return (gameOver && (winner == DEFAULT_WINNER));	
    }

    public boolean isOpen(int row, int col)
    {
        return myBoard.isOpen(row, col);
    }

    public boolean isOver()
    {
        return gameOver;
    }
    
    public void setDimension(int size)              // changes size of board, game will be reset
    {
        Board newBoard = new Board(size, size);     // new board with new size 
        myBoard = newBoard;
        currentTurn = INITIAL_TURN;                 // can't use new game, since it resets the board to 3x3
        side = size;
        maxTurns = size * size;
        winner = DEFAULT_WINNER;
        isXTurn = true;
        gameOver = false;
    }
    
    public void setPlayer(boolean cpu1, boolean cpu2, int diff1, int diff2)     // note: does not support both players being computer yet...
    {
        TTTPlayer newPlayer1 = new TTTPlayer("Player 1", X);
        TTTPlayer newPlayer2 = new TTTPlayer("Player 2", O);
        ComputerPlayer comPlayer1 = new ComputerPlayer("Player 1", X);
        ComputerPlayer comPlayer2 = new ComputerPlayer("Player 2", O);
        
        // System.out.println("player1 is " + player1.getSym());
        // System.out.println("player2 is " + player2.getSym());

        if (cpu1 && !cpu2)                          // first player is computer
        {
            if (diff1 == 1 || diff1 == 2)
                comPlayer1.setDiff(diff1);
            comPlayer1.setSym(player1.getSym());
            player1 = comPlayer1;
            System.out.println("player1 is now a computer set to " + diff1);
            System.out.println("player2 is now a human");
        }
        else if (!cpu1 && cpu2)                     // second player is computer
        {
            if (diff2 == 1 || diff2 == 2)
                comPlayer1.setDiff(diff2);
            comPlayer1.setSym(player2.getSym());
            player2 = comPlayer1;
            System.out.println("player1 is now a human");
            System.out.println("player2 is now a computer set to " + diff2);
        }
        else                                        // neither player is computer (also for if both players are set to be computer, which is not supported yet)
        {
            newPlayer1.setSym(player1.getSym());
            player1 = newPlayer1;
            newPlayer2.setSym(player2.getSym());
            player2 = newPlayer2;
            System.out.println("player1 is now a human");
            System.out.println("player2 is now a human");
        }
        currentTurn = INITIAL_TURN;
        winner = DEFAULT_WINNER;
        isXTurn = true;
        gameOver = false;
        myBoard.reset();
    }

    public void newGame()
    {
        myBoard.reset();
        currentTurn = INITIAL_TURN;
        winner = DEFAULT_WINNER;
        isXTurn = true;
        gameOver = false;
    }
    
    public void aIMove()
    {
        if (isXTurn)
            player1.makeChoice(myBoard, side);
        else
             player2.makeChoice(myBoard, side);
    }
    
    public int getAIRow()
    {
        if (isXTurn)
            return player1.getSelectedRow();
        else
            return player2.getSelectedRow();
    }
    
    public int getAICol()
    {
        if (isXTurn)
            return player1.getSelectedCol();
        else
            return player2.getSelectedCol();
    }

    public void makeMove(int row, int col)		// Only makes a move if the game is not over and if the selected spot is open
    {
        if (!gameOver)
        {
            if (isOpen(row, col))
            {
                if (isXTurn)
                    myBoard.setSpace(row, col, player1.getSym());
                else
                    myBoard.setSpace(row, col, player2.getSym());
                gameOver = checkBoard(row, col);	// checks to see if someone won the game 
                if (gameOver)				// if someone won, check who's turn it was
                {
                    if (isXTurn)
                        winner = player1.getSym();
                    else 
                        winner = player2.getSym();
                }

                else					
                {
                    if (currentTurn < maxTurns)	// else if still more turns available, go to next turn
                    {
                        currentTurn++;
                        isXTurn = !isXTurn;
                    }
                    else				// else, if no winner and no more turns left, then it must be a draw
                        gameOver = true;		// i.e., a cat game
                }
            }
        }
    }

    public String toString()
    {
        String result = myBoard + "\n";
        if (isXTurn)
            result += "It is currently the X Player's turn ";
        else
            result += "It is currently the O Player's turn ";
        result += "on turn " + currentTurn + "\n";
        if (gameOver)
        {
            if (winner != DEFAULT_WINNER)
                result += "Game is over! The winner is " + winner;
            else 
                result += "Game is over! This game is a cat";
        }
        return result;
    }

    private boolean checkBoard(int row, int col)				 // helper method, uses recursion to check for matches
    {
        boolean[] result = new boolean[side - 1];
        boolean finalResult = false;
        int count = 0;
                
        for (int i = 0; i < (side - 1); i++)
        {
            result[i] = false;
        }
                
        for (int i = 0; i < (side - 1); i++)                    // checking row (side - 1 is for representing the grid as 0 to 2 for a 3x3 board
        { 
            if (myBoard.isEqual(row, i, row, (i + 1)))              
            {
                result[i] = true;
            }
        }
        
        for (int i = 0; i < (side - 1); i++)
        {
            if (result[i])
                count++;
        }
        if (count == (side - 1))
        {
            finalResult = true;
        }
        else
        {
            for (int i = 0; i < (side - 1); i++)                    // reset b/t ea. check if failed
                result[i] = false;
        }
        count = 0;
        
        for (int i = 0; i < (side - 1); i++)                    // checking column
        {
            if (myBoard.isEqual(i, col, (i + 1), col))
            {
                result[i] = true;
            }
        }
        
        for (int i = 0; i < (side - 1); i++)
        {
            if (result[i])
                count++;
        }
        if (count == (side - 1))
        {
            finalResult = true;
        }
        else
        {
            for (int i = 0; i < (side - 1); i++)                    // reset b/t ea. check if failed
                result[i] = false;
        }
        count = 0;
        
        if (row == col)                                         // checking diagonal if applicable
        {
            for (int i = 0; i < (side - 1); i++)
            {
                if (myBoard.isEqual(i, i, (i + 1), (i + 1)))
                {
                    result[i] = true;
                }
            }
        }
        
        for (int i = 0; i < (side - 1); i++)
        {
            if (result[i])
                count++;
        }
        if (count == (side - 1))
        {
            finalResult = true;
        }
        else
        {
            for (int i = 0; i < (side - 1); i++)                    // reset b/t ea. check if failed
                result[i] = false;
        }
        count = 0;
        
        if ((row + col) == (side - 1))                                   // checking other diagonal if applicable
        {
            for (int i = 0; i < (side - 1); i++)
            {
                if (myBoard.isEqual(((side - 1) - i), i, ((side - 1) - (i + 1)), (i + 1)))
                {
                    result[i] = true;
                }
            }
        }
    
        // System.out.println("Done checking!!!");
        
        for (int i = 0; i < (side - 1); i++)
        {
            if (result[i])
                count++;
        }
        
        if (count == (side - 1))
        {
            finalResult = true;
        }

        return finalResult;
    }    
}    
        

// starts with turn 1, goes up to turn 9, turn 5 is the earliest turn the game can end with a winner, turn 9 could either have a winner or be a cat game        

         