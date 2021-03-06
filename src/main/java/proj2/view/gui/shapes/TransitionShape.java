package proj2.view.gui.shapes;

import java.awt.Point;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import javax.swing.JLabel;

import proj2.view.gui.shapes.StateShape;

/**
* Represents shape of an transition for the gui
*/
public class TransitionShape extends Component implements Shape {

	private String name;

	private StateShape origin;
	private StateShape destination;

	private QuadCurve2D transition;

	private static final int DOUBLE_MULT = 2;
	private static final int STANDARD_FONT_SIZE = 12;
	private static final int LOOP_OFFSET = 90;
	private static final int ROTATE_CORRECTION = 90;

	Color lineColor;
	Color textColor;

	/**
	* TransitionShape constructor
	*
	* @param o origin StateShape
	* @param d destination StateShape
	* @param n name/transition name for transition
	* @param lineColor color of transition line
	* @param textColor color of transition text
	*/
	public TransitionShape(StateShape o, StateShape d, String n,
							Color lineColor, Color textColor) {

		this.name = n;
    	this.origin = o;
    	this.destination = d;

		int orgX = origin.getX();
		int orgY = origin.getY();
		int destX = destination.getX();
		int destY = destination.getY();
		int contX = getCont(orgX, destX);
		int contY = getCont(orgY, destY);

		this.lineColor = lineColor;
		this.textColor = textColor;

		transition = new QuadCurve2D.Double(orgX,orgY,contX,contY,destX,destY);
	}

	/**
	* Gets control point for transitions
	*
	* @return halfway point between points
	*
	* @param start start point
	* @param end end point
	*/
	private int getCont(int start, int end) {
		return (start + end) / DOUBLE_MULT;
	}

	/**
	* Gets angle to properly represent text
	*
	* @return calculated angle
	*/
	private int getAngle() {

		int orgX = origin.getX();
		int orgY = origin.getY();
		int destX = destination.getX();
		int destY = destination.getY();

    	int angle = (int) Math.toDegrees(Math.atan2(orgX-destX,orgY-destY));

		// sets correct initial rotation
		angle += ROTATE_CORRECTION;

		// sets rotation to correct direction
		angle = getInverse(angle);

		return angle;
	}

	/**
	* Gets angle to correct rotation of text
	*
	* @return corrected angle
	*
	* @param num angle to correct
	*/
	private int getInverse(int num) {
		if (num < 0) {
			num += DOUBLE_MULT*Math.abs(num);
		} else {
			num = num - DOUBLE_MULT*num;
		}

		return num;
	}

	/**
	* Paints the shapes needed to represent TransitionShape
	*
	* @param g graphics object
	*/
 	public void paintShape(Graphics g) {

    	Graphics2D g2d = (Graphics2D) g;

		int orgX = origin.getX();
		int orgY = origin.getY();
		int destX = destination.getX();
		int destY = destination.getY();
		int contX = getCont(orgX, destX);
		int contY = getCont(orgY, destY);

		String pointer = "  ---> ";
		Font font = new Font(null, Font.PLAIN, STANDARD_FONT_SIZE);


		// Check to see if this is a self loop
		if (orgX != destX && orgY != destY) {

			transition.setCurve(orgX,orgY,contX,contY,destX,destY);

			AffineTransform rotater = new AffineTransform();
			// System.out.println(getAngle());
			rotater.rotate(Math.toRadians(getAngle()), 0, 0);
			Font rotatedFont = font.deriveFont(rotater);
			g2d.setFont(rotatedFont);
			g2d.setColor(textColor);
			g2d.drawString(name + pointer,contX,contY);
			g2d.setColor(lineColor);
			g2d.draw(transition);

		// this is a self loop
		} else {
			g2d.draw(new Ellipse2D.Double(orgX,orgY,LOOP_OFFSET,LOOP_OFFSET));
			g2d.drawString(name, orgX+LOOP_OFFSET,orgY+LOOP_OFFSET);
		}


		g2d.setFont(font);
	}
}
