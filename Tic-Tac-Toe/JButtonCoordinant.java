// sirdaniel711
// JButtonCoordinant.java
//
// This program extends from JButton (attempt), with the added capability of having coordinants.

import javax.swing.*;

public class JButtonCoordinant extends JButton
{
    private int myRow;
    private int myCol;

    public JButtonCoordinant()
    {
        super();
        myRow = 0;
        myCol = 0;
    }

    public JButtonCoordinant(Action a)
    {
        super(a);
        myRow = 0;
        myCol = 0;
    }

    public JButtonCoordinant(Icon icon)
    {
        super(icon);
        myRow = 0;
        myCol = 0;
    }

    public JButtonCoordinant(String text)
    {
        super(text);
        myRow = 0;
        myCol = 0;
    }

    public JButtonCoordinant(String text, Icon icon)
    {
        super(text, icon);
        myRow = 0;
        myCol = 0;
    }

    public JButtonCoordinant(int row, int col)
    {
        super();
        myRow = row;
        myCol = col;
    }

    public JButtonCoordinant(Action a, int row, int col)
    {
        super(a);
        myRow = row;
        myCol = col;
    }

    public JButtonCoordinant(Icon icon, int row, int col)
    {
        super(icon);
        myRow = row;
        myCol = col;
    }

    public JButtonCoordinant(String text, int row, int col)
    {
        super(text);
        myRow = row;
        myCol = col;
    }

    public JButtonCoordinant(String text, Icon icon, int row, int col)
    {
        super(text, icon);
        myRow = row;
        myCol = col;
    }

    public int getXCoordinant()
    {
        return myRow;
    }

    public int getYCoordinant()
    {
        return myCol;
    }
}