package proj2.document;


public class Edge{

    //What value the edge needs 
	String gointTo;
    String edgeWeight;
    Edge nextEdge;

	public Edge(String to,String edgeWeightGiven)
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
        return "("+gointTo+","+edgeWeight+")";
     }




}