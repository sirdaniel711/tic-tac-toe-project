// sirdaniel711
//
// BoolTool.java
// The purpose of this class is to serve as a useful tool.
// This tool represents an array of booleans 

public class BoolTool
{
    public static final int DEFAULT_SIZE = 3;
    public static final int MAX_SIZE = 100;         // no larger than 100 booleans
    
    private boolean[] var;
    
    public void BoolTool()                          // initially an array of size 3 all set to false
    {
        var = new boolean[DEFAULT_SIZE];
        for (int i = 0; i < var.length; i++)
            var[i] = false;
    }
    
    public void BoolTool(int size)
    {
        if ((size < MAX_SIZE) && (size > 0))
        {
            var = new boolean[size];
            for (int i = 0; i < var.length; i++)
                var[i] = false;
        }
    }

    public int getSize()                            // getter (accessor)
    {
        return var.length;
    }

    public boolean isTrue(int index)
    {
        if ((index >= 0) && (index < var.length))
            return var[index];
        else
            return false;
    }
    
    public void setTrue(int index, boolean bool)    // setter (mutator)
    {
        if ((index >= 0) && (index < var.length))
            var[index] = bool;
    }

    public void resetBool()
    {
        for (int i = 0; i < var.length; i++)
            var[i] = false;
    }

    public String toString()
    {
        String result = "Boolean Variable of size " + var.length + ": \n";
        for (int i = 0; i < var.length; i++)
            result = result + i + ": " + var[i] + ", ";
        return result;
    }
}