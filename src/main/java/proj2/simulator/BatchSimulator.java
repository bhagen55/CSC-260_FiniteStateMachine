package proj2.simulator;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

import java.util.LinkedList;

import proj2.view.gui.DrawPanel;
import proj2.view.gui.shapes.*;
import proj2.document.*;

public class BatchSimulator extends JPanel implements Simulator, KeyListener{

    private DrawPanel dp;
    private Document d;
    private LinkedList words;
    private JTextField jtf;
    private JTextArea jta;

    private Color StateFill;
    private Color StateOutline;
    private static final Color HIGHLIGHT_COLOR = Color.BLUE;

    private boolean current;
    private StateShape currentShape;

    private LinkedList<String> toParse;
    private LinkedList<StateShape> states;
    private LinkedList<TransitionShape> transitions;
    private LinkedList<State> stateItems;

    public BatchSimulator(DrawPanel dpanel, Document doc, LinkedList<String> input) {

        dp = dpanel;
        d = doc;
        toParse = input;
        stateItems = d.getModel();
        states = dp.getStates();
        transitions = dp.getTransitions();

        //super("Hello");
        System.out.println("I'm alive!");

        jtf = new JTextField(20);
        words = new LinkedList();

        JFrame f = new JFrame("Batch Simulation");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(dp);
        f.setSize(800,800);
        f.setVisible(true);

        this.addKeyListener(this);

        jta = new JTextArea();

        update();

    }

    public void simulate() {

        drawEverything();

        currentShape = getStartShape();
        int inputSize = toParse.size();
        for (int step=0; step < inputSize; step++) {
            repaint();
            stepForward(step);
        }

    }

    private void drawEverything() {}

    private StateShape getStartShape() {

        int size = states.size();
        for (int i=0; i < size; i++) {
            StateShape currShape = states.get(i);
            if (currShape.isStart() == true) {
                currShape.toggleCurrent();
                return currShape;
            }
        }
        return null;
    }


    private State getNextStateItem(String shape, String transition) {
        int size = stateItems.size();
        for (int i=0; i<size; i++) {
            State currState = stateItems.get(i);
            String currStateSymbol = currState.getName();
            if (shape.equals(currStateSymbol)) {
                if (currState.hasTransition(transition)) {
                    return currState.goTo(transition);
                }
            }
        }
        return null;
    }
    

    private void stepForward(int step) {

        currentShape.toggleCurrent();

        String shapeSymbol = currentShape.getName();
        String transitionSymbol = toParse.get(step);
        State currentShapeItem = getNextStateItem(shapeSymbol, transitionSymbol);
        if (currentShapeItem == null) {
            notNextException();
        } else {
            currentShape = getShape(currentShapeItem.getName());
            currentShape.toggleCurrent();
        }

    }

    private void notNextException() {

    }

    private StateShape getShape(String name) {

        for (StateShape curr : states) {
            if (curr.getName().equals(name)) {
                return curr;
            }
        }
        return null;
    }

    private Color getStateOutline() {
        return StateOutline;
    }

    private void setStateOutline(Color newColor) {
        StateOutline = newColor;
    }

    public void update() {

        for (Object word:words) {
            System.out.println(word);
        }
    }





    public void paintComponent(Graphics g) {

        for (TransitionShape transition: transitions) {
            transition.paintShape(g);
        }
        for (StateShape state: states) {
            state.paintShape(g);
            }
        }


    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

}
