package proj2.document;

/**
* Represents the transition of a finite state machine.
*/
public class Transition{

    //What value the transition needs
	public State goingTo;
    public String transitionWeight;

    /**
    *Creates an Transition to a given state with a given weight
    *@param to: state Transition goes to
    *@param transitionWeightGiven: weight of this transition
    */
	public Transition(State to,String transitionWeightGiven)
	{
		transitionWeight=transitionWeightGiven;
        goingTo=to;
	}

    /**
	* Gets the transition label
	*
    * @return the weight of this transition
    */
	 public String getWeight()
     {
        return transitionWeight;
     }

	 /**
	 * Creates a string representation of the transition
	 *
	 * @return string representation of the transition
	 */
     public String toString()
     {
        return "("+getGoingTo()+","+transitionWeight+")";
     }


    /**
	* Gets the name of the state this transition is going to
	*
    * @return String name of the state this transition goes to
    */
     public String getGoingTo()
     {
        return goingTo.getName();
     }

}
