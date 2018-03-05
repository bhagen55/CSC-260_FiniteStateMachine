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
* Menu with options to save an fsm as different formats
*/
public class SaveMenu {

    /**
    * Constructs a menu using a printable object as the print source
    * for image savers and the document for text savers
    */
    public SaveMenu(DrawPanel gui, Document doc) {
        JFrame f = new JFrame();
        f.setSize(300,200);
        f.setLayout(new GridLayout(1, 1));

        JPanel p=new JPanel();
        p.setLayout(new GridLayout(3, 1, 2, 2));

        JLabel title = new JLabel("Select a save format then click save");

        Saver[] savers = {new FSMSave(doc), new ImageSave(gui), new LaTeXSave(), new TextSave(doc)};
        JComboBox<Saver> cb = new JComboBox<Saver>(savers);

        JButton saveButton = new JButton("Save");

        // Call the needed save class when the button is pressed
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Saver selected = (Saver)cb.getSelectedItem();
                selected.save();
            }
        });

        p.add(title);
        p.add(cb);
        p.add(saveButton);

        f.add(p);

        f.setVisible(true);
    }
}
