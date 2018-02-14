package proj2.document;

import java.util.ArrayList;

public class Vertex{

	private String symbol;
	private Boolean isAcceptState;
	private Edge firstEdge;
	private int numEdges=0;
    private int xCoord;
    private int yCoord;
    private ArrayList<Edge> edges;


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


    public void addCoordinates(int x, int y)
    {
        xCoord=x;
        yCoord=y;
    }

    public int getX()
    {
        return xCoord;
    }

    public int getY()
    {
         return yCoord;
    }

	public void setX(int newX) {
		xCoord = newX;
	}

	public void setY(int newY) {
		yCoord = newY;
	}

	private Edge findEdge(String to, String edgeWeight) {

		for (Edge currEdge:edges) {
			if (currEdge.getWeight().equals(edgeWeight) &&
				currEdge.getGoingTo().equals(to)) {
					return currEdge;
			}
		}

		return null;
	}

    public void removeEdge(String to, String edgeWeight)
    {
		Edge toRemove = findEdge(to, edgeWeight);
    	if (toRemove != null) {
			edges.remove(toRemove);
			numEdges--;
		}
    }


   	public boolean hasEdge(String to, String edgeWeight)
   	{
		if (findEdge(to, edgeWeight) != null) {
			return true;
		} else {
			return false;
		}
   	}



    public int numEdges()
    {
        return numEdges;
    }

    public boolean canAccept()
    {
    	return isAcceptState;
    }

	public void toggleAccept()
	{
		isAcceptState = !isAcceptState;
	}

    public String getName()
    {
    	return symbol;
    }

    public ArrayList<Edge> getEdges()
    {
        return edges;
    }

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
		return toReturn + "|";
    }


}
