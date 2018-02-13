package proj2.view.gui;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

import proj2.view.gui.DrawPanel;

/*
* Main gui class
* Adapted from this tutorial: https://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html
*/

public class Gui {

    public void createAndShowGUI(DrawPanel panel) {
    	JFrame f = new JFrame("Finite State Machine");
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(panel);
        f.setSize(250,250);
        f.setVisible(true);
    }

}
