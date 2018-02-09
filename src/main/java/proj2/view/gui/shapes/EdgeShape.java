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

	private QuadCurve2D.Double edge;

	public EdgeShape(VertexShape o, VertexShape d, String name) {
		this.name = name;

    	this.origin = o;
    	this.destination = d;

		edge.setCurve(origin.getX(), origin.getY(), 0, 0, destination.getX(), destination.getY());
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

    	g2d.setColor(Color.BLACK);


		g2d.draw(edge);
  }
}
