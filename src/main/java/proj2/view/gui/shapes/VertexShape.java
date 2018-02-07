package proj2.view.gui.shapes;

import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Component;


/*
* Represents shape of a vertex for the gui
*/

public class VertexShape extends Component{

    private String name;

    private int xPos;
    private int yPos;
    private int width;
    private int height;

    private int acceptOffset;

    private boolean isAccepting;

    Ellipse2D ellipse;

    public VertexShape(int xPos, int yPos, String name, boolean isAccepting) {

      this.name = name;

      this.xPos = xPos;
      this.yPos = yPos;

      this.width = 40;
      this.height = 40;

      this.acceptOffset = 10;

      this.isAccepting = isAccepting;

      ellipse = new Ellipse2D.Double(xPos, yPos, width, height);
      }

    /*
    * Changes coordinates of vertex object
    */
    public void moveShape(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public String getName() {
      return name;
    }

    /*
    * Get the x coordinate of the vertex object
    */
    public int getX() {
        return xPos;
    }

    /*
    * Set the x coordinate of the vertex object
    */
    public void setX(int newX) {
        xPos = newX;
    }


    /*
    * Set the y coordinate of the vertex object
    */
    public void setY(int newY) {
        yPos = newY;
    }

    /*
    * Get the y coordinate of the vertex object
    */
    public int getY() {
        return yPos;
    }

    /*
    * Get the width of the vertex object
    */
    public int getWidth() {
      if (isAccepting) {
        return width+acceptOffset;
      }
      else {
        return width;
      }
    }

    /*
    * Get the height of the vertex object
    */
    public int getHeight() {
      if (isAccepting) {
        return height+acceptOffset;
      }
      else {
        return height;
      }
    }

    /*
    * Toggles whether this vertex is an accepting state
    * If toggled, the shape needs to be re-drawn
    * TODO: Check how to redraw the shape, if needed?
    */
    public void toggleAccept() {
      isAccepting = !isAccepting;
    }

    /*
    * Get whether or not the vertex object is an accepting state
    */
    public boolean isAcceptState() {
      return isAccepting;
    }

    public Rectangle getBounds() {
      return(ellipse.getBounds());
    }

    /*
    * Does the actual painting of this object on a JPanel
    */
    public void paintSquare(Graphics g){

      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(Color.BLACK);
      g2d.draw(ellipse);
      g2d.drawString(name,xPos,yPos);

      if(isAccepting){
        g2d.draw(new Ellipse2D.Double(xPos,yPos,width+acceptOffset,height+acceptOffset));
      }
    }
}
