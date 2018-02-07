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

import java.util.ArrayList;

import proj2.document.*;
import proj2.view.gui.shapes.*;

/*
* Extension of JPanel that handles drawing of states and vertex objects
* Adapted from this tutorial: https://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html
*/
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

  ArrayList<VertexShape> vertices;
  //testVertex.addMouseListener(this);
  //addMouseListener(this);

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

        VertexShape testVertex1 = new VertexShape(10,10, "test1", false);
        VertexShape testVertex2 = new VertexShape(60,10, "test2", false);
        vertices.add(testVertex1);
        vertices.add(testVertex2);

        for (VertexShape vertex: vertices) {
          vertex.addMouseListener(this);
        }

        this.addMouseListener(this);


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
          vertex.paintSquare(g);
        }

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
      boolean foundVertex = false;
      for (VertexShape vertex: vertices) {
        if (vertex.getBounds().contains(e.getPoint())) {
          System.out.println("Found me!");
          foundVertex = true;
        }
      }
      if (!foundVertex) {
        System.out.println("Adding Vertex");
        System.out.println(vertices.size());
        String name = ""+(vertices.size()+1);
        vertices.add(new VertexShape(e.getX(),e.getY(), name, false));
      }
      repaint();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
      for (VertexShape vertex: vertices) {
        if (vertex.getBounds().contains(e.getPoint())) {
          vertex.moveShape(e.getX(), e.getY());
        }
      }
      repaint();
    }
}
