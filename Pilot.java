/**
 * Tori Windrich
 * 4/17/2018
 * Project 5: Tie Fighter Patrols With Graphs
 */
package TieFighter;

public class Pilot implements Comparable
{
    private String name;
    private int pathLength;
    
    /**
     * Creates a new Pilot with the passed in name.
     * 
     * @param n 
     */
    public Pilot(String n)
    {
        n = name;
        pathLength = 0;
    }
    
    /**
     * Creates a new Pilot with an empty name.
     */
    public Pilot()
    {
        name = "";
        pathLength = 0;
    }
    
    /**
     * Sets the name to the passed in string.
     * 
     * @param n the new name
     */
    public void setName(String n)
    {
        name = n;
    }
    
    /**
     * Sets the path length to the passed in int.
     * 
     * @param l the new path length
     */
    public void setPathLength(int l)
    {
        pathLength = l;
    }
    
    /**
     * Returns the name.
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the path length.
     * 
     * @return pathLength
     */
    public int getPathLength()
    {
        return pathLength;
    }
    
    /**
     * Returns a positive number if the calling Pilot is bigger, 0 if they are the same,
     * and a negative number if the calling Pilot is smaller.
     * 
     * @param o the object being compared
     * @return the difference in path lengths or -1
     */
    @Override
    public int compareTo(Object o)
    {
        //if the object is a pilot
        if (o instanceof Pilot)
        {
            return pathLength - ((Pilot)o).pathLength;
        }
        return -1;
    }
}
