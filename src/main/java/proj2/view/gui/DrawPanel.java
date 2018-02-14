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

    // Boolean value to know if edge is being created or set
    private boolean edgeStarted;

    private VertexShape toVertex;
    private boolean foundVertex;

    // Keeping track of dragging vertexes
    private boolean dragging;

    // Holds last mouse button pushed
    private int buttonNumber;

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

    private static final int RIGHT_CLICK = 3;
    private static final int LEFT_CLICK = 1;


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

        savePath = new JTextField("fsm");
        loadPath = new JTextField("fsm");

        vertexShapes = new ArrayList<VertexShape>();

		edgeShapes = new ArrayList<EdgeShape>();

        doc = d;

        ffc = filecontroller;

        // Add mouse listener to the panel to deal with mouse events
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

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

    }

    /**
    * Updates this view based on information from the document
    *
    */
    public void update()
    {
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

        for (EdgeShape edge: edgeShapes) {
          edge.paintShape(g);
        }
        for (VertexShape vertex: vertexShapes) {
            vertex.paintShape(g);
        }
    }

    private VertexShape search(MouseEvent e) {
        for (VertexShape vertex: vertexShapes) {
    		if (vertex.getEllipse().getBounds().contains(e.getPoint())) {
    			// Save the vertex as the currently selected one.
                 return vertex;
        	}
        }
        return null;
    }

	/**
	* Indicates the start of a drag.
	* Saves the vertex that is being pressed to be modified by the dragging
	* handler.
	*
	* @param e mouse event passed by mouse listener
	*/
    public void mousePressed(MouseEvent e) {

        // Used later to see if it is a right click or left click
        buttonNumber = e.getButton();

    	// Check if actually pressing on a vertex
        VertexShape inBounds = search(e);
    	if (inBounds != null) {
            selVertex = inBounds;
        } else {
            selVertex = null;
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
		if (e.getButton() == LEFT_CLICK) {
            // If a vertex was being dragged, send its new location to the document
            if (dragging && selVertex != null) {
                doc.moveVertex(selVertex.getName(), selVertex.getX(), selVertex.getY());
                dragging = false;
            }
		}


		// If right click, create an edge to the vertex it was released on
		else if (e.getButton() == RIGHT_CLICK) {

            VertexShape inBounds = search(e);
        	if (inBounds != null) {
                String vertexName = edgeField.getText();
                toVertex = inBounds;

                // Adds edge from selVertex to tovertex
                if (selVertex != null) {
                    doc.addEdge(selVertex.getName(), toVertex.getName(), vertexName);
                }
            }
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
		if (e.getButton() == LEFT_CLICK) {
			foundVertex = false;

			// check if the user is clicking on a vertex
            VertexShape inBounds = search(e);
            if (inBounds != null) {
                if (e.getClickCount() == 2) {
                    doc.toggleAccept(inBounds.getName());
                }
                foundVertex = true;
            }

            if (foundVertex == false) {
  				String vertexName = vertexField.getText();
                doc.addVertex(vertexName, e.getX(), e.getY());
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
		if (buttonNumber != RIGHT_CLICK && selVertex != null) {
            dragging = true;
            selVertex.moveShape(e.getX(), e.getY());
		}
        repaint();
    }

    /**
    * Necessary implemented methods.
    */
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void actionPerformed(ActionEvent e) {}

}
