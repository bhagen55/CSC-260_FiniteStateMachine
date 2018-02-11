package proj2.document;

import java.util.LinkedList; 


public class Vertex{

	String symbol;
	Boolean isAcceptState;
	private LinkedList<Edge> edges;

	public Vertex(String givenSymbol)
	{
		isAcceptState=false;
		symbol=givenSymbol;
		edges= new LinkedList<Edge>();
	}

	    /**
     * Adds a directed edge between two vertices.  If there is already an edge
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the edge is created between them.
     *
     * @param from the source vertex for the added edge
     * @param to the destination vertex for the added edge
     */
    public void addEdge(String to, String edgeWeight)
    {
    }

    public void removeEdge(String to, String edgeWeight)
    {
    }


   	public boolean hasEdge(String to, String edgeWeight)
   	{
   		return false;
   	}



    public int numEdges()
    {
        return -1;
    }

    public boolean canAccept()
    {
    	return isAcceptState;
    }

    public Vertex goTo(String edgeWeight)
    {
    	return null;
    }


}