// sirdaniel711
// TTTPlayer.java
//
// This class represents a typical Tic-Tac-Toe player

public class TTTPlayer extends Player 
{
    public static final int DEFAULT = 0;
    public static final char DEFAULT_SYMBOL = 'X';
    
    private int totalWins;                  // total # of wins for current session
    protected char symbol;
    
    public TTTPlayer()
    {
        super();
        totalWins = DEFAULT;
        symbol = DEFAULT_SYMBOL;
    }
    
    public TTTPlayer(String name)
    {
        super(name);
        totalWins = DEFAULT;
        symbol = DEFAULT_SYMBOL;
    }
    
    public TTTPlayer(char mySym)
    {
        super();
        totalWins = DEFAULT;
        symbol = mySym;
    }
    
    public TTTPlayer(String name, char mySym)
    {
        super(name);
        totalWins = DEFAULT;
        symbol = mySym;
    }
    
    public int getTotalWins()
    {
        return totalWins;
    }
    
    public int getSelectedRow()             // methods for child classes, do nothing here
    {
        return DEFAULT;
    }
    
    public int getSelectedCol()
    {
        return DEFAULT;
    }
    
    public char getSym()
    {
        return symbol;
    }
    
    public void setSym(char mySym)
    {
        symbol = mySym;
    }
    
    public void makeChoice(Board board, int size)
    {
    }
    
    public void win()                    // increment total wins
    {
        totalWins++;
    }
    
    public void resetTotalWins()
    {
        totalWins = 0;
    }
    
    public String toString()
    {
        String result = super.toString();
        result += ",Total wins = " + totalWins + ", Symbol = " + symbol; 
        return result;
    }
}
