package proj2.view.gui.menus;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class saveMenu {

        public saveMenu() {
            JFrame f = new JFrame();
            f.setSize(300,200);
            f.setLayout(new GridLayout(1, 1));

            JPanel p=new JPanel();
            p.setLayout(new GridLayout(3, 1, 2, 2));
            JTextField t1=new JTextField(20);

            p.add(t1);
            f.add(p);
            f.setVisible(true);
        }
}
