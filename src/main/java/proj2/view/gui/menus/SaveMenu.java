package proj2.view.gui.menus;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaveMenu {

    public SaveMenu() {
        JFrame f = new JFrame();
        f.setSize(300,200);
        f.setLayout(new GridLayout(1, 1));

        JPanel p=new JPanel();
        p.setLayout(new GridLayout(3, 1, 2, 2));

        JLabel title = new JLabel("Select a save format then click save");

        String[] choices = {"FSM","Image", "LaTeX","","Text"};
        JComboBox<String> cb = new JComboBox<String>(choices);

        JButton saveButton = new JButton("Save");

        // Call the needed save class when the button is pressed
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        p.add(title);
        p.add(cb);
        p.add(saveButton);

        f.add(p);

        f.setVisible(true);
    }
}
