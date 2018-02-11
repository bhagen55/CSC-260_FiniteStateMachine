package proj2.document;


public class Edge{

    //What value the edge needs 
	String gointTo;
    String edgeWeight

	public Edge(String to,String edgeWeightGiven)
	{
		edgeWeight=edgeWeightGiven;
        gointTo=to;
	}

	 public String getWeight()
     {
        return edgeWeight;
     }

    


}