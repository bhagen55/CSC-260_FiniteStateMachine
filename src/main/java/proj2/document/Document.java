package proj2.document;

import java.util.LinkedList;
import java.util.ArrayList;
import proj2.view.gui.Observer;

/**
 * Class that holds a linked list of Vertex objects
 */
public class Document
{
    private LinkedList<Vertex> content;
    private ArrayList<String> vertexIndices;
    private LinkedList<Observer> observers;
    private boolean haveAddedCoordinates=false;

    public Document()
    {
        content = new LinkedList<Vertex>();
        vertexIndices = new ArrayList<String>();
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
     * @return linked list of type Vertex which is the list of vertices in the FSM
     */
    public LinkedList<Vertex> getModel()
    {
        return content;
    }

    /**
     * Returns the first vertex in the document which is also the start vertex of
     * FSM
     *
     * @return first vertex in FSM
     */
    public Vertex getFirstVertex()
    {
        return content.get(0);
    }

    /**
     * Removes vertex in the finite state machine.  If vertex is not in the FSM,
     * prints out a message stating that the vertex does not exists
     *
     * @param toRemove name of vertex to be removed
     */
    public void removeVertex(String toRemove)
    {
        if (!this.hasVertex(toRemove)) {
            System.out.println("Vertex specified to be removed does not exist");
        }
        else {
            int vertexIndex = vertexIndices.indexOf(toRemove);
            content.remove(vertexIndex);
            vertexIndices.remove(toRemove);
			notifyObservers();
        }
    }



    /**
     * Adds a vertex to the finite state machine.  If the vertex already exists in
     * the machine, does nothing.  If the vertex does not exist, it is
     * added to the machine, with no edges connected to it.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(String vertex)
    {
        if (!this.hasVertex(vertex)) {
            Vertex toAdd = new Vertex(vertex);
            content.add(toAdd);
            vertexIndices.add(vertex);
			notifyObservers();
        }
    }

    /**
     * Adds a vertex to the finite state machine.  If the vertex already exists in
     * the machine, does nothing.  If the vertex does not exist, it is
     * added to the machine, with no edges connected to it.
     *
     * @param vertex the vertex to add
     * @param xpos x coordinate of the vertex being added
     * @param ypos y coordinate of the vertex being added
     */
    public void addVertex(String vertex, int xpos, int ypos)
    {
        haveAddedCoordinates=true;
        if (!this.hasVertex(vertex)) {
                        System.out.println("Adding vertex");
            Vertex toAdd = new Vertex(vertex);
            toAdd.addCoordinates(xpos, ypos);
            content.add(toAdd);
            vertexIndices.add(vertex);
			notifyObservers();
        }
    }


    /**
     * Tells whether or not a vertex is in the machine.
     *
     * @param vertexName a vertex
     * @return true iff 'vertex' is a vertex in the machine.
     */
    public boolean hasVertex(String vertexName)
    {
        return vertexIndices.contains(vertexName);
    }

    /**
    * Move vertex to given coordinates
    *
    * @param vertexName a vertex to me moved
    * @param xPos x coordinate to move to
    * @param yPos y coordinate to move to
    */
    public void moveVertex(String vertexName, int xPos, int yPos) {
        if (!this.hasVertex(vertexName)) {
            System.out.println("Vertex specified to move does not exist");
        }
        else {
            int vertexIndex = vertexIndices.indexOf(vertexName);
            Vertex foundVertex = content.get(vertexIndex);
            foundVertex.addCoordinates(xPos,yPos);
        }
    }

    /**
    * Toggle accept state of a vertex
    *
    * @param vertexName a vertex
    */
    public void toggleAccept(String vertexName)
    {
        if (!this.hasVertex(vertexName)) {
            System.out.println("Vertex specified to toggle state does not exist");
        }
        else {
            int vertexIndex = vertexIndices.indexOf(vertexName);
            content.get(vertexIndex).toggleAccept();
            notifyObservers();
        }
    }


    /**
     * Adds a directed edge between two vertices.  If there is already an edge
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the edge is created between them.
     *
     * @param from the source vertex for the added edge
     * @param to the destination vertex for the added edge
     * @param edgeWeight weight of edge being added
     */
    public void addEdge(String from, String to, String edgeWeight)
    {
        if (this.hasVertex(from)&&this.hasVertex(to)) {

            int indexFrom = vertexIndices.indexOf(from);
            int indexTo = vertexIndices.indexOf(to);
            Vertex vFrom = content.get(indexFrom);
            Vertex vTo = content.get(indexTo);

            if(!vFrom.hasEdge(to, edgeWeight)) {
                vFrom.addEdge(vTo, edgeWeight);
    			notifyObservers();
                }
        }
    }

    /**
     * Removes an edge between two vertices.  If either of the vertices does not
     * exist, does nothing.
     *
     * @param from the source vertex for the removed edge
     * @param to the destination vertex for the removed edge
     * @param edgeWeight weight of edge being removed
     */
    public void removeEdge(String from, String to, String edgeWeight)
    {
        if (this.hasEdge(from, to, edgeWeight)) {
            int index = vertexIndices.indexOf(from);
            Vertex v = content.get(index);
            v.removeEdge(to, edgeWeight);
			notifyObservers();
        }
    }

    /**
     * Checks if edge between two vertices exists or not. Returns false if either
     * of the vertices entered are not in the FSM.
     *
     * @param from the source vertex for the desired edge
     * @param to the destination vertex for the desired edge
     * @param edgeWeight weight of desired
     *
     * @return true if the referenced edge between vertices exists, false otherwise
     */
    public boolean hasEdge(String from, String to, String edgeWeight)
    {
        if (this.hasVertex(from) && this.hasVertex(to)) {
            int index = vertexIndices.indexOf(from);
            Vertex v = content.get(index);
            return v.hasEdge(to, edgeWeight);
        }
        return false;
    }

    /**
     * Returns the number of edges connected to the specified vertex.
     *
     * @param vertex name of specified vertex that number of edges from is desired
     *
     * @return number of edges from specified vertex
     */
    public int numEdges(String vertex)
    {
        int index = vertexIndices.indexOf(vertex);
        Vertex v = content.get(index);
        return v.numEdges();
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
		System.out.println("Observer Added");
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

    /**
    *Returns a string representation of this finite state machine
    *
    * @return string representation of the document
    */
    public String toString()
    {

        String toReturn= "";
        if(haveAddedCoordinates){

        for(Vertex curVertex: content)
        {
            toReturn=toReturn+curVertex.getName()+"|"+curVertex.getX()+"|"+curVertex.getY();

            toReturn=toReturn+"\n";
        }
    }
        toReturn=toReturn+"$\n";
        for(Vertex curVertex: content)
         {
            toReturn=toReturn+curVertex.toString();
            toReturn=toReturn+"\n";
        }
        return toReturn;

    }
}
