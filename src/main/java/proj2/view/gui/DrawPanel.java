package proj2.view.gui;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Rectangle;
import java.awt.Point;

import java.util.ArrayList;

import proj2.document.*;
import proj2.view.gui.shapes.*;

/*
* Extension of JPanel that handles drawing of states and vertex objects
* Adapted from this tutorial: https://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html
* Dragging: http://www.java2s.com/Code/Java/Event/MoveShapewithmouse.htm
*/
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

  ArrayList<VertexShape> vertices;

  VertexShape selVertex;

  // Holds last known location of mouse for dragging

    int preX;
    int preY;
    int preXDrag;
    int preYDrag;


    public DrawPanel() {

      System.out.println("Setting up");

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // addMouseListener(new MouseAdapter(){
        //     public void mousePressed(MouseEvent e){
        //         moveSquare(e.getX(),e.getY());
        //     }
        // });

        // addMouseMotionListener(new MouseAdapter(){
        //     public void mouseDragged(MouseEvent e){
        //         moveSquare(e.getX(),e.getY());
        //     }
        // });

        vertices = new ArrayList<VertexShape>();

        VertexShape testVertex1 = new VertexShape(10,10, "A", false);
        VertexShape testVertex2 = new VertexShape(120,10, "B", false);
        vertices.add(testVertex1);
        vertices.add(testVertex2);

        for (VertexShape vertex: vertices) {
          vertex.addMouseListener(this);
        }

        this.addMouseListener(this);
        this.addMouseMotionListener(this);


    }

    private void moveVertex(VertexShape v, int x, int y){

        // Current square state, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int CURR_X = v.getX();
        final int CURR_Y = v.getY();
        final int CURR_W = v.getWidth();
        final int CURR_H = v.getHeight();
        final int OFFSET = 1;

        if ((CURR_X!=x) || (CURR_Y!=y)) {

            // The square is moving, repaint background
            // over the old square location.
            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
            v.setX(x);
            v.setY(y);

            // Repaint the square at the new location.
            repaint(v.getX(), v.getY(),
                    v.getWidth()+OFFSET,
                    v.getHeight()+OFFSET);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (VertexShape vertex: vertices) {
          vertex.paintShape(g);
        }

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
      selVertex = null;
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {


      boolean foundVertex = false;
      for (VertexShape vertex: vertices) {
        if (vertex.getEllipse().getBounds().contains(e.getPoint())) {

          // Record selected vertex to keep dragging from affecting other vertices
          selVertex = vertex;

          if (e.getClickCount() == 2) {
            vertex.toggleAccept();
          }
          preX = vertex.getX() - e.getX();
          preY = vertex.getY() - e.getY();
          preXDrag = e.getX();
          preYDrag = e.getY();
          foundVertex = true;
          vertex.setY(e.getY());
          vertex.setX(e.getX());
          repaint();
        }
      }
      if (!foundVertex) {
        System.out.println(vertices.size());
        String name = ""+(vertices.size()+1);
        vertices.add(new VertexShape(e.getX(),e.getY(), name, false));
      }
      repaint();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
      //System.out.println("Dragging to " + x + " " + y);

      // Only move if a vertex was clicked on before the mouse was dragged
      if (selVertex != null) {
        selVertex.setY(e.getY());
        selVertex.setX(e.getX());
        selVertex.moveShape(preX + e.getX(), preY + e.getY());
        preXDrag = e.getX();
        preYDrag = e.getY();
        repaint();
      }
    }
}
