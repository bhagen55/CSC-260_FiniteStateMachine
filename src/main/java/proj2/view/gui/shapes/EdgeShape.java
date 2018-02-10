package proj2.view.gui.shapes;

import java.awt.Point;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.QuadCurve2D;
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
		System.out.println("made an edge!");
		edge = new QuadCurve2D.Double();
		this.name = n;

    	this.origin = o;
    	this.destination = d;
		//System.out.println("----- GOT HERE -----");
		edge.setCurve(origin.getX(), origin.getY(), 0, 0, destination.getX(), destination.getY());
		repaint();
	}

 	private Point calcStartEndPoint() {
    	// Get centers of vertices
    	Point orCenter = new Point(origin.getX(), origin.getY());
    	Point desCenter = new Point(destination.getX(), destination.getY());

    	// Get projection to destination from origin
    	return null;
	}

 	public void paint(Graphics g) {

    	Graphics2D g2d = (Graphics2D) g;

    	g2d.setColor(Color.BLACK);


			g2d.drawLine(origin.getX(),origin.getY(),destination.getX(),destination.getY());
  }
}
