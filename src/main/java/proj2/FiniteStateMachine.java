package proj2;

import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

import proj2.document.Document;
import proj2.view.gui.Gui;
import proj2.view.gui.DrawPanel;

/*
* Encompassing class to hold our implementation of a finite state machine
*
* Deals with creation of the GUI and the document as well as allowing them to
* communicate with each other.
*
*/
public class FiniteStateMachine {

	/**
	* Creates the frame to hold a gui view of this fsm
	*
	* @param panel the panel to be drawn in the frame
	*/
	public static void createAndShowGUI(DrawPanel panel) {
		JFrame f = new JFrame("Finite State Machine");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(panel);
		f.setSize(800,800);
		f.setVisible(true);
	}

	/*
	* Threaded function to draw guis related to this fsm
	*/
	public static void main(String[] args) {

		// Hold the document that represents the fsm
		Document doc = new Document();

		// Create the view panel and pass it the document
		DrawPanel panel = new DrawPanel(doc);
		// Add the view panel as an observer to the document
		doc.addObserver(panel);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(panel);
			}
		});
	}
}
