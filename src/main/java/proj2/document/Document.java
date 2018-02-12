package proj2.document;

/*
* Dummy class to hold basic state info
* Like a string name and x/y coords
*/
import java.util.LinkedList; 

public class Document {

	private LinkedList<Vertex> content;

	public Document(){
		content= new LinkedList<Vertex>();
	}

/**
     * @return the number of vertices in the machine.
     */
    public int numVertices()
    {
    	return -1;
    }

    
   public void removeVertex(String toRemove)
   {
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
        return false;
    }

    /**
    *Returns a string representation of this finite state machine
     */
    public String toString()
    {
        return "";
    }



	
}
