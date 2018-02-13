package proj2.document;



public class Vertex{

	String symbol;
	Boolean isAcceptState;
	private Edge firstEdge;
	int numEdges=0;
    private List choords;


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
    public void addEdge(Vertex to, String edgeWeight)
    {
    	Edge newEdge=new Edge(to,edgeWeight);
    	newEdge.nextEdge=firstEdge;
    	firstEdge= newEdge;
    	numEdges++;
    }


    public void addCoordinates(int x, int y)
    {
        choords= new List[2];
        choords[0]=x;
        choords[1]=y;
    }

    public int getX()
    {
        return choords[0];
    }

    public int getY()
    {
        return choords[1];
    }

    public void removeEdge(String to, String edgeWeight)
    {
    	if(firstEdge.goingTo.equals(to)&&firstEdge.edgeWeight.equals(edgeWeight))
    	{
    		firstEdge=firstEdge.next;
    	}
    	else{
    		Edge runner = firstEdge;
            while (!runner.nextNode.goingTo.equals(to)&&!runner.nextNode.edgeWeight.equals(edgeWeight)&&!runner.nextNode.equals(null))
            {
                runner=runner.nextNode;
            }
            runner.nextNode=runner.nextNode.nextNode;
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
            while (!runner.nextNode.equals(null))
            {
            	if(runner.goingTo.equals(to)&&runner.edgeWeight.equals(edgeWeight))
            	{
            		return true;
            	}
                runner=runner.nextNode;
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

    public Vertex getName()
    {
    	return symbol;
    }

    public List getEdges()
    {
        List edges = [numEdges];
        Edge runner = firstEdge;
        int x=0;
            while (!runner.nextNode.equals(null))
            {
                edges[x]=[runner.getGoingTo(),runner.edgeWeight];
                x++;
                runner=runner.nextNode;
            }
        return edges;

    }




    public String toString()
    {
        String toReturn= "";
        toReturn = toReturn+ symbol;
        Edge runner = firstEdge;
            while (!runner.nextNode.equals(null))
            {
                toReturn=toReturn+runner.toString;
                if(!runner.nextEdge==null)
                {
                    toReturn=toReturn+",";
                }
                runner=runner.nextNode;
            }
        toReturn=toReturn+"|";
        return toReturn;
    }


}
