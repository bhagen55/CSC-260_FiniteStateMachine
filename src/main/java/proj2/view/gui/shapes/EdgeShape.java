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

import proj2.view.gui.shapes.VertexShape;

/*
* Represents shape of an edge for the gui
*/
public class EdgeShape extends Component{

	private String name;

	private VertexShape origin;
	private VertexShape destination;

	private QuadCurve2D edge;

	public EdgeShape(VertexShape o, VertexShape d, String n) {

		this.name = n;

    	this.origin = o;
    	this.destination = d;

		int orgX = origin.getX();
		int orgY = origin.getY();
		int destX = destination.getX();
		int destY = destination.getY();
		int contX = getCont(orgX, destX);
		int contY = getCont(orgY, destY);

		int selfLoopContX = origin.getX() + 100;
		int selfLoopContY = origin.getY() + 100;

		edge = new QuadCurve2D.Double(orgX,orgY,contX,contY,destX,destY);
	}

	private int getCont(int start, int end) {
		return (start + end) / 2;
	}

	private int getAngle() {

	int orgX = origin.getX();
	int orgY = origin.getY();
	int destX = destination.getX();
	int destY = destination.getY();

    int angle = (int) Math.toDegrees(Math.atan2(orgX-destX,orgY-destY));

	// sets correct initial rotation
	angle += 90;

	// sets rotation to correct direction
	angle = getInverse(angle);

    return angle;
}

private int getInverse(int num) {
	if (num < 0) {
		num += 2*Math.abs(num);
	} else {
		num = num - 2*num;
	}

	return num;
}

 	public void paintShape(Graphics g) {

    	Graphics2D g2d = (Graphics2D) g;

		int orgX = origin.getX();
		int orgY = origin.getY();
		int destX = destination.getX();
		int destY = destination.getY();
		int contX = getCont(orgX, destX);
		int contY = getCont(orgY, destY);

		String pointer = "  ---> ";

		// Check to see if this is a self loop
		if (orgX != destX && orgY != destY) {
			//int contX = getCont(orgX, orgY);
			//int contY = getCont(destX, destY);

			edge.setCurve(orgX,orgY,contX,contY,destX,destY);
		}
		else {
			int selfLoopContX = origin.getX() + 100;
			int selfLoopContY = origin.getY() + 100;
		}

		Font font = new Font(null, Font.PLAIN, 16);
		AffineTransform rotater = new AffineTransform();
		System.out.println(getAngle());
		rotater.rotate(Math.toRadians(getAngle()), 0, 0);
		Font rotatedFont = font.deriveFont(rotater);
		g2d.setFont(rotatedFont);
		g2d.drawString(name + pointer,contX,contY);

    	g2d.setColor(Color.BLACK);

		g2d.draw(edge);
	}
}
