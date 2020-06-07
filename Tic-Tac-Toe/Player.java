// sirdaniel711
// Player.java
//
// This program represents a generic player

public class Player
{
    public static final String DEFAULT_NAME = "Player";		// default name
    
    private String name;					// name of player

    public Player()						// default name is used if one is not provided
    {
        name = DEFAULT_NAME;					
    }

    public Player(String name)					// for if a name is given
    {
        this.name = name;
    }

    public String getName()					// returns name of player
    {
        return name;
    }

    public void setName(String name)				// changes the players name
    {
        this.name = name;
    }

    public String toString()					// returns the players name as a string
    {
        return name;
    }
}
