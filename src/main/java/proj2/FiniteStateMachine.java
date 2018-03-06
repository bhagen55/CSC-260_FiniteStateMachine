package proj2;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

import proj2.document.Document;
import proj2.view.gui.DrawPanel;
import proj2.filehandler.concretefilehandler.TextSave;

import proj2.simulator.BatchSimulator;

/**
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
	private static void createAndShowGUI(DrawPanel panel) {
		JFrame f = new JFrame("Finite State Machine");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(panel);
		f.setSize(800,800);
		f.setVisible(true);
	}

	private static void cr (BatchSimulator bs) {
		JFrame f2 = new JFrame("Simulator");
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.add(bs);
		f2.setSize(800,800);
		f2.setVisible(true);
	}

	/**
	* Threaded function to draw guis related to this fsm
	*
	* @param args arguments to be passed to main, currently not implemented
	*/
	public static void main(String[] args) {

		// Hold the document that represents the fsm
		Document doc = new Document();

		// Creates the File Format Controller
		TextSave ts = new TextSave(doc);

		LinkedList example = new LinkedList();
		example.add("a");
		example.add("b");
		example.add("c");
		example.add("d");
		// Create the view panel and pass it the document
		DrawPanel panel = new DrawPanel(doc, ts);
		//BatchSimulator bs = new BatchSimulator(panel, doc, example);

		// Add the view panel as an observer to the document
		doc.addObserver(panel);

		// Runs the GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(panel);
				//cr(bs);
			}
		});
	}
}
