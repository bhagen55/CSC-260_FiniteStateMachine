package proj2.view.gui.shapes;

import java.awt.Point;

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
    Double orAngle = orCenter.project(desCenter);
    Double desAngle = desCenter.project(orCenter);

    
  }

  public void paintEdgeShape(Graphics g) {

    // Calculate start and end coordinates

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);



  }




}
