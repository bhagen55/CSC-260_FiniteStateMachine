package proj2.view.gui;

import javax.swing.*;
import java.awt.*;
// import javax.swing.SwingUtilities;
// import javax.swing.JPanel;
// import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Point;


import java.util.ArrayList;
import java.util.LinkedList;

import proj2.document.*;
import proj2.view.gui.shapes.*;
import proj2.view.gui.Observer;

/*
* Extension of JPanel that handles drawing of states and vertex objects
* Adapted from this tutorial: https://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html
* Dragging: http://www.java2s.com/Code/Java/Event/MoveShapewithmouse.htm
*/
public class DrawPanel extends JPanel implements Observer, MouseListener, MouseMotionListener {

  	// Holds shadow classes of document vertices
  	private ArrayList<VertexShape> vertexShapes;

	// Holds shadow classes of edges
	private ArrayList<EdgeShape> edgeShapes;

  	// Holds the current selected vertex while it is being dragged
  	private VertexShape selVertex;

    // Holds the document that the view edits
    private Document doc;

  	// Holds last known location of mouse for dragging
  	private int preX;
  	private int preY;
  	private int preXDrag;
  	private int preYDrag;

    // Boolean value to know if edge is being created or set
    private boolean edgeStarted;

    //
    private VertexShape fromVertex;
    private VertexShape toVertex;
    private boolean foundVertex;

    private int buttonNumber;

    private String vertexName;

	/*
	* Text Box
	*/
	private JTextField vertexField;
	private JTextField edgeField;
	private String vertexNameEntry;
	private String edgeNameEntry;

	private JLabel vertexFieldName;
	private JLabel edgeFieldName;


	public DrawPanel(Document d) {

    	System.out.println("Setting up");

        // Set border of the panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Add text entry boxes and labels
		vertexField = new JTextField(6);
		edgeField = new JTextField(6);
		vertexFieldName = new JLabel("New Vertex Name");
		edgeFieldName = new JLabel("New Edge Name");


        vertexShapes = new ArrayList<VertexShape>();

		edgeShapes = new ArrayList<EdgeShape>();

        doc = d;

        // Add mouse listener to the panel to deal with mouse events
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
    * Updates this view based on information from the document
    *
    */
    public void update()
    {
		System.out.println("Panel update called");
        // Clear out old shapes
        vertexShapes = new ArrayList<VertexShape>();
        edgeShapes = new ArrayList<EdgeShape>();

        // Holds the current model that the view is based off
        LinkedList<Vertex> model = doc.getModel();

        for (Vertex vertex: model) {
            vertexShapes.add(new VertexShape(vertex.getX(),vertex.getY(), vertex.getName(), vertex.canAccept()));
        }

        for (VertexShape vertex: vertexShapes) {

            Vertex currVertex = model.get(vertexShapes.indexOf(vertex));

            ArrayList<Edge> vertexEdges = currVertex.getEdges();
            for (Edge edge: vertexEdges) {

                // Find vertexshape that corresponds to the end of the edge
                VertexShape endVertex = vertexShapes.get(0);
                for (VertexShape currVerShape: vertexShapes) {
                    if (currVerShape.getName().equals(edge.getGoingTo())) {
                        endVertex = currVerShape;
                    }
                }
                edgeShapes.add(new EdgeShape(vertex, endVertex, edge.getEdgeWeight()));
            }
        }
        edgeShapes.add(new EdgeShape(fromVertex, toVertex, vertexName));
    }


    /**
    * Moves vertex to the given x and y coords
    *
    * @param v the vertex to be moved
    * @param x x coordinate to be moved to
    * @param y y coordinate to be moved to
    */
    private void moveVertex(VertexShape v, int x, int y){

        // Current square state, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int CURR_X = v.getX();
        final int CURR_Y = v.getY();
        final int CURR_W = v.getWidth();
        final int CURR_H = v.getHeight();
        final int OFFSET = 1;

        if ((CURR_X!=x) || (CURR_Y!=y)) {

            // The square is moving, repaint background
            // over the old square location.
            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
            v.setX(x);
            v.setY(y);

            // Repaint the square at the new location.
            repaint(v.getX(), v.getY(),
                    v.getWidth()+OFFSET,
                    v.getHeight()+OFFSET);
        }
    }

    /**
    * Paints all of the vertices as well as the panel
    *
    * @param g graphics object to be painted
    */
    public void paintComponent(Graphics g) {

		// Paints the panel
    	super.paintComponent(g);

		// Paints the text boxes and their labels
		vertexField.setBounds(2,0, 120, 22);
		edgeField.setBounds(152, 0, 120, 22);
		add(vertexField);
		add(edgeField);
		vertexFieldName.setBounds(2, 20, 120, 20);
		edgeFieldName.setBounds(152, 20, 120, 20);
		add(vertexFieldName);
		add(edgeFieldName);

		// Paints the vertices
        for (VertexShape vertex: vertexShapes) {
        	vertex.paintShape(g);
        }

        for (EdgeShape edge: edgeShapes) {
          edge.paintShape(g);
        }
    }

	/**
	* Indicates the start of a drag.
	* Saves the vertex that is being pressed to be modified by the dragging
	* handler.
	*
	* @param e mouse event passed by mouse listener
	*/
    public void mousePressed(MouseEvent e) {

      buttonNumber = e.getButton();
      System.out.println("buttonNumber is "+buttonNumber);
      System.out.println();
      System.out.println("PRESSED x " + e.getX());
      System.out.println("PRESSED y " + e.getY());
      System.out.println();
		// Check if actually pressing on a vertex
		for (VertexShape vertex: vertexShapes) {
    		if (vertex.getEllipse().getBounds().contains(e.getPoint())) {
				// Save the vertex as the currently selected one.
		         //System.out.println("Selecting vertex " + vertex.getName());
                 System.out.println("PRESSED vertex is " + vertex.getName());

          	     fromVertex = vertex;
                 selVertex = vertex;
                 preX = e.getX();
                 preY = e.getY();
        	}
      	}
    }

	/**
	* Indicates release of mouse.
	* Clears out the selected vertex at the end of a drag.
	*
	* @param e mouse event passed by mouse listener
	*/
    public void mouseReleased(MouseEvent e) {

		// If left click, just release the selected vertex
		System.out.println("MOUSE RELEASED BUTTON " + e.getButton());
		if (e.getButton() == MouseEvent.BUTTON1) {
			System.out.println("Releasing selected vertex");
			// Release the currently selected vertex
			fromVertex = null;
		}
		// If right click, create an edge to the vertex it was released on
		// TODO: Get name from a GUI box
		else if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println();
            System.out.println("RELEASE x " + e.getX());
            System.out.println("RELEASE y " + e.getY());
            System.out.println();
			for (VertexShape vertex: vertexShapes) {
				if (vertex.getEllipse().getBounds().contains(e.getPoint())) {
					System.out.println("RELEASED vertex is " + vertex.getName());
					vertexName = edgeField.getText();
					//vertexName = "" + (edgeShapes.size()+1);
                    toVertex = vertex;
					//System.out.println(selVertex.getName());
					//System.out.println(vertex.getName());

					// Release the selected vertex
	        	}
			}
            System.out.println("fromVertex name is " + fromVertex.getName());
            System.out.println("toVertex name is " + toVertex.getName());
            System.out.println("fromVertex pos "+"("+fromVertex.getX()+","+fromVertex.getY()+")");
            System.out.println("toVertex pos "+"("+toVertex.getX()+","+toVertex.getY()+")");

			// TODO: Confirm new implementation here
			doc.addEdge(fromVertex.getName(), toVertex.getName(), vertexName);
            //edgeShapes.add(new EdgeShape(fromVertex, toVertex, vertexName));

		}
        repaint();
    }

	/**
	* Indicates mouse has entered the panel.
	* Currently not used.
	*
	* @param e mouse event passed by mouse listener
	*/
    public void mouseEntered(MouseEvent e) {
    }

	/**
	* Indicates mouse has exited the panel.
	* Currently not used.
	*
	* @param e mouse event passed by mouse listener
	*/
    public void mouseExited(MouseEvent e) {
    }

	/**
	* Indicates the mouse has been clicked a number of times
	* Checks if the user is clicking on a vertex. If it isn't, creates a vertex
	* If the user double clicks on a vertex the accept state is toggled.
	*
	* @param e mouse event passed by mouse listener
	*/
	public void mouseClicked(MouseEvent e) {

		// Only look for left clicks
        System.out.println("e button # " + e.getButton());
		if (e.getButton() == 1) {
			foundVertex = false;
            System.out.println("foundVertex is false");

			// check if the user is clicking on a vertex
			for (VertexShape vertex: vertexShapes) {
				System.out.println("Looking for vertexes");
				if (vertex.getEllipse().getBounds().contains(e.getPoint())) {

					// If they are double clicking, toggle the accept state
					if (e.getClickCount() == 2) {
						vertex.toggleAccept(); // TODO: toggle actual vertex througj document
					}
					foundVertex = true;
                }
            }

            if (!foundVertex) {
                System.out.println("NO VERTEX");
  				String name = vertexField.getText();
                doc.addVertex(name, e.getX(), e.getY()); // TODO: Change to match new implementation
  			}
        repaint();
		}
    }

	/**
	* Indicates the mouse is moved.
	* Currently not used.
	*
	* @param e mouse event passed by mouse listener
	*/
	public void mouseMoved(MouseEvent e) {
    }

	/**
	* Indicates the mouse has been dragged with the button pressed down.
	* If a vertex was pressed on, this moves the vertex.
	*
	* @param e mouse event passed by mouse listener
	*/
    public void mouseDragged(MouseEvent e) {
		// Only move with a left click
		if (buttonNumber != 3 && selVertex != null) {

            //selVertex.setY(e.getY());
            //selVertex.setX(e.getX());
            int Xoffset = e.getX() - preX;
            int Yoffset = e.getY() - preY;

            selVertex.moveShape(e.getX(), e.getY());

			// Only move if a vertex was clicked on before the mouse was dragged
			if (selVertex != null) {
				selVertex.setY(e.getY());
				selVertex.setX(e.getX());
				selVertex.moveShape(preX + e.getX(), preY + e.getY());

				repaint();
		    }
		}
        repaint();
    }
}
