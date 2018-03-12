package proj2.document;

import java.util.LinkedList;
import java.util.ArrayList;

import proj2.view.gui.Observer;
import proj2.document.Action;
import proj2.document.actions.*;

/**
 * Class that holds a linked list of State objects
 */
public class Document
{
    private LinkedList<State> content;
    private ArrayList<String> stateIndices;
    private LinkedList<Observer> observers;
    private boolean haveAddedCoordinates=false;

    public Document()
    {
        content = new LinkedList<State>();
        stateIndices = new ArrayList<String>();
        observers = new LinkedList<Observer>();
    }

    /**
     * Returns the number vertices in the FSM
     *
     * @return the number of vertices in the machine.
     */
    public int numVertices()
    {
        return content.size();
    }

    /**
     * Package private method that returns the linked list of vertices for the
     * view to display.
     *
     * @return linked list of type State which is the list of vertices in the FSM
     */
    public LinkedList<State> getModel()
    {
        return content;
    }

    /**
     * Returns the first state in the document which is also the start state of
     * FSM
     *
     * @return first state in FSM
     */
    public State getFirstState()
    {
        return content.get(0);
    }

    /**
     * Removes state in the finite state machine.  If state is not in the FSM,
     * prints out a message stating that the state does not exists
     *
     * @param toRemove name of state to be removed
     */
    public void removeState(String toRemove)
    {
        if (!this.hasState(toRemove)) {
            System.out.println("State specified to be removed does not exist");
        }
        else {
            int stateIndex = stateIndices.indexOf(toRemove);
            content.remove(stateIndex);
            stateIndices.remove(toRemove);
			notifyObservers();
        }
    }



    /**
     * Adds a state to the finite state machine.  If the state already exists in
     * the machine, does nothing.  If the state does not exist, it is
     * added to the machine, with no transitions connected to it.
     *
     * @param state the state to add
     */
    public void addState(String state)
    {
        if (!this.hasState(state)) {
            State toAdd = new State(state);
            content.add(toAdd);
            stateIndices.add(state);
			notifyObservers();
        }
    }

    /**
     * Adds a state to the finite state machine.  If the state already exists in
     * the machine, does nothing.  If the state does not exist, it is
     * added to the machine, with no transitions connected to it.
     *
     * @param state the state to add
     * @param xpos x coordinate of the state being added
     * @param ypos y coordinate of the state being added
     */
    public void addState(String state, int xpos, int ypos)
    {
        haveAddedCoordinates=true;
        if (!this.hasState(state)) {
            State toAdd = new State(state);
            toAdd.addCoordinates(xpos, ypos);
            content.add(toAdd);
            stateIndices.add(state);
			notifyObservers();
        }
    }


    /**
     * Tells whether or not a state is in the machine.
     *
     * @param stateName a state
     * @return true iff 'state' is a state in the machine.
     */
    public boolean hasState(String stateName)
    {
        return stateIndices.contains(stateName);
    }

    /**
    * Move state to given coordinates
    *
    * @param stateName a state to me moved
    * @param xPos x coordinate to move to
    * @param yPos y coordinate to move to
    */
    public void moveState(String stateName, int xPos, int yPos) {
        if (!this.hasState(stateName)) {
            System.out.println("State specified to move does not exist");
        }
        else {
            int stateIndex = stateIndices.indexOf(stateName);
            State foundState = content.get(stateIndex);
            foundState.addCoordinates(xPos,yPos);
        }
    }

    /**
    * Toggle accept state of a state
    *
    * @param stateName a state
    */
    public void toggleAccept(String stateName)
    {
        if (!this.hasState(stateName)) {
            System.out.println("State specified to toggle state does not exist");
        }
        else {
            int stateIndex = stateIndices.indexOf(stateName);
            content.get(stateIndex).toggleAccept();
            notifyObservers();
        }
    }

    /**
    * Determine accept state of a state
    *
    * @param stateName a state
    */
    public boolean getAccept(String stateName)
    {
        if (!this.hasState(stateName)) {
            System.out.println("State specified to toggle state does not exist");
        }
        else {
            int stateIndex = stateIndices.indexOf(stateName);
            return content.get(stateIndex).canAccept();
        }
    }


    /**
     * Adds a directed transition between two vertices.  If there is already an transition
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the transition is created between them.
     *
     * @param from the source state for the added transition
     * @param to the destination state for the added transition
     * @param transitionWeight weight of transition being added
     */
    public void addTransition(String from, String to, String transitionWeight)
    {
        if (this.hasState(from) && this.hasState(to)) {

            int indexFrom = stateIndices.indexOf(from);
            int indexTo = stateIndices.indexOf(to);
            State vFrom = content.get(indexFrom);
            State vTo = content.get(indexTo);

            if(!vFrom.hasTransition(to, transitionWeight)) {
                vFrom.addTransition(vTo, transitionWeight);
    			notifyObservers();
                }
        }
    }

    /**
     * Removes an transition between two vertices.  If either of the vertices does not
     * exist, does nothing.
     *
     * @param from the source state for the removed transition
     * @param to the destination state for the removed transition
     * @param transitionWeight weight of transition being removed
     */
    public void removeTransition(String from, String to, String transitionWeight)
    {
        if (this.hasTransition(from, to, transitionWeight)) {
            int index = stateIndices.indexOf(from);
            State v = content.get(index);
            v.removeTransition(to, transitionWeight);
			notifyObservers();
        }
    }

    /**
     * Checks if transition between two vertices exists or not. Returns false if either
     * of the vertices entered are not in the FSM.
     *
     * @param from the source state for the desired transition
     * @param to the destination state for the desired transition
     * @param transitionWeight weight of desired
     *
     * @return true if the referenced transition between vertices exists, false otherwise
     */
    public boolean hasTransition(String from, String to, String transitionWeight)
    {
        if (this.hasState(from) && this.hasState(to)) {
            int index = stateIndices.indexOf(from);
            State v = content.get(index);
            return v.hasTransition(to, transitionWeight);
        }
        return false;
    }

    /**
     * Returns the number of transitions connected to the specified state.
     *
     * @param state name of specified state that number of transitions from is desired
     *
     * @return number of transitions from specified state
     */
    public int numTransitions(String state)
    {
        int index = stateIndices.indexOf(state);
        State v = content.get(index);
        return v.numTransitions();
    }

    /**
     * Notify method to tell views/observers to update
     */
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    /**
     * Adds an observer to the list of observers stored in the document
     *
     * @param o observer of type Observer to be added
     */
    public void addObserver(Observer o)
    {
        observers.add(o);
    }

    /**
    *returns x coordinate
    *@return x coordinate of this state
    */
    public int getX(String StateName)
    {
        int stateIndex = stateIndices.indexOf(StateName);
        return content.get(stateIndex).getX();
    }

    /**
    *returns y coordinate
    *@return y coordinate of this state
    */
    public int getY(String StateName)
    {
		int stateIndex = stateIndices.indexOf(StateName);
        return content.get(stateIndex).getY();
    }

	/**
	* Adds an action to a state
	*
	* @param StateName name of state to add action to
	*/
	public void addAction(String stateName, Action action)
	{
		int stateIndex = stateIndices.indexOf(stateName);
		content.get(stateIndex).setAction(action);
	}

    /**
    * Get action associated with state
    *
    * @param stateName name of state to get action of
    * @return action associated with state
    */
    public Action getAction(String stateName)
    {
        int stateIndex = stateIndices.indexOf(stateName);
        return content.get(stateIndex).getAction();
    }


    /**
     * Removes an observer from the list of observers stored in the document
     *
     * @param o observer of type Observer to be removed
     */
    public void removeObserver(Observer o)
    {
        observers.remove(o);
    }


    public ArrayList<String> getStates(){
        ArrayList<String> copy= new ArrayList<String>();
        for(int x=0;x<stateIndices.size();x++)
        {

        copy.add(stateIndices.get(x));
        }
        return copy;
    }

    public ArrayList<String> getTransitionsForState(String StateName)
    {
        int stateIndex = stateIndices.indexOf(StateName);
        return content.get(stateIndex).getTransitionStrings();
    }

    /**
    *Returns a string representation of this finite state machine
    *
    * @return string representation of the document
    */
    public String toString()
    {

        String toReturn= "";
        if(haveAddedCoordinates){

        for(State curState: content)
        {
            toReturn=toReturn+curState.getName()+"|"+curState.getX()+"|"+curState.getY();

            toReturn=toReturn+"\n";
        }
    }
        toReturn=toReturn+"$\n";
        for(State curState: content)
         {
            toReturn=toReturn+curState.toString();
            toReturn=toReturn+"\n";
        }
        return toReturn;

    }

}
