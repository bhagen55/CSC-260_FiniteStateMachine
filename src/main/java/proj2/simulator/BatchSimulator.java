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

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;


public class BatchSimulator extends JPanel implements Simulator{

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

    private String[] toParse;
    private LinkedList<StateShape> states;
    private LinkedList<TransitionShape> transitions;
    private LinkedList<State> stateItems;

    private char charExample = 'e';
    private boolean buttonPressed;
    private int currentStep;
    private static final int RIGHT_ARROW_KEY_CODE = 39;
    private static final int LEFT_ARROW_KEY_CODE = 37;

    private JFrame f2;


    public BatchSimulator(DrawPanel dpanel, Document doc, String[] input) {

        dp = dpanel;
        d = doc;
        toParse = input;
        stateItems = d.getModel();
        states = dp.getStates();
        transitions = dp.getTransitions();
        buttonPressed = false;
        currentStep = 0;



        //super("Hello");
        System.out.println("I'm alive!");
        System.out.println("transitions size " + transitions.size());
        System.out.println("states size " + states.size());

        // add(new ArrowPress(RIGHT_ARROW_KEY_CODE, 0));
        // add(new ArrowPress(LEFT_ARROW_KEY_CODE, 0));
        //simulate();

    }

    public void simulate() {

        System.out.println("Should be painted but isn't probably");

        if (currentStep == 0) {
            currentShape = getStartShape();
            System.out.println("#### PRE TOGGLE START " + currentShape.isCurrent());
            currentShape.toggleCurrent();
            stepForward();
            System.out.println("#### POST TOGGLE START " + currentShape.isCurrent());


        } else if (currentStep < toParse.length) {
            System.out.println("currentSHAPE IS " + currentShape.getName());
            stepForward();
        }
        //currentStep++;
        repaint();

    }


    private void stepForward() {

        // unhighlights current shape
        currentShape.toggleCurrent();

        String shapeSymbol = currentShape.getName();
        System.out.println("shapeSymbol is " + shapeSymbol);
        String transitionSymbol = toParse[currentStep];
        //System.out.println("toParse currentstep " + toParse[currentStep-1]);
        State currentShapeItem = getNextStateItem(shapeSymbol, transitionSymbol);
        if (currentShapeItem == null) {
            System.out.println("not next exception?");
            notNextException();
        } else {
            System.out.println("** NOW STATE ** " + currentShape.getName());
            currentShape = getShape(currentShapeItem.getName());
            System.out.println("** NEXT STATE ** " + currentShape.getName());

            // unhighlights the next shape
            currentShape.toggleCurrent();
        }
        currentStep++;

    }

    private void drawEverything() {}

    private StateShape getStartShape() {

        int size = states.size();
        for (int i=0; i < size; i++) {
            StateShape currShape = states.get(i);
            if (currShape.isStart() == true) {
                //currShape.toggleCurrent();
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


    public void addNotify() {
        super.addNotify();
        requestFocus();
    }


    public void paintComponent(Graphics g) {

        System.out.println("painting is broken");
        super.paintComponent(g);

        // g.clearRect(0, 0, getWidth(), getHeight());
        // g.drawString("curr key is " + charExample, 250, 250);
        // g.drawString("transitions length" + transitions.size(), 300, 300);
        // g.drawString("states length" + states.size(), 300, 350);

        for (TransitionShape transition: transitions) {
            transition.paintShape(g);
        }
        for (StateShape state: states) {
            state.paintShape(g);
        }
    }

    private BatchSimulator getThis() {
        return this;
    }

}

// public class ArrowPress extends JPanel {
//
//     public ArrowPress(int keyCode, int modifier) {
//
//         InputMap im = getInputmap(WHEN_IN_FOCUSED_WINDOW);
//         ActionMap am = getActionMap();
//
//         im.put(KeyStroke.getKeyStroke(keyCode, modifier, false), "keyPressed");
//         im.put(KeyStroke.getKeyStroke(keyCode, modifier, true), "keyReleased");
//
//         am.put("keyPressed", new AbstractAction() {
//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     if (keyCode == RIGHT_ARROW_KEY_CODE) {
//                         stepForward(currentStep);
//                     } else if (keyCode == LEFT_ARROW_KEY_CODE) {
//
//                     }
//                 }
//             });
//
//     }
// }
