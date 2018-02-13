package proj2;

import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

import proj2.document.Document;
import proj2.view.Gui;
import proj2.view.DrawPanel;

/*
* Encompassing class to hold our implementation of a finite state machine
*
* Deals with creation of the GUI and the document as well as allowing them to
* communicate with each other.
*
*/
public class FiniteStateMachine {

	// Hold the document that represents the fsm
	Document doc;

	// Hold the gui view
	DrawPanel panel;
	Gui gui;

	public FiniteStateMachine() {

		doc = new Document();

		// Create the view panel and pass it the document
		panel = new DrawPanel(doc);
		// Add the view panel as an observer to the document
		doc.addObserver(panel)

		// Create and show the GUI of the view panel
		gui = new Gui();
		gui.createAndShowGUI(panel);
	}
}
