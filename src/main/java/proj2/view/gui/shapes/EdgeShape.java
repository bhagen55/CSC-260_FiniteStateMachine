package proj2.view.gui.shapes;

import java.awt.Point;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;

import proj2.view.gui.shapes.VertexShape;

/*
* Represents shape of an edge for the gui
*/

public class EdgeShape extends Component{

	private String name;

	private VertexShape origin;
	private VertexShape destination;

	public EdgeShape(VertexShape origin, VertexShape destination, String name) {
		this.name = name;

    	this.origin = origin;
    	this.destination = destination;
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
  }
}
