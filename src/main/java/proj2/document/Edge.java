package proj2.document;


public class Edge{

    //What value the edge needs
	Vertex gointTo;
    String edgeWeight;
    Edge nextEdge;

	public Edge(Vertex to,String edgeWeightGiven)
	{
		edgeWeight=edgeWeightGiven;
        gointTo=to;
	}

	 public String getWeight()
     {
        return edgeWeight;
     }

     public string toString()
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
