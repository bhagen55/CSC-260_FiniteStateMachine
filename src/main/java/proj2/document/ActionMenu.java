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

/**
* Menu with options to add actions to states
*/
public class ActionMenu {

	/**
	* Constructs a menu with a list of actions
	* to add to states
	*/
	public ActionMenu(Document doc) {
		JFrame f = new JFrame();
		f.setSize(400,200);
		f.setLayout(new GridLayout(1, 1));
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1, 2, 2));

		JLabel title = new JLabel("Select an action to add:");

		Action[] actions = {new SoundAction};
		JComboBox<Saver> cb = new JComboBox<Saver>(savers);

	}
}
