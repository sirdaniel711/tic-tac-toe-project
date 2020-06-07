// sirdaniel711
// ComputerPlayer.java
//
// This program represents an AI controlled player. 
// As an easy AI, all it does is it makes a random move

public class ComputerPlayer extends TTTPlayer
{
    public static final int HIGHEST_DIFFICULTY = 2;     
    
    private int selectedRow;
    private int selectedCol;
    private int difficulty;
    
    public ComputerPlayer()
    {
        super();
        selectedRow = DEFAULT;
        selectedCol = DEFAULT;
        difficulty = DEFAULT;
    }
        
    public ComputerPlayer(int diff)
    {
        super();
        if (diff <= HIGHEST_DIFFICULTY && diff >= DEFAULT)
            difficulty = diff;
        selectedRow = DEFAULT;
        selectedCol = DEFAULT;
    }
    
    public ComputerPlayer(char mySym)
    {
        super(mySym);
        selectedRow = DEFAULT;
        selectedCol = DEFAULT;
        difficulty = DEFAULT;
    }
    
    public ComputerPlayer(String name)
    {
        super(name);
        selectedRow = DEFAULT;
        selectedCol = DEFAULT;
        difficulty = DEFAULT;
    }
    
    public ComputerPlayer(char mySym, int diff)
    {
        super(mySym);
        if (diff <= HIGHEST_DIFFICULTY && diff >= DEFAULT)
            difficulty = diff;
        selectedRow = DEFAULT;
        selectedCol = DEFAULT;
    }
    
    public ComputerPlayer(String name, int diff)
    {
        super(name);
        if (diff <= HIGHEST_DIFFICULTY && diff >= DEFAULT)
            difficulty = diff;
        selectedRow = DEFAULT;
        selectedCol = DEFAULT;
    }
    
    public ComputerPlayer(String name, char mySym)
    {
        super(name, mySym);
        selectedRow = DEFAULT;
        selectedCol = DEFAULT;
        difficulty = DEFAULT;
    }
    
    public ComputerPlayer(String name, char mySym, int diff)
    {
        super(name, mySym);
        if (diff <= HIGHEST_DIFFICULTY && diff >= DEFAULT)
            difficulty = diff;
        selectedRow = DEFAULT;
        selectedCol = DEFAULT;
    }
    
    public int getSelectedRow()
    {
        return selectedRow;
    }
    
    public int getSelectedCol()
    {
        return selectedCol;
    }
    
    public int getDiff()
    {
        return difficulty;
    }
    
    public void setDiff(int diff)
    {
        if (diff <= HIGHEST_DIFFICULTY && diff >= DEFAULT)
            difficulty = diff;
    }
    
    public void makeChoice(Board board, int size)             // makes a move based on difficulty 
    {
        Board myBoard = board;      // copy of the current board to examine
        int selector = DEFAULT;     // used to store the selected move from the random generator
        int counter = DEFAULT;      // counts the # of available spaces
        int row1 = DEFAULT;         // for hard difficulty, helps locate open spaces
        int row2 = DEFAULT;
        int col1 = DEFAULT;
        int col2 = DEFAULT;
        char adjSpace = myBoard.OPEN;       // for hard, marks whether the adjacent match is its own or the opponent  (puts priority on winning over blocking)
        boolean con1 = false;               // monitors whether a match is found from a row, col, or diagnal
        boolean con2 = false;
        boolean con3 = false;
        boolean con4 = false;
        boolean first = false;
        boolean path1 = false;
        boolean path2 = false;
        boolean path1A = false;             // for hard, helps track which path is taken in the strategy
        boolean path2A = false;
        boolean path1B = false;
        boolean chosen = false;             // for hard, indicates whether a technique is used
        // System.out.println("HEEELLLOO!!!!!!");
                     
        // easy
        if (difficulty == 0)                        // for easy, just select a random available space
        {                                           // logic: count # of available spaces, then select one at random & record its row & col (location)
            // for (int i = 0; i < size; i++)
            // {
            //     for (int j = 0; j < size; j++)      // this can be simplified now...
            //     {
            //         if (myBoard.isOpen(i, j))
            //             counter++;                  // counting # of available spaces
            //     }
            // }
        
            System.out.println("Easy Path");
            selector = ((int)(myBoard.numOpen() * Math.random())) + 1;        // select one at random
            counter = DEFAULT;
        
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    if (myBoard.isOpen(i, j))
                    {
                        counter++;                  // iterate through available spaces until the selected one is reached
                        if (counter == selector)
                        {
                            selectedRow = i;        // record the row & column of the selected space
                            selectedCol = j;
                        }
                    }
                }
            }
        }
        
        // medium
        else if (difficulty == 1)                   // for meadium, select random unless there is a 2 in a row, then select that space
        {                                           // logic: scan for a certain pattern: 2 in a row
                                                    // if none, choose one at random (same as easy), otherwise choose that spot
            System.out.println("Medium Path");
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    if (myBoard.isOpen(i, j))
                    {
                        // 3x3
                        if (size == myBoard.DEFAULT_SIZE)
                        {
                            for (int k = 0; k < (size - 1); k++)                // for a 3x3 board
                            {
                                for (int l = (k + 1); l < size; l++)
                                {
                                    if ((j != k) && (j != l) && myBoard.isEqual(i, k, i, l) && !myBoard.isOpen(i, k))               // checking row
                                    {
                                        con1 = (myBoard.isEqual(i, k, i, l) && !myBoard.isOpen(i, k));
                                        System.out.println("con1 is " + con1);
                                        selectedRow = i;
                                        selectedCol = j;
                                        counter++;
                                    }
                                    if ((i != k) && (i != l) && myBoard.isEqual(k, j, l, j) && !myBoard.isOpen(k, j))              // checking col
                                    {
                                        con2 = (myBoard.isEqual(k, j, l, j) && !myBoard.isOpen(k, j));
                                        System.out.println("con2 is " + con2);
                                        selectedRow = i;
                                        selectedCol = j;
                                        counter++;
                                    }
                                    if ((i == j) && (i != k) && (i != l) && myBoard.isEqual(k, k, l, l) && !myBoard.isOpen(k, k))   // checking diag 
                                    {
                                        con3 = (myBoard.isEqual(k, k, l, l) && !myBoard.isOpen(k, k));
                                        System.out.println("con3 is " + con3);
                                        selectedRow = i;
                                        selectedCol = j;
                                        counter++;
                                    }
                                    if (((i + j) == (size - 1)) && (i != k) && (i != l) && myBoard.isEqual(k, (size - 1) - k, l, (size - 1) - l) 
                                        && !myBoard.isOpen(k, (size - 1) - k))
                                    {
                                        con4 = (myBoard.isEqual(k, (size - 1) - k, l, (size - 1) -l) && !myBoard.isOpen(k, (size - 1) - k));
                                        System.out.println("con4 is " + con4);
                                        selectedRow = i;
                                        selectedCol = j;
                                        counter++;
                                    }
                                }
                            }
                        }
                        
                        // 4x4
                        else if (size == (myBoard.DEFAULT_SIZE + 1))
                        {
                            for (int k = 0; k < (size - 2); k++)                // for a 4x4 board
                            {
                                for (int l = (k + 1); l < (size - 1); l++)
                                {
                                    for (int m = (l + 1); m < size; m++)
                                    {
                                        if ((j != k) && (j != l) && (j != m) && myBoard.isEqual(i, k, i, l) && myBoard.isEqual(i, l, i, m) 
                                            && !myBoard.isOpen(i, k))           // checking row
                                        {
                                            con1 = (myBoard.isEqual(i, k, i, l) && myBoard.isEqual(i, l, i, m) && !myBoard.isOpen(i, k));
                                            System.out.println("con1 is " + con1);
                                            System.out.println(" " + i + "," + k + " matches " + i + "," + l + " and " + i + "," + m);
                                            selectedRow = i;
                                            selectedCol = j;
                                            counter++;
                                        }
                                        if ((i != k) && (i != l) && (i != m) && myBoard.isEqual(k, j, l, j) && myBoard.isEqual(l, j, m, j) 
                                            && !myBoard.isOpen(k, j))           // checking col
                                        {
                                            con2 = (myBoard.isEqual(k, j, l, j) && myBoard.isEqual(l, j, m, j) && !myBoard.isOpen(k, j));
                                            System.out.println("con2 is " + con2);
                                            System.out.println(" " + k + "," + j + " matches " + l + "," + j + " and " + m + "," + j);
                                            selectedRow = i;
                                            selectedCol = j;
                                            counter++;
                                        }
                                        if ((i == j) && (i != k) && (i != l) && (i != m) && myBoard.isEqual(k, k, l, l) && myBoard.isEqual(l, l, m, m) 
                                            && !myBoard.isOpen(k, k))           // checking diag 
                                        {
                                            con3 = (myBoard.isEqual(k, k, l, l) && myBoard.isEqual(l, l, m, m) && !myBoard.isOpen(k, k));
                                            System.out.println("con3 is " + con3);
                                            System.out.println(" " + k + "," + k + " matches " + l + "," + l + " and " + m + "," + m);
                                            selectedRow = i;
                                            selectedCol = j;
                                            counter++;
                                        }
                                        if (((i + j) == (size - 1)) && (i != k) && (i != l) && (i != m) && myBoard.isEqual(k, (size - 1) - k, l, 
                                            (size - 1) - l) && myBoard.isEqual(l, (size - 1) - l, m, (size - 1) - m) 
                                            && !myBoard.isOpen(k, (size - 1) - k))
                                        {
                                            con4 = (myBoard.isEqual(k, (size - 1) - k, l, (size - 1) -l) && myBoard.isEqual(l, (size - 1) - l, 
                                                m, (size - 1) - m) && !myBoard.isOpen(k, (size - 1) - k));
                                            System.out.println("con4 is " + con4);
                                            System.out.println(" " + k + "," + ((size - 1) - k) + " matches " + l + "," + ((size - 1) - l) 
                                                + " and " + m + "," + ((size - 1) - m));
                                            selectedRow = i;
                                            selectedCol = j;
                                            counter++;
                                        }
                                    }
                                }
                            }
                        }
                        
                        // 5x5
                        else
                        {
                            for (int k = 0; k < (size - 3); k++)                // for a 5x5 board
                            {
                                for (int l = (k + 1); l < (size - 2); l++)
                                {
                                    for (int m = (l + 1); m < (size - 1); m++)
                                    {
                                        for (int n = (m + 1); n < size; n++)
                                        {
                                            if ((j != k) && (j != l) && (j != m) && (j != n) && myBoard.isEqual(i, k, i, l) && myBoard.isEqual(i, l, i, m) 
                                                && myBoard.isEqual(i, m, i, n) && !myBoard.isOpen(i, k))           // checking row
                                            {
                                                con1 = (myBoard.isEqual(i, k, i, l) && myBoard.isEqual(i, l, i, m) && myBoard.isEqual(i, m, i, n) 
                                                    && !myBoard.isOpen(i, k));
                                                System.out.println("con1 is " + con1);
                                                selectedRow = i;
                                                selectedCol = j;
                                                counter++;
                                            }
                                            if ((i != k) && (i != l) && (i != m) && (i != n) && myBoard.isEqual(k, j, l, j) && myBoard.isEqual(l, j, m, j) 
                                                && myBoard.isEqual(m, j, n, j) && !myBoard.isOpen(k, j))           // checking col
                                            {
                                                con2 = (myBoard.isEqual(k, j, l, j) && myBoard.isEqual(l, j, m, j) && myBoard.isEqual(m, j, n, j) 
                                                    && !myBoard.isOpen(k, j));
                                                System.out.println("con2 is " + con2);
                                                selectedRow = i;
                                                selectedCol = j;
                                                counter++;
                                            }
                                            if ((i == j) && (i != k) && (i != l) && (i != m) && (i != n) && myBoard.isEqual(k, k, l, l) 
                                                && myBoard.isEqual(l, l, m, m) && myBoard.isEqual(m, m, n, n) && !myBoard.isOpen(k, k))  // checking diag 
                                            {
                                                con3 = (myBoard.isEqual(k, k, l, l) && myBoard.isEqual(l, l, m, m) && myBoard.isEqual(m, m, n, n) 
                                                    && !myBoard.isOpen(k, k));
                                                System.out.println("con3 is " + con3);
                                                selectedRow = i;
                                                selectedCol = j;
                                                counter++;
                                            }
                                            if (((i + j) == (size - 1)) && (i != k) && (i != l) && (i != m) && (i != n) 
                                                && myBoard.isEqual(k, (size - 1) - k, l, (size - 1) - l) && myBoard.isEqual(l, (size - 1) - l, m, 
                                                (size - 1) - m) && myBoard.isEqual(m, (size - 1) - m, n, (size - 1) - n) 
                                                && !myBoard.isOpen(k, (size - 1) - k))
                                            {
                                                con4 = (myBoard.isEqual(k, (size - 1) - k, l, (size - 1) -l) && myBoard.isEqual(l, (size - 1) - l, 
                                                    m, (size - 1) - m) && myBoard.isEqual(m, (size - 1) - m, n, (size - 1) - n) 
                                                    && !myBoard.isOpen(k, (size - 1) - k));
                                                System.out.println("con4 is " + con4);
                                                selectedRow = i;
                                                selectedCol = j;
                                                counter++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
                        
            if (counter == DEFAULT)             // if no 2 in a row is found, pick a spot at random
            {
                counter = DEFAULT;
                
                // for (int i = 0; i < size; i++)
                // {
                //     for (int j = 0; j < size; j++)
                //     {
                //         if (myBoard.isOpen(i, j))
                //             counter++;                  // counting # of available spaces
                //     }
                // }
                
                selector = ((int)(myBoard.numOpen() * Math.random())) + 1;        // select one at random
                counter = DEFAULT;
                
                for (int i = 0; i < size; i++)
                {
                    for (int j = 0; j < size; j++)
                    {
                        if (myBoard.isOpen(i, j))
                        {
                            counter++;                  // iterate through available spaces until the selected one is reached
                            if (counter == selector)
                            {
                                selectedRow = i;        // record the row & column of the selected space
                                selectedCol = j;
                            }
                        }
                    }
                }
            }
        }
        
        
        // hard
        else                                        // for hard, follow a strategy unless there is a 2 in a row, then select that space
        {                                           // logic: scan for a certain pattern: 2 in a row
            System.out.println("Hard Path");
                                                    // if none, follow a strategy, otherwise choose that spot
            for (int i = 0; i < size; i++)          // mostly hard coded
            {
                for (int j = 0; j < size; j++)
                {
                    if (myBoard.isOpen(i, j))
                    {
                        // 3x3
                        if (size == myBoard.DEFAULT_SIZE)
                        {
                            for (int k = 0; k < (size - 1); k++)                // for a 3x3 board
                            {
                                for (int l = (k + 1); l < size; l++)
                                {
                                    if ((j != k) && (j != l) && myBoard.isEqual(i, k, i, l) && !myBoard.isOpen(i, k))               // checking row
                                    {
                                        if (adjSpace == symbol)     // if winning
                                        {
                                            con1 = (myBoard.isEqual(i, k, i, l) && (myBoard.getSpace(i, k) == adjSpace));
                                            System.out.println("con1a is " + con1);
                                            if (con1)
                                            {
                                                selectedRow = i;
                                                selectedCol = j;
                                            }
                                        }
                                        else                        // else if blocking
                                        {
                                            con1 = (myBoard.isEqual(i, k, i, l) && !myBoard.isOpen(i, k));
                                            System.out.println("con1b is " + con1);
                                            selectedRow = i;
                                            selectedCol = j;
                                            adjSpace = myBoard.getSpace(i, k);
                                            System.out.println("adjSpace = " + adjSpace);
                                        }
                                    }
                                    if ((i != k) && (i != l) && myBoard.isEqual(k, j, l, j) && !myBoard.isOpen(k, j))              // checking col
                                    {
                                        if (adjSpace == symbol)
                                        {
                                            con2 = (myBoard.isEqual(k, j, l, j) && (myBoard.getSpace(k, j) == adjSpace));
                                            System.out.println("con2a is " + con2);
                                            if (con2)
                                            {
                                                selectedRow = i;
                                                selectedCol = j;
                                            }
                                        }
                                        else
                                        {
                                            con2 = (myBoard.isEqual(k, j, l, j) && !myBoard.isOpen(k, j));
                                            System.out.println("con2b is " + con2);
                                            selectedRow = i;
                                            selectedCol = j;
                                            adjSpace = myBoard.getSpace(k, j);
                                            System.out.println("adjSpace = " + adjSpace);                                            
                                        }
                                    }
                                    if ((i == j) && (i != k) && (i != l) && myBoard.isEqual(k, k, l, l) && !myBoard.isOpen(k, k))   // checking diag 
                                    {
                                        if (adjSpace == symbol)
                                        {
                                            con3 = (myBoard.isEqual(k, k, l, l) && (myBoard.getSpace(k, k) == adjSpace));
                                            System.out.println("con3a is " + con3);
                                            if (con3)
                                            {
                                                selectedRow = i;
                                                selectedCol = j;
                                            }
                                        }
                                        else
                                        {
                                            con3 = (myBoard.isEqual(k, k, l, l) && !myBoard.isOpen(k, k));
                                            System.out.println("con3b is " + con3);
                                            selectedRow = i;
                                            selectedCol = j;
                                            adjSpace = myBoard.getSpace(k, k);
                                            System.out.println("adjSpace = " + adjSpace);                                            
                                        }
                                    }
                                    if (((i + j) == (size - 1)) && (i != k) && (i != l) && myBoard.isEqual(k, (size - 1) - k, l, (size - 1) - l) 
                                        && !myBoard.isOpen(k, (size - 1) - k))
                                    {
                                        if (adjSpace == symbol)
                                        {
                                            con4 = (myBoard.isEqual(k, (size - 1) - k, l, (size - 1) - l) 
                                                    && (myBoard.getSpace(k, (size - 1) - k) == adjSpace));
                                            System.out.println("con4a is " + con4);
                                            if (con4)
                                            {
                                                selectedRow = i;
                                                selectedCol = j;
                                            }
                                        }
                                        con4 = (myBoard.isEqual(k, (size - 1) - k, l, (size - 1) -l) && !myBoard.isOpen(k, (size - 1) - k));
                                        System.out.println("con4b is " + con4);
                                        selectedRow = i;
                                        selectedCol = j;
                                        adjSpace = myBoard.getSpace(k, (size - 1) - k);
                                        System.out.println("adjSpace = " + adjSpace);                                        
                                    }
                                }
                            }
                        }

                        // 4x4
                        else if (size == (myBoard.DEFAULT_SIZE + 1))
                        {
                            for (int k = 0; k < (size - 2); k++)                // for a 4x4 board
                            {
                                for (int l = (k + 1); l < (size - 1); l++)
                                {
                                    for (int m = (l + 1); m < size; m++)
                                    {
                                        if ((j != k) && (j != l) && (j != m) && myBoard.isEqual(i, k, i, l) && myBoard.isEqual(i, l, i, m) 
                                            && !myBoard.isOpen(i, k))           // checking row
                                        {
                                            con1 = (myBoard.isEqual(i, k, i, l) && myBoard.isEqual(i, l, i, m) && !myBoard.isOpen(i, k));
                                            System.out.println("con1 is " + con1);
                                            System.out.println(" " + i + "," + k + " matches " + i + "," + l + " and " + i + "," + m);
                                            selectedRow = i;
                                            selectedCol = j;
                                            counter++;
                                        }
                                        if ((i != k) && (i != l) && (i != m) && myBoard.isEqual(k, j, l, j) && myBoard.isEqual(l, j, m, j) 
                                            && !myBoard.isOpen(k, j))           // checking col
                                        {
                                            con2 = (myBoard.isEqual(k, j, l, j) && myBoard.isEqual(l, j, m, j) && !myBoard.isOpen(k, j));
                                            System.out.println("con2 is " + con2);
                                            System.out.println(" " + k + "," + j + " matches " + l + "," + j + " and " + m + "," + j);
                                            selectedRow = i;
                                            selectedCol = j;
                                            counter++;
                                        }
                                        if ((i == j) && (i != k) && (i != l) && (i != m) && myBoard.isEqual(k, k, l, l) && myBoard.isEqual(l, l, m, m) 
                                            && !myBoard.isOpen(k, k))           // checking diag 
                                        {
                                            con3 = (myBoard.isEqual(k, k, l, l) && myBoard.isEqual(l, l, m, m) && !myBoard.isOpen(k, k));
                                            System.out.println("con3 is " + con3);
                                            System.out.println(" " + k + "," + k + " matches " + l + "," + l + " and " + m + "," + m);
                                            selectedRow = i;
                                            selectedCol = j;
                                            counter++;
                                        }
                                        if (((i + j) == (size - 1)) && (i != k) && (i != l) && (i != m) && myBoard.isEqual(k, (size - 1) - k, l, 
                                            (size - 1) - l) && myBoard.isEqual(l, (size - 1) - l, m, (size - 1) - m) 
                                            && !myBoard.isOpen(k, (size - 1) - k))
                                        {
                                            con4 = (myBoard.isEqual(k, (size - 1) - k, l, (size - 1) -l) && myBoard.isEqual(l, (size - 1) - l, 
                                                m, (size - 1) - m) && !myBoard.isOpen(k, (size - 1) - k));
                                            System.out.println("con4 is " + con4);
                                            System.out.println(" " + k + "," + ((size - 1) - k) + " matches " + l + "," + ((size - 1) - l) 
                                                               + " and " + m + "," + ((size - 1) - m));
                                            selectedRow = i;
                                            selectedCol = j;
                                            counter++;
                                        }
                                    }
                                }
                            }                                         // note: for 4x4 and 5x5 board sizes, hard is similar to medium for now
                            if (adjSpace == myBoard.OPEN)             // if no 2 in a row is found, choose randomly 
                            {
                                counter = DEFAULT;
                                
                                // for (int i = 0; i < size; i++)
                                // {
                                //     for (int j = 0; j < size; j++)
                                //     {
                                //         if (myBoard.isOpen(i, j))
                                //             counter++;                  // counting # of available spaces
                                 //    }
                                // }
                                
                                selector = ((int)(myBoard.numOpen() * Math.random())) + 1;        // select one at random
                                counter = DEFAULT;
                                
                                for (int n = 0; n < size; n++)
                                {
                                    for (int o = 0; o < size; o++)
                                    {
                                        if (myBoard.isOpen(n, o))
                                        {
                                            counter++;                  // iterate through available spaces until the selected one is reached
                                            if (counter == selector)
                                            {
                                                selectedRow = n;        // record the row & column of the selected space
                                                selectedCol = o;
                                            }
                                        }
                                    }
                                }
                            }                            
                        }
                        
                        // 5x5
                        else
                        {
                            for (int k = 0; k < (size - 3); k++)                // for a 5x5 board
                            {
                                for (int l = (k + 1); l < (size - 2); l++)
                                {
                                    for (int m = (l + 1); m < (size - 1); m++)
                                    {
                                        for (int n = (m + 1); n < size; n++)
                                        {
                                            if ((j != k) && (j != l) && (j != m) && (j != n) && myBoard.isEqual(i, k, i, l) && myBoard.isEqual(i, l, i, m) 
                                                && myBoard.isEqual(i, m, i, n) && !myBoard.isOpen(i, k))           // checking row
                                            {
                                                con1 = (myBoard.isEqual(i, k, i, l) && myBoard.isEqual(i, l, i, m) && myBoard.isEqual(i, m, i, n) 
                                                        && !myBoard.isOpen(i, k));
                                                System.out.println("con1 is " + con1);
                                                selectedRow = i;
                                                selectedCol = j;
                                                counter++;
                                            }
                                            if ((i != k) && (i != l) && (i != m) && (i != n) && myBoard.isEqual(k, j, l, j) && myBoard.isEqual(l, j, m, j) 
                                                && myBoard.isEqual(m, j, n, j) && !myBoard.isOpen(k, j))           // checking col
                                            {
                                                con2 = (myBoard.isEqual(k, j, l, j) && myBoard.isEqual(l, j, m, j) && myBoard.isEqual(m, j, n, j) 
                                                        && !myBoard.isOpen(k, j));
                                                System.out.println("con2 is " + con2);
                                                selectedRow = i;
                                                selectedCol = j;
                                                counter++;
                                            }
                                            if ((i == j) && (i != k) && (i != l) && (i != m) && (i != n) && myBoard.isEqual(k, k, l, l) 
                                                && myBoard.isEqual(l, l, m, m) && myBoard.isEqual(m, m, n, n) && !myBoard.isOpen(k, k))  // checking diag 
                                            {
                                                con3 = (myBoard.isEqual(k, k, l, l) && myBoard.isEqual(l, l, m, m) && myBoard.isEqual(m, m, n, n) 
                                                        && !myBoard.isOpen(k, k));
                                                System.out.println("con3 is " + con3);
                                                selectedRow = i;
                                                selectedCol = j;
                                                counter++;
                                            }
                                            if (((i + j) == (size - 1)) && (i != k) && (i != l) && (i != m) && (i != n) 
                                                && myBoard.isEqual(k, (size - 1) - k, l, (size - 1) - l) && myBoard.isEqual(l, (size - 1) - l, m, 
                                                (size - 1) - m) && myBoard.isEqual(m, (size - 1) - m, n, (size - 1) - n) 
                                                && !myBoard.isOpen(k, (size - 1) - k))
                                            {
                                                con4 = (myBoard.isEqual(k, (size - 1) - k, l, (size - 1) -l) && myBoard.isEqual(l, (size - 1) - l, 
                                                        m, (size - 1) - m) && myBoard.isEqual(m, (size - 1) - m, n, (size - 1) - n) 
                                                        && !myBoard.isOpen(k, (size - 1) - k));
                                                System.out.println("con4 is " + con4);
                                                selectedRow = i;
                                                selectedCol = j;
                                                counter++;
                                            }
                                        }
                                    }
                                }
                            }
                            if (adjSpace == myBoard.OPEN)             // if no 2 in a row is found, choose randomly
                            {
                                counter = DEFAULT;
                                
                                // for (int i = 0; i < size; i++)
                                // {
                                //     for (int j = 0; j < size; j++)
                                //     {
                                //         if (myBoard.isOpen(i, j))
                                //             counter++;                  // counting # of available spaces
                                //     }
                                // }
                                
                                selector = ((int)(myBoard.numOpen() * Math.random())) + 1;        // select one at random
                                counter = DEFAULT;
                                
                                for (int o = 0; o < size; o++)
                                {
                                    for (int p = 0; p < size; p++)
                                    {
                                        if (myBoard.isOpen(o, p))
                                        {
                                            counter++;                  // iterate through available spaces until the selected one is reached
                                            if (counter == selector)
                                            {
                                                selectedRow = o;        // record the row & column of the selected space
                                                selectedCol = p;
                                            }
                                        }
                                    }
                                }
                            }                            
                        }
                    }
                }
            }
            
            // strategy 1: First to move, picks center
            // strategy 2: Second to move, opponent does not pick center
            // strategy 3: Second to move, opponent picks center
            
            // for 3x3 board size, strategy for hard difficulty
            if (size == myBoard.DEFAULT_SIZE)
            {
                if (adjSpace == myBoard.OPEN)       // if no 2 in a row is found, follow the strategy
                {
                    // first, check if middle is occupied (this determines which strategy to follow)
                    // if it isn't occupied, choose it, and become the first player (strategy 1)
                    // if it is, then see who occupies it in order to determine which strategy to follow
                    if ((myBoard.numOpen() % 2) == 1)       // if odd # of spaces left, cpu is first to go
                        first = true;
                    
                    System.out.println("path 1 is " + path1 + ", and path 2 is " + path2);
                    if (myBoard.isOpen(1, 1))               // if middle space is open, take it
                    {
                        System.out.println("Checkpoint 3");
                        selectedRow = 1;
                        selectedCol = 1;
                    }
                    
                    // System.out.println("path 1 is " + path1 + ", and path 2 is " + path2);
                    // strategy 1
                    else if (first && (myBoard.getSpace(1, 1) == symbol))     // needs work... what if first player does not pick middle at first, but 
                    {                                                       // later on?
                        System.out.println("taking path 1");
                        // first, check if opponent chose a side or corner
                        if ((((!myBoard.isOpen(0, 1)) || (!myBoard.isOpen(1, 0)) || (!myBoard.isOpen(1, 2)) || (!myBoard.isOpen(2, 1))) 
                             && (myBoard.numOpen() == 7)) || myBoard.numOpen() == 5)
                        {
                            // if side, do tri method to win
                            if (myBoard.numOpen() == 7)     // first part
                            {
                                System.out.println("Tri method!");
                                selector = ((int)(4 * Math.random())) + 1;        // select a corner at random
                                if (selector == 1)
                                {
                                    selectedRow = 0;
                                    selectedCol = 0;
                                    System.out.println("selecting 0,0 part 1");
                                }
                                else if (selector == 2)
                                {
                                    selectedRow = 0;
                                    selectedCol = 2;
                                    System.out.println("selecting 0,2 part 1");
                                }
                                else if (selector == 3)
                                {
                                    selectedRow = 2;
                                    selectedCol = 0;
                                    System.out.println("selecting 2,0 part 1");
                                }
                                else
                                {
                                    selectedRow = 2;
                                    selectedCol = 2;
                                    System.out.println("selecting 2,2 part 1");
                                }
                            }
                            else        // second part
                            {
                                if (((myBoard.getSpace(0, 0) == symbol) && (myBoard.isOpen(0, 1))) || ((myBoard.getSpace(2, 2) == symbol) 
                                    && (myBoard.isOpen(1, 2))))
                                {
                                    selectedRow = 0;
                                    selectedCol = 2;
                                    System.out.println("selecting 0,2 part 2");
                                }
                                else if (((myBoard.getSpace(0, 2) == symbol) && (myBoard.isOpen(1, 2))) || ((myBoard.getSpace(2, 0) == symbol) 
                                    && (myBoard.isOpen(2, 1))))
                                {
                                    selectedRow = 2;
                                    selectedCol = 2;
                                    System.out.println("selecting 2,2 part 2");
                                }
                                else if (((myBoard.getSpace(2, 2) == symbol) && (myBoard.isOpen(2, 1))) || ((myBoard.getSpace(0, 0) == symbol) 
                                    && (myBoard.isOpen(1, 0))))
                                {
                                    selectedRow = 2;
                                    selectedCol = 0;
                                    System.out.println("selecting 2,0 part 2");
                                }
                                else
                                {
                                    selectedRow = 0;
                                    selectedCol = 0;
                                    System.out.println("selecting 0,0 part 2");
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Human chose corner, this part is incomplete...");
                        }
                    }
                    // strategy 2
                    else if (!first && (myBoard.getSpace(1, 1) == symbol)) // CPU is not first player and middle space is taken by CPU.
                    {
                        System.out.println("Strategy 2? This is for if cpu is not first player. This part is incomplete...");
                    }
                    
                    // strategy 3
                    else                                                    // Middle space is not taken by CPU
                    {   // checking for special cases first
                        // side technique
                        // note: only works if the first move is on a side instead of the corner, which is a bad move...
                        // if ((myBoard.getSpace(0, 1) == symbol) && (myBoard.getSpace(1, 0) == symbol) && (myBoard.isOpen(0, 0)) 
                        //     && (myBoard.isOpen(0, 2)) && (myBoard.isOpen(2, 0)))
                        // {
                        //     selectedCol = 0;
                        //     selectedRow = 0;
                        //     chosen = true;
                        // }
                        // else if ((myBoard.getSpace(0, 1) == symbol) && (myBoard.getSpace(1, 2) == symbol) && (myBoard.isOpen(0, 2)) 
                        //          && (myBoard.isOpen(0, 0)) && (myBoard.isOpen(2, 2)))
                        // {
                        //     selectedCol = 2;
                        //     selectedRow = 0;
                        //     chosen = true;
                        // }
                        // else if ((myBoard.getSpace(1, 2) == symbol) && (myBoard.getSpace(2, 1) == symbol) && (myBoard.isOpen(2, 2)) 
                        //          && (myBoard.isOpen(0, 2)) && (myBoard.isOpen(2, 0)))
                        // {
                        //     selectedCol = 2;
                        //     selectedRow = 2;
                        //     chosen = true;
                        // }
                        // else if ((myBoard.getSpace(2, 1) == symbol) && (myBoard.getSpace(1, 0) == symbol) && (myBoard.isOpen(2, 0)) && (myBoard.isOpen(2, 2)) 
                        //          && (myBoard.isOpen(0, 0)))
                        // {
                        //     selectedCol = 0;
                        //     selectedRow = 2;
                        //     chosen = true;
                        // }
                        // L technique (one version)        // happens automatically from blocking
                        // else if (((myBoard.getSpace(0, 0) == symbol) && (myBoard.getSpace(2, 0) == symbol) && (myBoard.isOpen(1, 0)) && (myBoard.isOpen(2, 1)) && (myBoard.isOpen(2, 2))) || ((myBoard.getSpace(0, 2) == symbol) && (myBoard.getSpace(2, 2) == symbol) && (myBoard.isOpen(1, 2)) && (myBoard.isOpen(2, 1)) && (myBoard.isOpen(2, 0))))
                        // {
                        //     selectedCol = 1;
                        //     selectedRow = 2;
                        //     chosen = true;
                        // }
                        // else if (((myBoard.getSpace(0, 2) == symbol) && (myBoard.getSpace(0, 0) == symbol) && (myBoard.isOpen(0, 1)) && (myBoard.isOpen(1, 0)) && (myBoard.isOpen(2, 0))) || ((myBoard.getSpace(2, 2) == symbol) && (myBoard.getSpace(2, 0) == symbol) && (myBoard.isOpen(2, 1)) && (myBoard.isOpen(1, 0)) && (myBoard.isOpen(0, 0))))
                        // {
                        //     selectedCol = 0;
                        //     selectedRow = 1;
                        //     chosen = true;
                        // }
                        // else if (((myBoard.getSpace(2, 2) == symbol) && (myBoard.getSpace(0, 2) == symbol) && (myBoard.isOpen(1, 2)) && (myBoard.isOpen(0, 1)) && (myBoard.isOpen(0, 0))) || ((myBoard.getSpace(2, 0) == symbol) && (myBoard.getSpace(0, 0) == symbol) && (myBoard.isOpen(1, 0)) && (myBoard.isOpen(0, 1)) && (myBoard.isOpen(0, 2))))
                        // {
                        //     selectedCol = 1;
                        //     selectedRow = 0;
                        //     chosen = true;
                        // }
                        // else if (((myBoard.getSpace(2, 0) == symbol) && (myBoard.getSpace(2, 2) == symbol) && (myBoard.isOpen(2, 1)) && (myBoard.isOpen(1, 2)) && (myBoard.isOpen(0, 2))) || ((myBoard.getSpace(0, 0) == symbol) && (myBoard.getSpace(0, 2) == symbol) && (myBoard.isOpen(0, 1)) && (myBoard.isOpen(1, 2)) && (myBoard.isOpen(2, 2))))
                        // {
                        //     selectedCol = 2;
                        //     selectedRow = 1;
                        //     chosen = true;
                        // }
                        System.out.println("Strategy 3...");
                        if (!chosen)
                        {
                            // if all 4 corners are open, choose one at random
                            if (myBoard.isOpen(0, 0) && myBoard.isOpen(0, 2) && myBoard.isOpen(2, 0) && myBoard.isOpen(2, 2))
                            {
                                selector = ((int)(4 * Math.random())) + 1;        // select a corner at random
                                System.out.println("corner selected: " + selector);
                                if (selector == 1)
                                {
                                    selectedRow = 0;
                                    selectedCol = 0;
                                }
                                else if (selector == 2)
                                {
                                    selectedRow = 0;
                                    selectedCol = 2;
                                }
                                else if (selector == 3)
                                {
                                    selectedRow = 2;
                                    selectedCol = 0;
                                }
                                else
                                {
                                    selectedRow = 2;
                                    selectedCol = 2;
                                }
                            }
                            else if (myBoard.numOpen() == 6)        // select a winning corner (path 1)
                            {
                                // finding the 2 open corners
                                path1B = true;
                                if (myBoard.isOpen(0, 0))
                                {
                                    row1 = 0; 
                                    col1 = 0;
                                }
                                else if (myBoard.isOpen(0, 2))
                                {
                                    row1 = 0;
                                    col1 = 2;
                                }
                                else if (myBoard.isOpen(2, 0))
                                {
                                    row1 = 2;
                                    col1 = 0;
                                }
                                else
                                {
                                    row1 = 2;
                                    col1 = 2;
                                }
                                if (myBoard.isOpen(2, 2))
                                {
                                    row2 = 2;
                                    col2 = 2;
                                }
                                else if (myBoard.isOpen(2, 0))
                                {
                                    row2 = 2;
                                    col2 = 0;
                                }
                                else if (myBoard.isOpen(0, 2))
                                {
                                    row2 = 0;
                                    col2 = 2;
                                }
                                else
                                {
                                    row2 = 0;
                                    col2 = 0;
                                }
                                
                                selector = ((int)(2 * Math.random())) + 1;        // select a corner at random
                                if (selector == 1)
                                {
                                    selectedRow = row1;
                                    selectedCol = col1;
                                }
                                else
                                {
                                    selectedRow = row2;
                                    selectedCol = col2;
                                }
                            }
                            else if ((myBoard.numOpen() == 2) && path1B)        // select a side at random 
                            {                                                   // (path 1 continued)
                                if (myBoard.isOpen(0, 1))
                                {
                                    row1 = 0;
                                    col1 = 1;
                                    row2 = 2;
                                    col2 = 1;
                                }
                                else
                                {
                                    row1 = 1;
                                    col1 = 0;
                                    row2 = 1;
                                    col2 = 2;
                                }
                                
                                selector = ((int)(2 * Math.random())) + 1;        // select a side at random
                                if (selector == 1)
                                {
                                    selectedRow = row1;
                                    selectedCol = col1;
                                }
                                else
                                {
                                    selectedRow = row2;
                                    selectedCol = col2;
                                }
                            }
                            else if (myBoard.numOpen() == 2)        // select a corner or side at random (path 2A) & (path 3) & (paht 4)
                            {
                                if ((myBoard.isOpen(0, 0)) || (myBoard.isOpen(0, 2)) || (myBoard.isOpen(2, 0)) || (myBoard.isOpen(2, 2)))
                                {
                                    if (myBoard.isOpen(0, 0))
                                    {
                                        row1 = 0; 
                                        col1 = 0;
                                        row2 = 2;
                                        col2 = 2;
                                    }
                                    else if (myBoard.isOpen(0, 2))
                                    {
                                        row1 = 0;
                                        col1 = 2;
                                        row2 = 2;
                                        col2 = 0;
                                    }
                                    else if (myBoard.isOpen(2, 0))
                                    {
                                        row1 = 2;
                                        col1 = 0;
                                        row2 = 0;
                                        col2 = 2;
                                    }
                                    else
                                    {
                                        row1 = 2;
                                        col1 = 2;
                                        row2 = 0;
                                        col2 = 0;
                                    }
                                    
                                    selector = ((int)(2 * Math.random())) + 1;        // select a corner at random
                                    if (selector == 1)
                                    {
                                        selectedRow = row1;
                                        selectedCol = col1;
                                    }
                                    else
                                    {
                                        selectedRow = row2;
                                        selectedCol = col2;
                                    }
                                }
                                else
                                {
                                    if (myBoard.isOpen(0, 1))
                                    {
                                        row1 = 0;
                                        col1 = 1;
                                        row2 = 2;
                                        col2 = 1;
                                    }
                                    else
                                    {
                                        row1 = 1;
                                        col1 = 0;
                                        row2 = 1;
                                        col2 = 2;
                                    }
                                    
                                    selector = ((int)(2 * Math.random())) + 1;        // select a side at random
                                    if (selector == 1)
                                    {
                                        selectedRow = row1;
                                        selectedCol = col1;
                                    }
                                    else
                                    {
                                        selectedRow = row2;
                                        selectedCol = col2;
                                    }
                                }
                            }
                            else if(myBoard.numOpen() == 4)     // select a side or corner at random
                            {                                   // (path 2D)
                                if (myBoard.getSpace(0, 0) == symbol)
                                {
                                    if (myBoard.isOpen(0, 1))
                                    {
                                        row1 = 0;
                                        col1 = 1;
                                        row2 = 0;
                                        col2 = 2;
                                    }
                                    else
                                    {
                                        row1 = 1;
                                        col1 = 0;
                                        row2 = 2;
                                        col2 = 0;
                                    }
                                }
                                else if (myBoard.getSpace(0, 2) == symbol)
                                {
                                    if (myBoard.isOpen(1, 2))
                                    {
                                        row1 = 1;
                                        col1 = 2;
                                        row2 = 2;
                                        col2 = 2;
                                    }
                                    else
                                    {
                                        row1 = 0;
                                        col1 = 1;
                                        row2 = 0;
                                        col2 = 0;
                                    }
                                }
                                else if (myBoard.getSpace(2, 0) == symbol)
                                {
                                    if (myBoard.isOpen(1, 0))
                                    {
                                        row1 = 1;
                                        col1 = 0;
                                        row2 = 0;
                                        col2 = 0;
                                    }
                                    else
                                    {
                                        row1 = 2;
                                        col1 = 1;
                                        row2 = 2;
                                        col2 = 2;
                                    }
                                }
                                else
                                {
                                    if (myBoard.isOpen(2, 1))
                                    {
                                        row1 = 2;
                                        col1 = 1;
                                        row2 = 2;
                                        col1 = 0;
                                    }
                                    else
                                    {
                                        row1 = 1;
                                        col1 = 2;
                                        row2 = 0;
                                        col2 = 2;
                                    }
                                }
                                
                                selector = ((int)(2 * Math.random())) + 1;        // select a side or corner at random
                                if (selector == 1)
                                {
                                    selectedRow = row1;
                                    selectedCol = col1;
                                }
                                else
                                {
                                    selectedRow = row2;
                                    selectedCol = col2;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("selectedRow = " + selectedRow);
            System.out.println("selectedCol = " + selectedCol);
        }
    }
    
    public String toString()
    {
        String result = super.toString();
        result += ", Difficulty = " + difficulty + ", Selected row = " + selectedRow + ", Selected col = " + selectedCol;
        return result;
    }
}

