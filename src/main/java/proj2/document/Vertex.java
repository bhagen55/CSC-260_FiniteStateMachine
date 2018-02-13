package proj2.document;

import java.util.ArrayList;

public class Vertex{

	String symbol;
	Boolean isAcceptState;
	private Edge firstEdge;
	int numEdges=0;
    private int xCoord;
    private int yCoord;
    private ArrayList edges;


	public Vertex(String givenSymbol)
	{
		isAcceptState=false;
		symbol=givenSymbol;
		edges= new ArrayList();
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
    public void addEdge(Vertex to, String edgeWeight)
    {
    	Edge newEdge=new Edge(to,edgeWeight);
    	newEdge.nextEdge=firstEdge;
    	firstEdge= newEdge;
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

    public void removeEdge(String to, String edgeWeight)
    {
    	if(firstEdge.goingTo.equals(to)&&firstEdge.edgeWeight.equals(edgeWeight))
    	{
    		firstEdge=firstEdge.nextEdge;
    	}
    	else{
    		Edge runner = firstEdge;
            while (!runner.nextEdge.goingTo.equals(to)&&!runner.nextEdge.edgeWeight.equals(edgeWeight)&&!runner.nextEdge.equals(null))
            {
                runner=runner.nextEdge;
            }
            runner.nextEdge=runner.nextEdge.nextEdge;
            }
         numEdges--;
    }


   	public boolean hasEdge(String to, String edgeWeight)
   	{
   		if(firstEdge.goingTo.equals(to)&&firstEdge.edgeWeight.equals(edgeWeight))
    	{
    		return true;
    	}
    	else{
    		Edge runner = firstEdge;
            while (!runner.nextEdge.equals(null))
            {
            	if(runner.goingTo.equals(to)&&runner.edgeWeight.equals(edgeWeight))
            	{
            		return true;
            	}
                runner=runner.nextEdge;
            }

            }
        return false;
   	}



    public int numEdges()
    {
        return numEdges;
    }

    public boolean canAccept()
    {
    	return isAcceptState;
    }

    public String getName()
    {
    	return symbol;
    }

    public ArrayList<Edge> getEdges()
    {
        ArrayList<Edge> edges = new ArrayList();
        Edge runner = firstEdge;
        int x=0;
            while (!runner.nextEdge.equals(null))
            {
                edges.add(runner);
                x++;
                runner=runner.nextEdge;
            }
        return edges;

    }

    public String toString()
    {
        String toReturn= "";
        toReturn = toReturn+ symbol;
        Edge runner = firstEdge;
            while (!runner.nextEdge.equals(null))
            {
                toReturn=toReturn+runner.toString();
                if(runner.nextEdge!=null)
                {
                    toReturn=toReturn+",";
                }
                runner=runner.nextEdge;
            }
        toReturn=toReturn+"|";
        return toReturn;
    }


}
