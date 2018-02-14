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

	public EdgeShape(VertexShape o, VertexShape d, String n) {

		this.name = n;

    	this.origin = o;
    	this.destination = d;
		//System.out.println("----- GOT HERE -----");
		//edge.setCurve(origin.getX(), origin.getY(), 0, 0, destination.getX(), destination.getY());

		// System.out.println("made an edge!");
		// System.out.println("origin x " + origin.getX());
		// System.out.println("origin y " + origin.getY());
		// System.out.println("destination x " + destination.getX());
		// System.out.println("destination y " + destination.getY());
		// System.out.println();
		// System.out.println();

		int orgX = origin.getX();
		int orgY = origin.getY();
		int destX = destination.getX();
		int destY = destination.getY();
		int contX = getCont(orgX, destX);
		int contY = getCont(orgY, destY);

		int selfLoopContX = origin.getX() + 100;
		int selfLoopContY = origin.getY() + 100;

		edge = new QuadCurve2D.Double(orgX,orgY,contX,contY,destX,destY);
		//edge.setCurve(;

	}

	private int getCont(int start, int end) {
		if (start > end) {
			return ( ((start - end)/2) + end );
		} else {
			return ( ((end - start)/2) + start );
		}
	}

 	private Point calcStartEndPoint() {
    	// Get centers of vertices
    	Point orCenter = new Point(origin.getX(), origin.getY());
    	Point desCenter = new Point(destination.getX(), destination.getY());

    	// Get projection to destination from origin
    	return null;
	}

 	public void paintShape(Graphics g) {

    	Graphics2D g2d = (Graphics2D) g;

		int orgX = origin.getX();
		int orgY = origin.getY();
		int destX = destination.getX();
		int destY = destination.getY();

		if (orgX != destX && orgY != destY) {
			int contX = getCont(orgX, orgY);
			int contY = getCont(destX, destY);

			edge.setCurve(orgX,orgY,contX,contY,destX,destY);
		} else {
			int selfLoopContX = origin.getX() + 100;
			int selfLoopContY = origin.getY() + 100;

			edge.setCurve(orgX,orgY,selfLoopContX,orgX,orgY,selfLoopContY,destX,destY);
		}

    	g2d.setColor(Color.BLACK);


		g2d.draw(edge);
		//g2d.draw(new Line2D.Double(origin.getX(),origin.getY(),destination.getX(),destination.getY()));
  }
}
