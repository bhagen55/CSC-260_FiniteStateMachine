package proj2.document;


public class Edge{

    //What value the edge needs
	public Vertex goingTo;
    public String edgeWeight;

    /**
    *Creates an Edge to a given vertex with a given weight
    *@oaram Vertex: vertex Edge goes to
    *@param String: weight of this edge
    */
	public Edge(Vertex to,String edgeWeightGiven)
	{
		edgeWeight=edgeWeightGiven;
        goingTo=to;
	}

    /**
    *@return the weight of this edge
    */
	 public String getWeight()
     {
        return edgeWeight;
     }


     public String toString()
     {
        return "("+getGoingTo()+","+edgeWeight+")";
     }


    /**
    *@return String name of the vertex this edge goes to
    */
     public String getGoingTo()
     {
        return goingTo.getName();
     }

}
