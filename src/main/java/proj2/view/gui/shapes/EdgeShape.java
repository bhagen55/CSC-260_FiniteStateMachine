package proj2.view.gui.shapes;

import java.awt.Point;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Line2D;
import java.awt.Color;

import proj2.view.gui.shapes.VertexShape;

/*
* Represents shape of an edge for the gui
*/
public class EdgeShape extends Component{

	private String name;

	private VertexShape origin;
	private VertexShape destination;

	private QuadCurve2D edge;

	private JTextField name;

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

		name = new JTextField(n);
		name.setBounds(contX, contY, 60, 20);

		int selfLoopContX = origin.getX() + 100;
		int selfLoopContY = origin.getY() + 100;

		edge = new QuadCurve2D.Double(orgX,orgY,contX,contY,destX,destY);
	}

	private int getCont(int start, int end) {
		if (start > end) {
			return ( ((start - end)/2) + end );
		} else {
			return ( ((end - start)/2) + start );
		}
	}

 	public void paintShape(Graphics g) {

    	Graphics2D g2d = (Graphics2D) g;

		int orgX = origin.getX();
		int orgY = origin.getY();
		int destX = destination.getX();
		int destY = destination.getY();

		// Check to see if this is a self loop
		if (orgX != destX && orgY != destY) {
			int contX = getCont(orgX, orgY);
			int contY = getCont(destX, destY);

			edge.setCurve(orgX,orgY,contX,contY,destX,destY);
		}
		else {
			int selfLoopContX = origin.getX() + 100;
			int selfLoopContY = origin.getY() + 100;
		}

		g2d.draw(name);

    	g2d.setColor(Color.BLACK);

		g2d.draw(edge);
	}
}
