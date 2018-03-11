package proj2.document;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import proj2.document.Action;
import proj2.document.actions.*;
import proj2.document.State;

/**
* Menu with options to add actions to states
*/
public class ActionMenu {

	String selState;

	JFrame f;

	Document doc;

	JComboBox<Action> cb;

	/**
	* Constructs a menu with a list of actions
	* to add to states
	*/
	public ActionMenu(Document doc) {

		this.doc = doc;

		f = new JFrame();
		f.setSize(400,200);
		f.setLayout(new GridLayout(1, 1));
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1, 2, 2));

		JLabel title = new JLabel("Current Action:");

		Action[] actions = {new NoAction(), new SoundAction()};
		cb = new JComboBox<Action>(actions);
		cb.setSelectedIndex(0);

		JButton applyButton = new JButton("Apply");

		// Call the needed save class when the button is pressed
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action selected = (Action)cb.getSelectedItem();
				setAction(selected, selState);
			}
		});

		p.add(title);
		p.add(cb);
		p.add(applyButton);

		f.add(p);
	}

	/**
	* Show menu and set selected state name for action to be applied to
	*
	* @param selState state name to set as selected for actions to be applied to
	*/
	public void showMenu(String selState) {
		this.selState = selState;
		System.out.println(doc.getAction(selState));
		cb.setSelectedItem((Object)doc.getAction(selState));
		f.setVisible(true);
	}

	/**
	* Set action of a state through the document
	*
	* @param action action to add to state
	* @param state state to add action to
	*/
	private void setAction(Action action, String state) {
		doc.addAction(selState, action);
	}
}
