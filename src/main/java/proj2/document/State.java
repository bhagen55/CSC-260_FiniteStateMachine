package proj2.document;

import java.util.ArrayList;

import proj2.document.Action;
import proj2.document.actions.*;

public class State{

	private String symbol; //name of this state
	private Boolean isAcceptState; //returns true if this is an accept state otherwise false
	private int numTransitions=0;  // number of transitions in this state
    private int xCoord; // xCoordinate but does not need to be initialized
    private int yCoord; // yCoordinate but does not need to be initialized
    private ArrayList<Transition> transitions; // ArrayList containing transitions
    private Action action; // Action performed by the state when the FSM is simulated


	/**
	* Constructor for state that takes a string to be the name of the state
    *
	* @param givenSymbol: String to be the name of the state
	*/
	public State(String givenSymbol)
	{
		isAcceptState = false;
		symbol = givenSymbol;
		transitions = new ArrayList<Transition>();
		action = new NoAction();
	}

	/**
     * Adds a directed transition between two vertices.  If there is already an transition
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the transition is created between them.
     *
     * @param transitionWeight string that the transition accepts
     * @param to the destination state for the added transition
     */
    public void addTransition(State to, String transitionWeight)
    {
		transitions.add(new Transition(to,transitionWeight));
		numTransitions++;
    }

    /**
     * Adds or alters coordinates of this state
     *
     * @param x x value of coordinate
     * @param y y value of coordinate
     */
    public void addCoordinates(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /**
     * Returns x coordinate
     *
     * @return x coordinate of this state
     */
    public int getX()
    {
        return xCoord;
    }

    /**
     * Returns y coordinate
     *
     * @return y coordinate of this state
     */
    public int getY()
    {
         return yCoord;
    }

    /**
     * Returns the state at the other end of the transition with the specified
     * weight
     *
     * @param transitionWeight string weight that the method will follow to the next state
     *
     * @return State object at the end of the specified transition
     */
	public State goTo(String transitionWeight)
    {
		if (hasTransition(transitionWeight))
        {
			for (Transition curr : transitions)
            {
				String currWeight = curr.getWeight();
				if (currWeight.equals(transitionWeight))
                {
					Transition goal = getTransition(transitionWeight);
					return goal.getGoingTo();
				}
			}
		}
		return null;
	}

    /**
     * Returns first Transition object with a given transition getWeight
     *
     * @param weight String weight of the desired transition
     *
     * @return First Transition object that corresponds to the given weight
     */
	public Transition getTransition(String weight)
    {
		for (Transition curr : transitions)
        {
			if (curr.getWeight().equals(weight))
            {
				return curr;
			}
		}
		return null;
	}

    /**
     * Gets the transition to a given String with a given weight should it exist
     *
     * @param to of the Symbol that the transition should go to
     *
     * @param transitionWeight weight ot transition your looking for
     * @return Transition with the symbol to and Strign transitionWeight
     */
	private Transition findTransition(String to, String transitionWeight)
    {

		for (Transition currTransition:transitions)
        {
			if (currTransition.getWeight().equals(transitionWeight) &&
				currTransition.getGoingToName().equals(to))
            {
					return currTransition;
			}
		}

		return null;
	}

    /**
     * Removes an transition with Symbol to and weight transitionweight
     *
     * @param to the name of the state the transition to remove goes to
     * @param transitionWeight weight of the transition to be removed
     */
    public void removeTransition(String to, String transitionWeight)
    {
		Transition toRemove = findTransition(to, transitionWeight);
    	if (toRemove != null)
        {
			transitions.remove(toRemove);
			numTransitions--;
		}
    }


    /**
     * Returns if a desired transition exists
     *
     * @param to: the name of the state the transition to remove goes to
     *
     * @param transitionWeight: weight of the transition to be examined
     * @return true if a certain transition exists false otherwise
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
     * Returns if a desired transition exists
     *
     * @param transitionWeight: weight of the transition to be examined
     * @return true if a certain transition exists false otherwise
     */
	public boolean hasTransition(String transitionWeight) {
		if (getTransition(transitionWeight) != null) {
			return true;
		} else {
			return false;
		}
	}


    /**
     * Returns the number of transitions
     * @return the number of transitions in this state
     */
    public int numTransitions()
    {
        return numTransitions;
    }

    /**
     * Returns whether or not the state is an accept state
     *
     * @return true if this is an accept state false otherwise
     */
    public boolean canAccept()
    {
    	return isAcceptState;
    }

    /**
     * Toggles whether or not the current state is an accept state
     */
	public void toggleAccept()
	{
		isAcceptState = !isAcceptState;
	}

    /**
     * Returns string name of the state
     *
     * @return the name of this state
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
     * @return the transitions in this state
     */
    public ArrayList<String> getTransitionStrings()
    {
        ArrayList<String> copy =new ArrayList<String>();
        for (Transition curr : transitions)
        {
            copy.add(curr.getStringOfTransitionContents());
        }
        return copy;

    }

    /**
     * Sets the action of this state that can be run
	 *
	 * @param action action to be applied to this state
     */
    public void setAction(Action action)
    {
		this.action = action;
    }

	/**
	* Get action of this state
	*
	* @return action associated with this state
	*/
	public Action getAction() {
		return action;
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
