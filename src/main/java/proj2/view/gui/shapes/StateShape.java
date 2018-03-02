package proj2.view.gui.shapes;

import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;


/**
* Represents shape of a state for the gui
*/
public class StateShape extends Component implements Shape {

	// Unique string name that will be shown in the middle of the shape
	private String name;

	// Boolean to store whether or not this is an accept state
	private boolean isAccepting;

	// Boolean to store if the state is a start state
	private boolean isStart;

	// Boolean to store if the state is the current state in simulation
	private boolean isCurrent;

	// Position information (Refers to the center of the ellipse)
	private int xPos;
	private int yPos;
	private int radius;

	// Amount to offset the second ellipse when shape is an accpet state
	private int acceptOffset;

	// Graphics object used to represent shape
	Ellipse2D ellipse;
	Ellipse2D acceptEllipse;

	private static final int DEFAULT_RADIUS = 50;
	private static final int DEFAULT_OFFSET = 10;
	private static final int DOUBLE_MULT = 2;
	private static final int DEFAULT_FONT_SIZE = 12;


	/**
	* Constructs a StateShape object
	*
	* @param xPos x coordinate
	* @param yPos y coordinate
	* @param name name of state
	* @param isAccepting whether or not this is an accept state
	* @param isStart whether or not this is a start state
	*/
	public StateShape(int xPos, int yPos, String name, boolean isAccepting, boolean isStart) {

		this.name = name;

		this.xPos = xPos;
		this.yPos = yPos;

		this.radius = DEFAULT_RADIUS;

		this.acceptOffset = DEFAULT_OFFSET;

		this.isAccepting = isAccepting;

		this.isStart = isStart;

		// Construct the ellipse to represent this shape
		ellipse = makeEllipse(xPos, yPos, radius);

		// Construct the offset ellipse for accept state
		acceptEllipse = makeEllipse(xPos, yPos, radius+acceptOffset);
	}

	/**
	* Makes an ellipse but offsets to set given coordinates in its center
	*
	* @param xPos the x coordinate for the center of the state
	* @param yPos the y coordinate for the center of the state
	* @param radius the radius of the state
	*
	* @return an ellipse representation fo the state with the given
	* coordinates and size.
	*/
	private Ellipse2D.Double makeEllipse(int xPos, int yPos, int radius) {
		return new Ellipse2D.Double(xPos - radius, yPos - radius, radius*2, radius*2);
	}

	/**
    * Changes coordinates of state object
    *
    * @param xPos the x coordinate to move the center of the state to
    * @param yPos the y coordinate to move the center of the state to
    */
    public void moveShape(int xPos, int yPos) {
    	ellipse = makeEllipse(xPos, yPos, radius);
    	acceptEllipse = makeEllipse(xPos, yPos, radius+acceptOffset);
    	this.xPos = xPos;
    	this.yPos = yPos;
    }

    /**
    * Returns name of the state
    *
    * @return the string name of the state
    */
    public String getName() {
    	return name;
    }

    /**
    * Get the x coordinate of the state object
    *
    * @return the x position of the center of the state
    */
    public int getX() {
        return this.xPos;
    }

    /**
    * Get the y coordinate of the state object
    *
    * @return the y position of the center of the state
    */
    public int getY() {
        return this.yPos;
    }

    /**
    * Set the x coordinate of the state object
    *
    * @param newX the new x coordinate to move the state to
    */
    public void setX(int newX) {
        xPos = getXOffset(newX);
        ellipse = makeEllipse(xPos, yPos, radius);
    }

    /**
    * Set the y coordinate of the state object
    *
    * @param newY the new y coordiante to move the state to
    */
    public void setY(int newY) {
        yPos = getYOffset(newY);
        ellipse = makeEllipse(xPos, yPos, radius);
    }

    /**
    * TODO: What does this do?
    */
    private int getXOffset(int newX) {
        return newX + (getWidth()/DOUBLE_MULT);
    }

    /**
    * TODO: What does this do?
    */
    private int getYOffset(int newY) {
        return newY + (getHeight()/DOUBLE_MULT);
    }

    /**
    * Gets the width of the state object
    *
    * @return the width of the state
    */
    public int getWidth() {
		if (isAccepting) {
			return (radius*DOUBLE_MULT)+acceptOffset;
		}
		else {
			return radius*DOUBLE_MULT;
		}
    }

    /**
    * Gets the height of the state object
    *
    * @return the height of the state
    */
    public int getHeight() {
		if (isAccepting) {
			return (radius*DOUBLE_MULT)+acceptOffset;
		}
		else {
			return radius*DOUBLE_MULT;
		}
    }

    /*
    * Toggles whether this state is an accepting state
    * If toggled, the shape needs to be re-drawn
    */
    public void toggleAccept() {
    	isAccepting = !isAccepting;
    }

	/**
	* Toggles whether this state is the current in simulation
	* If toggled, the shape needs to be re-drawn (in simulation)
	*/
	public void toggleCurrent() {
		isCurrent = !isCurrent;
	}

    /**
    * Get whether or not the state object is an accepting state
    *
    * @return whether or not this state is an accept state
    */
    public boolean isAcceptState() {
    	return isAccepting;
    }

	/**
	* Get whether or not the state object is the start state
	*
	* @return whether or not the state is the start state
	*/
	public boolean isStart() {
		return isStart;
	}

    /**
    * Get an Ellipse2D representation of the state
    *
    * @return an Ellipse2D representation of the state
    */
    public Ellipse2D getEllipse() {
    	return ellipse;
    }

	/**
	* Pass through a contains command to the shape
	*
	* @return whether or not the click is contained
	* within the shape
	*/
	public boolean contains(double x, double y) {
		return ellipse.contains(x, y);
	}

	public void paintColor(Color c) {

	}

    /*
    * Does the actual painting of this object on a JPanel
    *
    * @param g graphics object used by repaint()
    */
    public void paintShape(Graphics g){

		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font(null, Font.PLAIN, DEFAULT_FONT_SIZE);
		g2d.setFont(font);


		g2d.setColor(Color.WHITE);
		g2d.fill(ellipse);

		if(isStart) {
			g2d.setColor(Color.RED);
		} else if (isCurrent) {

		} else {
			g2d.setColor(Color.BLACK);
		}

		g2d.draw(ellipse);
		g2d.drawString(name,xPos,yPos);

		if(isAccepting) {
			g2d.draw(acceptEllipse);
		}
	}
}
