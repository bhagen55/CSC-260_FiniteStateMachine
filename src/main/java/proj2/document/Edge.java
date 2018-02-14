package proj2.document;

/**
* Represents the transition of a finite state machine.
* Called edge because of carryover from graph data type
*/
public class Edge{

    //What value the edge needs
	public Vertex goingTo;
    public String edgeWeight;

    /**
    *Creates an Edge to a given vertex with a given weight
    *@param to: vertex Edge goes to
    *@param edgeWeightGiven: weight of this edge
    */
	public Edge(Vertex to,String edgeWeightGiven)
	{
		edgeWeight=edgeWeightGiven;
        goingTo=to;
	}

    /**
	* Gets the transition label
	*
    * @return the weight of this edge
    */
	 public String getWeight()
     {
        return edgeWeight;
     }

	 /**
	 * Creates a string representation of the edge
	 *
	 * @return string representation of the edge
	 */
     public String toString()
     {
        return "("+getGoingTo()+","+edgeWeight+")";
     }


    /**
	* Gets the name of the vertex this edge is going to
	*
    * @return String name of the vertex this edge goes to
    */
     public String getGoingTo()
     {
        return goingTo.getName();
     }

}
