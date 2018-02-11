package proj2.document;

/**
 * Class that holds a linked list of Vertex objects
 */
import java.util.LinkedList;
import java.util.ArrayList;

public class Document
{

	private LinkedList<Vertex> content;
    private ArrayList<String> vertexIndices;

	public Document()
    {
		content = new LinkedList<Vertex>();
        vertexIndices = new ArrayList<String>();
	}

    /**
     * @return the number of vertices in the machine.
     */
    public int numVertices()
    {
    	return content.size();
    }

    /**
     *
     */
    public void removeVertex(String toRemove)
    {
        if (!this.hasVertex(toRemove)) {
            System.out.println("Vertex specified to be removed does not exist");
        }
        else {

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

    }

    /**
     * Tells whether or not a vertex is in the machine.
     *
     * @param vertex a vertex
     * @return true iff 'vertex' is a vertex in the machine.
     */
    public boolean hasVertex(String vertexName)
    {
        return vertexIndices.contains(vertexName);
    }


    /**
    *Returns a string representation of this finite state machine
    */
    public String toString()
    {
        return "";
    }


}
