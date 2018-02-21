package proj2.document;

import java.util.ArrayList;

public class State{

	private String symbol; //name of this state
	private Boolean isAcceptState; //returns true if this is an accept state otherwise false
	private int numTransitions=0;  // number of transitions in this state
    private int xCoord; // xCoordinate but does not need to be initialized
    private int yCoord; // yCoordinate but does not need to be initialized
    private ArrayList<Transition> transitions; // ArrayList containing transitions


	/**
	* Constructor for state that takes a string to be the name of the state
	* @param givenSymbol: String to be the name of the state
	*/
	public State(String givenSymbol)
	{
		isAcceptState=false;
		symbol=givenSymbol;
		transitions = new ArrayList<Transition>();
	}

	 /*
     * Adds a directed transition between two vertices.  If there is already an transition
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the transition is created between them.
     *
     * @param from the source state for the added transition
     * @param to the destination state for the added transition
     */
    public void addTransition(State to, String transitionWeight)
    {
		transitions.add(new Transition(to,transitionWeight));
		numTransitions++;
    }

    /**
    * Adds or alters coordinates of this state
    *@param x x value of coordinate
    *@param y y value of coordinate
    */
    public void addCoordinates(int x, int y)
    {
        xCoord=x;
        yCoord=y;
    }

    /**
    *returns x coordinate
    *@return x coordinate of this state
    */
    public int getX()
    {
        return xCoord;
    }

    /**
    *returns y coordinate
    *@return y coordinate of this state
    */
    public int getY()
    {
         return yCoord;
    }

    /**
    *gets the transition to a given String with a given weight should it exist
    *@param to of the Symbol that the transition should go to
    *@param transitionWeight weight ot transition your looking for
    *@return Transition with the symbol to and Strign transitionWeight
    */
	private Transition findTransition(String to, String transitionWeight) {

		for (Transition currTransition:transitions) {
			if (currTransition.getWeight().equals(transitionWeight) &&
				currTransition.getGoingTo().equals(to)) {
					return currTransition;
			}
		}

		return null;
	}

    /**
    *removes an transition with Symbol to and weight transitionweight
    *@param to the name of the state the transition to remove goes to
    *@param transitionWeight weight of the transition to be removed
    */
    public void removeTransition(String to, String transitionWeight)
    {
		Transition toRemove = findTransition(to, transitionWeight);
    	if (toRemove != null) {
			transitions.remove(toRemove);
			numTransitions--;
		}
    }


    /**
    *returns if a desired transition exists
    *@param to: the name of the state the transition to remove goes to
    *@param transitionWeight: weight of the transition to be removed
    *@return true if a certain transition exists false otherwise
    */
   	public boolean hasTransition(String to, String transitionWeight)
   	{
		if (findTransition(to, transitionWeight) != null) {
			return true;
		} else {
			return false;
		}
   	}


    /**
    *returns the number of transitions
    *@return the number of transitions in this state
    */
    public int numTransitions()
    {
        return numTransitions;
    }

    /**
    *@return true if this is an accept state false otherwise
    */
    public boolean canAccept()
    {
    	return isAcceptState;
    }

    /**
    *if state is an accept state makes
    *it not an accept state and if it is not an accept state
    *the state becomes an accept state
    */
	public void toggleAccept()
	{
		isAcceptState = !isAcceptState;
	}

    /**
    *@return the name of this state
    */
    public String getName()
    {
    	return symbol;
    }

    /**
    * @return the transitions in this state
    */
    public ArrayList<Transition> getTransitions()
    {
        return transitions;
    }

	/**
	* Creates a string representation of the state
	*
	* @return string representation of the state
	*/
    public String toString()
    {
		String toReturn = "";
		toReturn += getName();

		for (int i=0; i<numTransitions; i++) {
			Transition currTransition = transitions.get(i);
			toReturn += currTransition.toString();
			if (i != numTransitions-1) {
				toReturn += ",";
			}
		}
		return toReturn;
    }


}
