package proj2.view.gui;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.LinkedList;

import java.io.*;

import proj2.document.*;
import proj2.view.gui.shapes.*;
import proj2.view.gui.Observer;
import proj2.FileFormatController;

/*
* Extension of JPanel that handles drawing of states and vertex objects
* Adapted from this tutorial: https://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html
* Dragging: http://www.java2s.com/Code/Java/Event/MoveShapewithmouse.htm
*/
public class DrawPanel extends JPanel implements Observer, MouseListener, MouseMotionListener, ActionListener {

  	// Holds shadow classes of document vertices
  	private ArrayList<VertexShape> vertexShapes;

	// Holds shadow classes of edges
	private ArrayList<EdgeShape> edgeShapes;

  	// Holds the current selected vertex while it is being dragged
  	private VertexShape selVertex;

    // Holds the document that the view edits
    private Document doc;

    // Holds the File Format controller
    private FileFormatController ffc;

  	// Holds last known location of mouse for dragging
  	private int preX;
  	private int preY;
  	// private int preXDrag;
  	// private int preYDrag;

    // Boolean value to know if edge is being created or set
    private boolean edgeStarted;


    private VertexShape fromVertex;
    private VertexShape toVertex;
    private boolean foundVertex;

    // Keeping track of dragging vertexes
    private boolean dragging;

    // Holds last mouse button pushed
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

    /*
    * Save/Load Button and Path
    */
    private JButton saveButton;
    private JButton loadButton;

    private JTextField savePath;
    private JTextField loadPath;


	public DrawPanel(Document d, FileFormatController filecontroller) {

    	System.out.println("Setting up");

        // Set border of the panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Add text entry boxes and labels
		vertexField = new JTextField("a");
		edgeField = new JTextField("a");
		vertexFieldName = new JLabel("New Vertex Name");
		edgeFieldName = new JLabel("New Edge Name");

        // Add save/load buttons and paths
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        savePath = new JTextField("fsm.txt");
        loadPath = new JTextField("fsm.txt");

        vertexShapes = new ArrayList<VertexShape>();

		edgeShapes = new ArrayList<EdgeShape>();

        doc = d;

        ffc = filecontroller;

        // Call the FSM save when button pressed
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Call save fsm here
                try {
                    ffc.saveFile(savePath.getText());
                }
                catch (FileNotFoundException err) {
                    System.out.println("File save error");
                }
                System.out.println("Saving");
            }
        });

        // Call the FSM load when button pressed
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Call load fsm here
                // Get the save path from savePath
                try {
                    ffc.loadFile(savePath.getText());
                }
                catch (FileNotFoundException err) {
                    System.out.println("File load error");
                }
                System.out.println("Loading");
            }
        });

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

        // Add all the vertices from the model as vertexshapes
        for (Vertex vertex: model) {

            // Check if vertex is a start vertex
            boolean isStart = false;
            if (doc.getFirstVertex().getName().equals(vertex.getName())) {
                isStart = true;
            }

            vertexShapes.add(new VertexShape(vertex.getX(),vertex.getY(), vertex.getName(), vertex.canAccept(), isStart));
        }

        // Go through all the vertexshapes and add their edges from the model
        for (VertexShape vertex: vertexShapes) {

            // Get the vertex that corresponds with the current vertexshape
            Vertex currVertex = model.get(vertexShapes.indexOf(vertex));

            // Get the edges of the vertex
            ArrayList<Edge> vertexEdges = currVertex.getEdges();
            for (Edge edge: vertexEdges) {

                VertexShape endVertex = null;
                for (VertexShape endShape: vertexShapes) {
                    if (endShape.getName().equals(edge.getGoingTo())) {
                        endVertex = endShape;
                    }
                }
                edgeShapes.add(new EdgeShape(vertex, endVertex, edge.getWeight()));
            }
        }
        repaint();
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
		vertexField.setBounds(2,1, 120, 22);
		edgeField.setBounds(152, 1, 120, 22);
		add(vertexField);
		add(edgeField);
		vertexFieldName.setBounds(2, 20, 120, 20);
		edgeFieldName.setBounds(152, 20, 120, 20);
		add(vertexFieldName);
		add(edgeFieldName);

        // Paints the save/load buttons and paths
        saveButton.setBounds(300, 1, 100, 20);
        loadButton.setBounds(400, 1, 100, 20);
        add(saveButton);
        add(loadButton);

        savePath.setBounds(300, 21, 100, 20);
        loadPath.setBounds(400, 21, 100, 20);
        add(savePath);
        add(loadPath);

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

            // If a vertex was being dragged, send its new location to the document
            if (dragging) {
                doc.moveVertex(selVertex.getName(), selVertex.getX(), selVertex.getY());
            }
            dragging = false;
            selVertex = null;
		}
		// If right click, create an edge to the vertex it was released on
		else if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println();
            System.out.println("RELEASE x " + e.getX());
            System.out.println("RELEASE y " + e.getY());
            System.out.println();
			for (VertexShape vertex: vertexShapes) {
				if (vertex.getEllipse().getBounds().contains(e.getPoint())) {
					System.out.println("RELEASED vertex is " + vertex.getName());
					vertexName = edgeField.getText();

                    toVertex = vertex;
	        	}
			}
            System.out.println("fromVertex name is " + fromVertex.getName());
            System.out.println("toVertex name is " + toVertex.getName());
            System.out.println("fromVertex pos "+"("+fromVertex.getX()+","+fromVertex.getY()+")");
            System.out.println("toVertex pos "+"("+toVertex.getX()+","+toVertex.getY()+")");

            // Attempting to add edge
			doc.addEdge(fromVertex.getName(), toVertex.getName(), vertexName);
		}
        repaint();
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
                        doc.toggleAccept(vertex.getName());
					}
					foundVertex = true;
                }
            }

            if (!foundVertex) {
                //System.out.println("NO VERTEX");
  				String name = vertexField.getText();
                System.out.println("Attempting to add vertex");
                doc.addVertex(name, e.getX(), e.getY());
  			}
        repaint();
		}
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
            // int Xoffset = e.getX() - preX;
            // int Yoffset = e.getY() - preY;

            dragging = true;
            selVertex.moveShape(e.getX(), e.getY());
		}
        repaint();
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

    public void actionPerformed(ActionEvent e) {

    }
}
