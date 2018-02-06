package proj2.view.gui;

import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Component;


/*
* Represents shape of a vertex for the gui
*/

class VertexShape extends Component{

    private int xPos;
    private int yPos;
    private int width;
    private int height;

    private int acceptOffset;

    private boolean isAccepting;

    public VertexShape(int xPos, int yPos, boolean isAccepting) {
      this.xPos = xPos;
      this.yPos = yPos;

      this.width = 10;
      this.height = 10;

      this.acceptOffset = 5;

      this.isAccepting = isAccepting;
    }

    /*
    * Changes coordinates of vertex object
    */
    public void moveShape(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
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

    /*
    * Does the actual painting of this object on a JPanel
    */
    public void paintSquare(Graphics g){
      g.setColor(Color.BLACK);
      g.drawOval(xPos,yPos,width,height);

      if(isAccepting){
        g.drawOval(xPos,yPos,width+acceptOffset,height+acceptOffset);
      }
    }
}
