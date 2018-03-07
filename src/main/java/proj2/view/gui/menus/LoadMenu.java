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

import proj2.filehandler.*;
import proj2.filehandler.concretefilehandler.*;
import proj2.view.gui.Printable;
import proj2.view.gui.DrawPanel;
import proj2.document.Document;

/**
* Menu with options to load an fsm as different formats
*/
public class LoadMenu
{
    public LoadMenu(Document doc) {
        JFrame f = new JFrame();
        f.setSize(400,200);
        f.setLayout(new GridLayout(1, 1));
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 1, 2, 2));

        JLabel title = new JLabel("Select a load format:");

        Loader[] loaders = {new FSMSave(doc), new TextSave(doc)};
        JComboBox<Loader> cb = new JComboBox<Loader>(loaders);

        JTextField name = new JTextField("filename");

        JButton loadButton = new JButton("Load");

        // Call the needed load class when the button is pressed
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Loader selected = (Loader)cb.getSelectedItem();
                selected.load(name.getText());
            }
        });

        p.add(title);
        p.add(cb);
        p.add(name);
        p.add(loadButton);

        f.add(p);

        f.setVisible(true);
    }
}
