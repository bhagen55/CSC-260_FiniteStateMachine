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

import proj2.view.gui.shapes.*;
import proj2.view.gui.*;

/*
* Extension of JPanel that handles drawing of states and vertex objects
* Adapted from this tutorial: https://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html
*/
class DrawPanel extends JPanel implements MouseMotionListener {

  VertexShape testVertex;

    public DrawPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));

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

        testVertex = new VertexShape(10,10, false);
        testVertex.addMouseMotionListener(this);


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

        testVertex.paintSquare(g);
    }

    public void mouseMoved(MouseEvent e) {
      saySomething("Mouse moved", e);
    }

    void saySomething(String eventDescription, MouseEvent e) {
      System.out.println(eventDescription + " Detected on " + e.getComponent().getClass().getName());
    }
}
