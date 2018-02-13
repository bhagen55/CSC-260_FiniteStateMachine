package proj2.document;


public class Edge{

    //What value the edge needs
	public Vertex goingTo;
    public String edgeWeight;
    public Edge nextEdge;

	public Edge(Vertex to,String edgeWeightGiven)
	{
		edgeWeight=edgeWeightGiven;
        goingTo=to;
	}

	 public String getWeight()
     {
        return edgeWeight;
     }

     public String toString()
     {
        return "("+getGoingTo()+","+edgeWeight+")";
     }

     public String getGoingTo()
     {
        return goingTo.getName();
     }
     public String getEdgeWeight()
     {
        return edgeWeight;
     }

}
