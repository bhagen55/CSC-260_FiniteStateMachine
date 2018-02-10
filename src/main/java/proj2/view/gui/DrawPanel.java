package proj2.view.gui;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Rectangle;
import java.awt.Point;


import java.util.ArrayList;

import proj2.document.*;
import proj2.view.gui.shapes.*;

/*
* Extension of JPanel that handles drawing of states and vertex objects
* Adapted from this tutorial: https://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html
* Dragging: http://www.java2s.com/Code/Java/Event/MoveShapewithmouse.htm
*/
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

  	// Holds shadow classes of document vertices
  	ArrayList<VertexShape> vertices;

	// Holds shadow classes of edges
	ArrayList<EdgeShape> edges;

  	// Holds the current selected vertex while it is being dragged
  	VertexShape selVertex;

  	// Holds last known location of mouse for dragging
  	int preX;
  	int preY;
  	int preXDrag;
  	int preYDrag;

    // Boolean value to know if edge is being created or set
    boolean edgeStarted;

    //
    VertexShape fromVertex;
    VertexShape toVertex;
    boolean foundVertex;

	public DrawPanel() {

    	System.out.println("Setting up");

        // Set border of the panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        vertices = new ArrayList<VertexShape>();

		edges = new ArrayList<EdgeShape>();

        // Add mouse listener to the panel to deal with mouse events
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
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

		// Paints the vertices
        for (VertexShape vertex: vertices) {
        	vertex.paintShape(g);
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

		// Check if actually pressing on a vertex
		for (VertexShape vertex: vertices) {
    		if (vertex.getEllipse().getBounds().contains(e.getPoint())) {
				// Save the vertex as the currently selected one.
				System.out.println("Selecting vertex " + vertex.getName());
          		selVertex = vertex;
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
		System.out.println(e.getButton());
		if (e.getButton() == MouseEvent.BUTTON1) {
			System.out.println("Releasing selected vertex");
			// Release the currently selected vertex
			selVertex = null;
		}
		// If right click, create an edge to the vertex it was released on
		// TODO: Get name from a GUI box
		else if (e.getButton() == MouseEvent.BUTTON3) {
			System.out.println("Adding an edge");
			for (VertexShape vertex: vertices) {
				if (vertex.getEllipse().getBounds().contains(e.getPoint())) {
					System.out.println("Found a destination vertex");
					String name = ""+(edges.size()+1);
					System.out.println(selVertex.getName());
					System.out.println(vertex.getName());
                EdgeShape eS = new EdgeShape(selVertex, vertex, name);
	          		edges.add(eS);

					// Release the selected vertex
					selVertex = null;
					repaint();
	        	}
			}
		}
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
			for (VertexShape vertex: vertices) {
				System.out.println("Looking for vertexes");
				if (vertex.getEllipse().getBounds().contains(e.getPoint())) {

					// If they are double clicking, toggle the accept state
					if (e.getClickCount() == 2) {
						vertex.toggleAccept();
					}
					foundVertex = true;
					repaint();
        }
      }

			} else if (e.getButton() == MouseEvent.BUTTON3) {
          if (edgeStarted == false) {
            for (VertexShape vertex: vertices) {
      				if (vertex.getEllipse().getBounds().contains(e.getPoint())) {
                  VertexShape fromVertex = vertex;
                  edgeStarted = true;
      	      }
            }
          } else if (edgeStarted == true) {
              for (VertexShape vertex: vertices) {
        				if (vertex.getEllipse().getBounds().contains(e.getPoint())) {
                    VertexShape toVertex = vertex;
        	      }
              }
              String edgeName = "" + edges.size();
              edges.add(new EdgeShape(fromVertex, toVertex, edgeName));
              edgeStarted = false;
          }


			// If the user didn't click on a vertex, make a new one
			if (!foundVertex) {
        System.out.println("NO VERTEX");
				String name = ""+(vertices.size()+1);
				vertices.add(new VertexShape(e.getX(),e.getY(), name, false));
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
		if (e.getButton() == 0) {
			// Only move if a vertex was clicked on before the mouse was dragged
			  if (selVertex != null) {
				  selVertex.setY(e.getY());
				  selVertex.setX(e.getX());
				  selVertex.moveShape(preX + e.getX(), preY + e.getY());

				  repaint();
			  }
		}
	}
}
