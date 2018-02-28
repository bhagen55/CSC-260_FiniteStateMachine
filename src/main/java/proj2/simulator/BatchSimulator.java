package proj2.simulator;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

import proj2.view.gui.DrawPanel;

public class BatchSimulator implements Simulator {

    private DrawPanel dp;

    public BatchSimulator(DrawPanel dpanel) {

        dp = dpanel;

        JFrame f = new JFrame("Finite State Machine");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(dp);
        f.setSize(800,800);
        f.setVisible(true);

    }

    public void simulate() {}

    public void update() {}

}
