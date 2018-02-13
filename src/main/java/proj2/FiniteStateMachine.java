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

	Document doc;

	DrawPanel panel;
	Gui gui;

	public FiniteStateMachine() {

		doc = new Document();

		panel = new DrawPanel(doc);
		doc.addObserver(panel)

		gui = new Gui();
		gui.createAndShowGUI(panel);

	}
}
