package proj2.document;



public class Vertex{

	String symbol;
	Boolean isAcceptState;
	private Edge firstEdge;
	int numEdges;

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
    	Edge newEdge=new Edge(to,edgeWeight)
    	newEdge.nextEdge=firstEdge;
    	firstEdge= newEdge;
    	numEdges++;
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

    public Vertex goTo(String edgeWeight)
    {
    	return null;
    }

    public String toString()
    {
    	String final=symbol;

		Edge runner = firstEdge;
            while (!runner.nextNode.equals(null))
            {
            	final=final+runner.toString
            	if(!runner.nextEdge==null)
            	{
            		final=final+",";
            	}
                runner=runner.nextNode;
            }
        final=final+"|";
    }


}