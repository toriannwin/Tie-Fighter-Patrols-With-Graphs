/**
 * Tori Windrich
 * 4/17/2018
 * Project 5: Tie Fighter Patrols With Graphs
 */
package TieFighter;

import java.util.LinkedList;


public class Graph 
{
    private int size; //max size of the graph
    private LinkedList<Node> [] adjList; //the adjacency list of the graph
    private int filled; //filled spots of the graph
    
    /**
     * Creates a graph with the passed in size s as the max size.
     * 
     * @param s max size
     */
    public Graph(int s)
    {
        size = s; //sets max size
        adjList = new LinkedList[s]; //creates the adjacency list
        
        //creates empty linked lists for the adjacency list
        for(int i = 0; i < s; i++)
        {
            adjList[i] = new LinkedList();
        }
        //marks that none of the vertices exist yet
        filled = 0;
    }
    
    /**
     * Creates a graph with the default size 10.
     */
    public Graph()
    {
        size = 10; //sets max size
        adjList = new LinkedList[10]; //creates the adjacency list
        
        //creates empty linked lists for the adjacency list
        for(int i = 0; i < 10; i++)
        {
            adjList[i] = new LinkedList();
        }
        //marks that none of the vertices exist yet
        filled = 0;
    }
    
    /**
     * Returns the max number of vertices of the graph.
     * 
     * @return size
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * Returns the current number of vertices of the graph.
     * 
     * @return filled
     */
    public int getCurrentSize()
    {
        return filled;
    }
    
    /**
     * Returns whether or not the graph is currently empty.
     * 
     * @return if filled == 0
     */
    public boolean isEmpty()
    {
        return filled == 0;
    }
    
    /**
     * Inserts a new edge into the graph.
     * 
     * @param startVertex is the vertex in the adjacency list that will gain an edge
     * @param vertex is the vertex that will be added to the start vertex linked list of edges
     * @param weight is the weight of the edge between the start and end vertex
     */
    public void insertEdge(int startVertex, int vertex, int weight)
    {
        int index = 0;
        boolean foundIndex = false;
        
        //while we have not found the vertex number we are looking for, and
        //while we haven't gone out of bounds of the adjacency list
        while(index < filled && !foundIndex)
        {
            //if the vertex of the adjacency list is equal to the vertex we are searching for
            //indicate so with foundIndex. Otherwise, increment index
            if(adjList[index].get(0).getVertexNumber() == startVertex)
                foundIndex = true;
            else
                index++;
        }
        //if we went out of bounds and the start vertex is not in the graph, leave the method
        if(!foundIndex)
            return;
        //otherwise, add a new node to the start vertex linked list of edges
        //indicating the number, weight, and that it is not a start vertex
        adjList[index].add(new Node(vertex,weight,false));
    }
    
    /**
     * Inserts a new vertex into the graph.
     * 
     * @param vertex to be added to the adjacency list
     */
    public void insertVertex(int vertex)
    {
        //as long as there is still space left in the graph's adjacency list
        if(filled < size)
        {
            //at the next empty position in the adjacency list, add a new node to the empty linked list
            //indicating its number, the weight of zero, and that it is a start vertex
            adjList[filled].add(new Node(vertex,0,true));
            //increment filled to show addition of another vertex
            filled++;
        }    
    }
    
    /**
     * Returns the vertex at the passed in index of the adjacency list.
     * 
     * @param num is the index being looked for
     * @return the node that represents the vertex at the passed in index
     */
    public Node getVertexAtIndex(int i)
    {
        int index = 0;
        
        //while the index is in the bounds of the filled list and not equal to the passed in index
        //increment the index
        while(index < filled && index < i)
        {
            index++;
        }
        //if we are still in bounds, return the head node of the linked list at the index
        if(index < filled)
            return adjList[index].getFirst();
        else
            return null;
    }
    
    /**
     * Returns the vertex Node with the passed in vertex number.
     * 
     * @param num is the vertex number being search for
     * @return the node with the vertex number
     */
    public Node getVertex(int num)
    {
        int index = 0;
        
        //while still in the list and the vertex hasn't been found, increment index
        while(index < filled && adjList[index].getFirst().getVertexNumber() != num)
        {
            index++;
        }
        //if we are still in bounds, return the vertex at the found index
        if(index < filled)
            return adjList[index].getFirst();
        else
            return null;
    }
    
    /**
     * Returns the index of the passed in Node.
     * 
     * @param vertex is the node being search for.
     * @return the index of the vertex or -1.
     */
    public int getIndexOfVertex(Node vertex)
    {
        int index = 0;
        
        //while still in bounds and the vertex number still doesn't equal the passed in vertex
        //increment index
        while(index < filled && !adjList[index].getFirst().equals(vertex))
        {
            index++;
        }
        //if we went out of bounds, return -1, or return the found index
        if(index == filled)
            return -1;
        else
            return index;
    }
    
    /**
     * Returns the linked list of neighbors of a passed in vertex number.
     * 
     * @param num is the vertex number being searched for
     * @return the linked list that starts with a node with the vertex number passed in
     */
    public LinkedList<Node> getNeighbors(int num)
    {
        int index = 0;
        
        //while still in bounds and while the vertex number doesn't equal the passed in number
        //increment index
        while(index < filled && adjList[index].getFirst().getVertexNumber() != num)
        {
            index++;
        }
        //if we went out of bounds, return null, otherwise, return the linked list at the index
        if (index == filled)
            return null;
        return adjList[index];
    }
    
    /**
     * Calls the DFS method with the first vertex in the list.
     * (Basically a default DFS call)
     */
    public void DFS()
    {
        DFS(adjList[0].getFirst());
    }
    
    /**
     * The helper function for the DFSRecursive, which performs a depth first
     * traversal of the graph.
     * 
     * @param vertex 
     */
    public void DFS(Node vertex)
    {
        //creates list of booleans and call the recursive DFS function
        boolean [] visited = new boolean[size];
        DFSRecursive(vertex,visited);
    }
    
    /**
     * Performs a depth first traversal on the graph, marking each vertex as
     * visited and visiting every vertex in the graph.
     * 
     * @param vertex is the current vertex being visited
     * @param visited is the array indicating which vertices have been visited
     */
    public void DFSRecursive(Node vertex, boolean[] visited)
    {
        //getting the index of the passed in vertex, if it's not negative one
        //mark that position as visited
        int index = getIndexOfVertex(vertex);
        if(index != -1)
            visited[index] = true;
        else
            return;
        Node next;
        
        //for each edge in the linked list
        for(int i = 0; i < adjList[index].size(); i++)
        {
            //get the next node in the linked list
            next = adjList[index].get(i);
            //if the node has not been visited, call DFSRecursive to visit that node
            if(!visited[getIndexOfVertex(next)])
                DFSRecursive(next,visited);
        }
    }
    
    /**
     * Determines if there is a connection from the first to the second vertex number passed in.
     * 
     * @param start is the starting vertex number
     * @param end is the ending vertex number
     * @return whether or not there is a connection from start to end
     */
    public boolean hasConnection(int start, int end)
    {
        //get the neighbors for the passed in vertex number
        LinkedList<Node> neighbors = getNeighbors(start);
        //if those neighbors exist
        if(neighbors != null)
        {
            int index = 0;
            
            //while in the list, search for the end vertex, if it's found, return true
            while(index < neighbors.size())
            {
                if(neighbors.get(index).getVertexNumber() == end)
                    return true;
                index++;
            }
        }
        //end vertex was not found, return false
        return false;
    }
    
    /**
     * Returns the weight between the start and end vertex number.
     * 
     * @param start is the starting vertex number
     * @param end is the ending vertex number
     * @return the weight of the end vertex
     */
    public int getWeight(int start, int end)
    {
        //get the neighbors for the start vertex
        LinkedList<Node> neighbors = getNeighbors(start);
        //as long as those neighbors exist
        if(neighbors != null)
        {
            int index = 0;
            //while we are still in bounds
            while(index < neighbors.size())
            {
                //if the end vertex is found, return its weight
                if(neighbors.get(index).getVertexNumber() == end)
                    return neighbors.get(index).getWeight();
                index++;
            }
        }
        //end vertex wasn't found, return -1
        return -1;
    }
}
