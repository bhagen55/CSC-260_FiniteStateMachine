package proj2.simulator;

import java.util.LinkedList;

import proj2.view.gui.DrawPanel;
import proj2.view.gui.shapes.*;
import proj2.document.*;

public class StringSimulator implements Simulator {

    private DrawPanel dp;
    private Document d;
    private LinkedList words;

    private StateShape currentShape;

    private String[] toParse;
    private LinkedList<StateShape> states;
    private LinkedList<TransitionShape> transitions;
    private LinkedList<State> stateItems;

    private int currentStep;


    public StringSimulator(DrawPanel dpanel, Document doc, String[] input) {

        dp = dpanel;
        d = doc;
        toParse = input;
        stateItems = d.getModel();
        states = dp.getStates();
        transitions = dp.getTransitions();
        currentStep = 0;

    }

    public void simulate() {

        System.out.println(states.size());
        System.out.println(transitions.size());

        if (currentStep == 0) {
            currentShape = getStartShape();
            currentShape.toggleCurrent();
        }
        if (currentStep < toParse.length) {
            stepForward();
        }

    }


    private void stepForward() {

        // unhighlights current shape
        currentShape.toggleCurrent();

        String shapeSymbol = currentShape.getName();
        String transitionSymbol = toParse[currentStep];
        State currentShapeItem = getNextStateItem(shapeSymbol, transitionSymbol);

        if (currentShapeItem != null) {
            currentShape = getShape(currentShapeItem.getName());
            getState(currentShapeItem.getName()).getAction().execute();

            // unhighlights the next shape
            currentShape.toggleCurrent();
        }
        currentStep++;

    }

    private StateShape getStartShape() {

        int size = states.size();
        for (int i=0; i < size; i++) {
            StateShape currShape = states.get(i);
            if (currShape.isStart() == true) {
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

    private StateShape getShape(String name) {

        for (StateShape curr : states) {
            if (curr.getName().equals(name)) {
                return curr;
            }
        }
        return null;
    }

    private State getState(String name) {
        for (State curr : stateItems) {
            if (curr.getName().equals(name)) {
                return curr;
            }
        }
        return null;
    }

    public void update() {}

    private StringSimulator getThis() {
        return this;
    }

}
