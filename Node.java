/**
 * Tori Windrich
 * 4/17/2018
 * Project 5: Tie Fighter Patrols With Graphs
 */
package TieFighter;

public class Node <E>
{
    private boolean isStartVertex; //if this is true, there will be a number and no weight
    private int vertexNumber; //this is the number of the vertex that the edge represents connection to,
                              //or in the case of it being a start vertex, this is the number of that start vertex
 
    private int weight; //this is the weight of the edge to this vertexNumber, is zero and not looked at if it's a StartVertex
    
    /**
     * Creates a new node with the passed in number, weight, and indicator.
     * 
     * @param v vertex number
     * @param w weight of vertex
     * @param s whether or not it's a start vertex
     */
    public Node(int v, int w, boolean s)
    {
        vertexNumber = v;
        weight = w;
        isStartVertex = s;
    }
    
    /**
     * Creates a new node with default values 0.
     */
    public Node()
    {
        vertexNumber = 0;
        weight = 0;
    }
    
    /**
     * Returns whether or not the vertex is a start vertex.
     * 
     * @return isStartVertex
     */
    public boolean isStart()
    {
        return isStartVertex;
    }
    
    /**
     * Returns the vertex number.
     * 
     * @return vertexNumber
     */
    public int getVertexNumber()
    {
        return vertexNumber;
    }
    
    /**
     * Returns the node's weight.
     * 
     * @return weight
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Sets the vertex number.
     * 
     * @param n the new vertex number
     */
    public void setVertextNumber(int n)
    {
        vertexNumber = n;
    }
    
    /**
     * Sets the weight.
     * 
     * @param w the new weight
     */
    public void setWeight(int w)
    {
        weight = w;
    }
    
    /**
     * Sets whether this is a start vertex.
     * 
     * @param b the new indicator.
     */
    public void setIsStart(boolean b)
    {
        isStartVertex = b;
    }
    
    /**
     * Overridden equals function, if the passed in object is a Node, returns
     * whether the the vertex numbers equal each other.
     * 
     * @param o the object being compared
     * @return true if vertex numbers equal
     */
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Node)
        {
            return (((Node)o).vertexNumber == vertexNumber);
        }
        return false;
    }
}
