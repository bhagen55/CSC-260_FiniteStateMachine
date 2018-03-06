package proj2.view.gui;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
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
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.LinkedList;

import java.io.*;

import proj2.document.*;
import proj2.simulator.*;
import proj2.view.gui.shapes.*;
import proj2.view.gui.Observer;
import proj2.filehandler.concretefilehandler.TextSave;
import proj2.view.gui.menus.SaveMenu;
import proj2.view.theme.*;

/*
* Extension of JPanel that handles drawing of states and state objects
* Adapted from this tutorial: https://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html
* Dragging: http://www.java2s.com/Code/Java/Event/MoveShapewithmouse.htm
*/
public class DrawPanel extends JPanel implements Observer, MouseListener, MouseMotionListener, ActionListener, Printable {

    // Holds pointer to itself
    private DrawPanel gui;

  	// Holds shadow classes of document vertices
  	private ArrayList<StateShape> stateShapes;

	// Holds shadow classes of transitions
	private ArrayList<TransitionShape> transitionShapes;

  	// Holds the current selected state while it is being dragged
  	private StateShape selState;

    // Holds the document that the view edits
    private Document doc;

    // Holds the File Format controller
    private TextSave ts;

    // Boolean value to know if transition is being created or set
    private boolean transitionStarted;

    private StateShape toState;
    private boolean foundState;

    // Keeping track of dragging statees
    private boolean dragging;

    // Holds last mouse button pushed
    private int buttonNumber;

	/*
	* Text Box
	*/
	private JTextField stateField;
	private JTextField transitionField;
	private String stateNameEntry;
	private String transitionNameEntry;

	private JLabel stateFieldName;
	private JLabel transitionFieldName;

    private String batchSimulatorEntry;
    private JTextField batchSimulatorField;
    private JLabel batchSimulatorFieldName;

    /*
    * Simulators
    */
    private JButton batchSimulatorButton;

    /*
    * Save/Load Button and Path
    */
    private JButton saveButton;
    private JButton loadButton;

    private JTextField savePath;
    private JTextField loadPath;

	/*
	* Theme manager
	*/
	private ThemeManager themeManager;
	private Theme currTheme;
	private JButton themeButton;

    private static final int RIGHT_CLICK = 3;
    private static final int LEFT_CLICK = 1;

    private boolean simulationRunning = false;
    private boolean resettingScreen = false;
    private LinkedList example = new LinkedList();


	public DrawPanel(Document d, TextSave textsaver) {

        example.add("a");
        example.add("b");
        example.add("c");
        example.add("d");

    	System.out.println("Setting up");

		gui = this;

        // Set border of the panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Add text entry boxes and labels
		stateField = new JTextField("a");
		transitionField = new JTextField("a");
		stateFieldName = new JLabel("New State Name");
		transitionFieldName = new JLabel("New Transition Name");

        batchSimulatorField = new JTextField("");
        batchSimulatorFieldName = new JLabel("New Simulator Sequence");

        // Add simulator buttons
        batchSimulatorButton = new JButton("Batch Simulator");

        // Add save/load buttons and paths
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        savePath = new JTextField("fsm");
        loadPath = new JTextField("fsm");

		// Add theme manager stuff
		themeButton = new JButton("Open Theme Chooser");
		themeManager = new ThemeManager(gui);
		currTheme = themeManager.getTheme();

        stateShapes = new ArrayList<StateShape>();

		transitionShapes = new ArrayList<TransitionShape>();

        doc = d;

        ts = textsaver;

        // Add mouse listener to the panel to deal with mouse events
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // Call the Batch Simulator creation when button pressed
        batchSimulatorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BatchSimulator bs = new BatchSimulator(getThis(), doc, example);
            }
        });

        // Call the FSM save when button pressed
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SaveMenu sm = new SaveMenu(gui, doc);
            }
        });

        // Call the FSM load when button pressed
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // // Get the save path from savePath
                // try {
                //     ts.loadFile(savePath.getText());
                // }
                // catch (FileNotFoundException err) {
                //     System.out.println("File load error");
                // }
                // System.out.println("Loading");
            }
        });

		themeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themeManager.showMenu();
			}
		});

    }

    private DrawPanel getThis() {
        return this;
    }

    /**
    * Updates this view based on information from the document
    *
    */
    public void update()
    {
        // Clear out old shapes
        stateShapes = new ArrayList<StateShape>();
        transitionShapes = new ArrayList<TransitionShape>();

        // Holds the current model that the view is based off
        LinkedList<State> model = doc.getModel();

		currTheme = themeManager.getTheme();

        // Add all the vertices from the model as stateshapes
        for (State state: model) {

            // Check if state is a start state
            boolean isStart = false;
            if (doc.getFirstState().getName().equals(state.getName())) {
                isStart = true;
            }

            stateShapes.add(new StateShape(state.getX(),state.getY(), state.getName(), state.canAccept(), isStart,
							currTheme.getStateOutlineColor(), currTheme.getStateFillColor(),
							currTheme.getStateTextColor(), currTheme.getStateAcceptColor(),
							currTheme.getStateStartColor()));
        }

        // Go through all the stateshapes and add their transitions from the model
        for (StateShape state: stateShapes) {

            // Get the state that corresponds with the current stateshape
            State currState = model.get(stateShapes.indexOf(state));

            // Get the transitions of the state
            ArrayList<Transition> stateTransitions = currState.getTransitions();
            for (Transition transition: stateTransitions) {

                StateShape endState = null;
                for (StateShape endShape: stateShapes) {
                    if (endShape.getName().equals(transition.getGoingToName())) {
                        endState = endShape;
                    }
                }
                transitionShapes.add(new TransitionShape(state, endState, transition.getWeight(),
									currTheme.getTransLineColor(), currTheme.getTransTextColor()));
            }
        }
        repaint();
    }

    private LinkedList convertArrayListToLinkedList(ArrayList aL) {
        LinkedList toReturn = new LinkedList();
        int size = aL.size();
        for (int i=0;i<size;i++) {
            toReturn.add(aL.get(i));
        }
        return toReturn;
    }

    public LinkedList<StateShape> getStates() {
        LinkedList<StateShape> toReturn = (LinkedList<StateShape>)convertArrayListToLinkedList(stateShapes);
        return toReturn;
    }

    public LinkedList<TransitionShape> getTransitions() {
        LinkedList<TransitionShape> toReturn = (LinkedList<TransitionShape>)convertArrayListToLinkedList(transitionShapes);
        return toReturn;
    }


    /**
    * Moves state to the given x and y coords
    *
    * @param v the state to be moved
    * @param x x coordinate to be moved to
    * @param y y coordinate to be moved to
    */
    private void moveState(StateShape v, int x, int y){

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

        if (resettingScreen == true) {
            super.paintComponent(g);
            this.removeAll();
            resettingScreen = false;
        } else if (simulationRunning == true) {
            BatchSimulator bs = new BatchSimulator(getThis(), doc, example);
            bs.simulate();
        } else {

		// Paints the panel
    	super.paintComponent(g);

		// Paints the text boxes and their labels
		stateField.setBounds(2,1, 120, 22);
		transitionField.setBounds(152, 1, 120, 22);
		add(stateField);
		add(transitionField);
		stateFieldName.setBounds(2, 20, 120, 20);
		transitionFieldName.setBounds(152, 20, 120, 20);
		add(stateFieldName);
		add(transitionFieldName);

        batchSimulatorField.setBounds(700,740,100,20);
        add(batchSimulatorField);
        batchSimulatorFieldName.setBounds(700,760,100,20);
        add(batchSimulatorFieldName);

        // Paints the simulator buttons
        batchSimulatorButton.setBounds(700,780,100,20);
        add(batchSimulatorButton);

        // Paints the save/load buttons and paths
        saveButton.setBounds(300, 1, 100, 20);
        loadButton.setBounds(400, 1, 100, 20);
        add(saveButton);
        add(loadButton);

        savePath.setBounds(300, 21, 100, 20);
        loadPath.setBounds(400, 21, 100, 20);
        add(savePath);
        add(loadPath);

		// Paints theme manager button
		themeButton.setBounds(500, 1, 200, 20);
		add(themeButton);

		// Paints the vertices

        for (TransitionShape transition: transitionShapes) {
          transition.paintShape(g);
        }
        for (StateShape state: stateShapes) {
            state.paintShape(g);
        }

    }
    }

    private StateShape search(MouseEvent e) {
        for (StateShape state: stateShapes) {
    		if (state.getEllipse().getBounds().contains(e.getPoint())) {
    			// Save the state as the currently selected one.
                 return state;
        	}
        }
        return null;
    }

	/**
	* Indicates the start of a drag.
	* Saves the state that is being pressed to be modified by the dragging
	* handler.
	*
	* @param e mouse event passed by mouse listener
	*/
    public void mousePressed(MouseEvent e) {

        // Used later to see if it is a right click or left click
        buttonNumber = e.getButton();

    	// Check if actually pressing on a state
        StateShape inBounds = search(e);
    	if (inBounds != null) {
            selState = inBounds;
        } else {
            selState = null;
        }
    }

	/**
	* Indicates release of mouse.
	* Clears out the selected state at the end of a drag.
	*
	* @param e mouse event passed by mouse listener
	*/
    public void mouseReleased(MouseEvent e) {


		// If left click, just release the selected state
		if (e.getButton() == LEFT_CLICK) {
            // If a state was being dragged, send its new location to the document
            if (dragging && selState != null) {
                doc.moveState(selState.getName(), selState.getX(), selState.getY());
                dragging = false;
            }
		}


		// If right click, create an transition to the state it was released on
		else if (e.getButton() == RIGHT_CLICK) {

            StateShape inBounds = search(e);
        	if (inBounds != null) {
                String stateName = transitionField.getText();
                toState = inBounds;
                System.out.println(inBounds.getName());

                // Adds transition from selState to tostate
                if (selState != null) {
                    doc.addTransition(selState.getName(), toState.getName(), stateName);
                }
            }
		}

        repaint();
    }

	/**
	* Indicates the mouse has been clicked a number of times
	* Checks if the user is clicking on a state. If it isn't, creates a state
	* If the user double clicks on a state the accept state is toggled.
	*
	* @param e mouse event passed by mouse listener
	*/
	public void mouseClicked(MouseEvent e) {

		// Only look for left clicks
		if (e.getButton() == LEFT_CLICK) {
			foundState = false;

			// check if the user is clicking on a state
            StateShape inBounds = search(e);
            if (inBounds != null) {
                if (e.getClickCount() == 2) {
                    doc.toggleAccept(inBounds.getName());
                }
                foundState = true;
            }

            if (foundState == false) {
  				String stateName = stateField.getText();
                doc.addState(stateName, e.getX(), e.getY());
  			}
            repaint();
		}
    }

	/**
	* Indicates the mouse has been dragged with the button pressed down.
	* If a state was pressed on, this moves the state.
	*
	* @param e mouse event passed by mouse listener
	*/
    public void mouseDragged(MouseEvent e) {
		// Only move with a left click
		if (buttonNumber != RIGHT_CLICK && selState != null) {
            dragging = true;
            selState.moveShape(e.getX(), e.getY());
		}
        repaint();
    }

    /*
    * Necessary implemented methods
    */
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void actionPerformed(ActionEvent e) {}

    /**
    * Get a printable representation of the jpanel
    * but only with states and transitions
    *
    * @return bufferedimage representation of jpanel without buttons
    */
    public BufferedImage getPrintable() {
        BufferedImage bufimg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufimg.createGraphics();
        paintWithoutButtons(g);
        return bufimg;
    }

    /**
    * Paints only states and transitions
    *
    * @param g graphics object to use
    */
    private void paintWithoutButtons(Graphics g) {

        // Paints the panel
        super.paintComponent(g);

        for (TransitionShape transition: transitionShapes) {
            transition.paintShape(g);
        }
        for (StateShape state: stateShapes) {
            state.paintShape(g);
        }
    }
}
