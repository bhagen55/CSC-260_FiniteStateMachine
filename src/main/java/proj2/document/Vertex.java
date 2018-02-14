package proj2.document;

import java.util.ArrayList;

public class Vertex{

	private String symbol; //name of this Vertex
	private Boolean isAcceptState; //returns true if this is an accept state otherwise false
	private int numEdges=0;  // number of edges in this vertex
    private int xCoord; // xCoordinate but does not need to be initialized
    private int yCoord; // yCoordinate but does not need to be initialized
    private ArrayList<Edge> edges; // ArrayList containing Edges


	/**
	* Constructor for vertex that takes a string to be the name of the vertex
	* @param givenSymbol: String to be the name of the Vertex
	*/
	public Vertex(String givenSymbol)
	{
		isAcceptState=false;
		symbol=givenSymbol;
		edges = new ArrayList();
	}

	 /*
     * Adds a directed edge between two vertices.  If there is already an edge
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the edge is created between them.
     *
     * @param from the source vertex for the added edge
     * @param to the destination vertex for the added edge
     */
    public void addEdge(Vertex to, String edgeWeight)
    {
		edges.add(new Edge(to,edgeWeight));
		numEdges++;
    }

    /**
    * Adds or alters coordinates of this vertex
    *@param x x value of coordinate
    *@param y y value of coordinate
    */
    public void addCoordinates(int x, int y)
    {
        xCoord=x;
        yCoord=y;
    }

    /**
    *returns x coordinate
    *@return x coordinate of this vertex
    */
    public int getX()
    {
        return xCoord;
    }

    /**
    *returns y coordinate
    *@return y coordinate of this vertex
    */
    public int getY()
    {
         return yCoord;
    }

    /**
    *gets the edge to a given String with a given weight should it exist
    *@param to of the Symbol that the edge should go to
    *@param edgeWeight weight ot edge your looking for
    *@return Edge with the symbol to and Strign edgeWeight
    */
	private Edge findEdge(String to, String edgeWeight) {

		for (Edge currEdge:edges) {
			if (currEdge.getWeight().equals(edgeWeight) &&
				currEdge.getGoingTo().equals(to)) {
					return currEdge;
			}
		}

		return null;
	}

    /**
    *removes an edge with Symbol to and weight edgeweight
    *@param to the name of the vertex the edge to remove goes to
    *@param edgeWeight weight of the edge to be removed
    */
    public void removeEdge(String to, String edgeWeight)
    {
		Edge toRemove = findEdge(to, edgeWeight);
    	if (toRemove != null) {
			edges.remove(toRemove);
			numEdges--;
		}
    }


    /**
    *returns if a desired edge exists
    *@param to: the name of the vertex the edge to remove goes to
    *@param edgeWeight: weight of the edge to be removed
    *@return true if a certain edge exists false otherwise
    */
   	public boolean hasEdge(String to, String edgeWeight)
   	{
		if (findEdge(to, edgeWeight) != null) {
			return true;
		} else {
			return false;
		}
   	}


    /**
    *returns the number of edges
    *@return the number of edges in this vertex
    */
    public int numEdges()
    {
        return numEdges;
    }

    /**
    *@return true if this is an accept state false otherwise
    */
    public boolean canAccept()
    {
    	return isAcceptState;
    }

    /**
    *if vertex is an accept state makes
    *it not an accept state and if it is not an accept state
    *the vertex becomes an accept state
    */
	public void toggleAccept()
	{
		isAcceptState = !isAcceptState;
	}

    /**
    *@return the name of this vertex
    */
    public String getName()
    {
    	return symbol;
    }

    /**
    * @return the edges in this vertex
    */
    public ArrayList<Edge> getEdges()
    {
        return edges;
    }

	/**
	* Creates a string representation of the vertex
	*
	* @return string representation of the vertex
	*/
    public String toString()
    {
		String toReturn = "";
		toReturn += getName();

		for (int i=0; i<numEdges; i++) {
			Edge currEdge = edges.get(i);
			toReturn += currEdge.toString();
			if (i != numEdges-1) {
				toReturn += ",";
			}
		}
		return toReturn;
    }


}
