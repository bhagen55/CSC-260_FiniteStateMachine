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
* Represents shape of a vertex for the gui
*/
public class VertexShape extends Component{

	// Unique string name that will be shown in the middle of the shape
	private String name;

	// Boolean to store whether or not this is an accept state
	private boolean isAccepting;

	// Boolean to store if the vertex is a start vertex
	private boolean isStart;

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
	* Constructs a VertexShape object
	*
	* @param xPos x coordinate
	* @param yPos y coordinate
	* @param name name of vertex
	* @param isAccepting whether or not this is an accept state
	* @param isStart whether or not this is a start state
	*/
	public VertexShape(int xPos, int yPos, String name, boolean isAccepting, boolean isStart) {

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
	* @param xPos the x coordinate for the center of the vertex
	* @param yPos the y coordinate for the center of the vertex
	* @param radius the radius of the vertex
	*
	* @return an ellipse representation fo the vertex with the given
	* coordinates and size.
	*/
	private Ellipse2D.Double makeEllipse(int xPos, int yPos, int radius) {
		return new Ellipse2D.Double(xPos - radius, yPos - radius, radius*2, radius*2);
	}

	/**
    * Changes coordinates of vertex object
    *
    * @param xPos the x coordinate to move the center of the vertex to
    * @param yPos the y coordinate to move the center of the vertex to
    */
    public void moveShape(int xPos, int yPos) {
    	ellipse = makeEllipse(xPos, yPos, radius);
    	acceptEllipse = makeEllipse(xPos, yPos, radius+acceptOffset);
    	this.xPos = xPos;
    	this.yPos = yPos;
    }

    /**
    * Returns name of the vertex
    *
    * @return the string name of the vertex
    */
    public String getName() {
    	return name;
    }

    /**
    * Get the x coordinate of the vertex object
    *
    * @return the x position of the center of the vertex
    */
    public int getX() {
        return this.xPos;
    }

    /**
    * Get the y coordinate of the vertex object
    *
    * @return the y position of the center of the vertex
    */
    public int getY() {
        return this.yPos;
    }

    /**
    * Set the x coordinate of the vertex object
    *
    * @param newX the new x coordinate to move the vertex to
    */
    public void setX(int newX) {
        xPos = getXOffset(newX);
        ellipse = makeEllipse(xPos, yPos, radius);
    }

    /**
    * Set the y coordinate of the vertex object
    *
    * @param newY the new y coordiante to move the vertex to
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
    * Gets the width of the vertex object
    *
    * @return the width of the vertex
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
    * Gets the height of the vertex object
    *
    * @return the height of the vertex
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
    * Toggles whether this vertex is an accepting state
    * If toggled, the shape needs to be re-drawn
    */
    public void toggleAccept() {
    	isAccepting = !isAccepting;
    }

    /**
    * Get whether or not the vertex object is an accepting state
    *
    * @return whether or not this vertex is an accept state
    */
    public boolean isAcceptState() {
    	return isAccepting;
    }

    /**
    * Get an Ellipse2D representation of the vertex
    *
    * @return an Ellipse2D representation of the vertex
    */
    public Ellipse2D getEllipse() {
    	return ellipse;
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
		}
		else {
			g2d.setColor(Color.BLACK);
		}

		g2d.draw(ellipse);
		g2d.drawString(name,xPos,yPos);

		if(isAccepting) {
			g2d.draw(acceptEllipse);
		}
	}
}
