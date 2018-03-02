package proj2.view.theme;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import proj2.view.theme.Theme;
import proj2.view.gui.DrawPanel;

/**
* Provides a GUI management of themes for a finite state machine
* Contains three default themes as well as provisions for creating
* custom themes. Saves these themes so they can be utilized later.
*/
public class ThemeManager
{
	private ArrayList<Theme> themes;

	private Theme currTheme;

	private DrawPanel gui;

	public ThemeManager(DrawPanel gui) {

		this.gui = gui;

		Theme defTheme = constructDefaultTheme();
		themes.add(defTheme);
		currTheme = defTheme;

		themes.add(constructBlueTheme());
		themes.add(constructGreenTheme());
	}

	public Theme getTheme() {
		return currTheme;
	}


	private Theme constructDefaultTheme() {
		Theme defTheme = new Theme("default theme", Color.BLACK, Color.WHITE,
									Color.BLACK, Color.BLACK, Color.RED,
									Color.BLACK, Color.BLACK, Color.WHITE);
		return defTheme;
	}

	private Theme constructBlueTheme() {
		Theme blueTheme = new Theme("blue theme", Color.BLUE, Color.WHITE,
									Color.BLUE, Color.BLUE, Color.BLACK,
									Color.BLUE, Color.BLUE, Color.WHITE);
		return blueTheme;
	}

	private Theme constructGreenTheme() {
		Theme greenTheme = new Theme("green theme", Color.GREEN, Color.WHITE,
									Color.GREEN, Color.GREEN, Color.BLACK,
									Color.GREEN, Color.GREEN, Color.WHITE);
		return greenTheme;
	}

	private Theme constructCustomTheme(String name, Color stateOutlineColor,
									Color stateFillColor, Color stateTextColor,
									Color stateAcceptColor, Color stateStartColor,
									Color transLineColor, Color transTextColor,
									Color backgroundColor) {
		Theme custTheme = new Theme(name, stateOutlineColor, stateFillColor,
									stateTextColor, stateAcceptColor,
									stateStartColor, transLineColor,
									transTextColor, backgroundColor);
		return custTheme;
	}

	public void showMenu() {
		JFrame f = new JFrame();
		f.setSize(500,500);
		f.setLayout(new GridLayout(1, 1));

		JPanel p=new JPanel();
		p.setLayout(new GridLayout(2, 1, 2, 2));

		JLabel title = new JLabel("Theme Colors:");

		JLabel stateOutlineColor = new JLabel("State Outline Color:");
		JButton stateOutlineColorButton = new JButton("Change");
		stateOutlineColorButton.setBackground(currTheme.getStateOutlineColor());
		stateOutlineColorButton.setOpaque(true);

		JLabel stateFillColor = new JLabel("State Fill Color:");
		JButton stateFillColorButton = new JButton("Change");
		stateFillColorButton.setBackground(currTheme.getStateFillColor());
		stateFillColorButton.setOpaque(true);

		JLabel stateTextColor = new JLabel("State Text Color:");
		JButton stateTextColorButton = new JButton("Change");
		stateTextColorButton.setBackground(currTheme.getStateTextColor());
		stateTextColorButton.setOpaque(true);

		JLabel stateAcceptColor = new JLabel("State Accept Color:");
		JButton stateAcceptColorButton = new JButton("Change");
		stateAcceptColorButton.setBackground(currTheme.getStateAcceptColor());
		stateAcceptColorButton.setOpaque(true);

		JLabel stateStartColor = new JLabel("State Start Color:");
		JButton stateStartColorButton = new JButton("Change");
		stateStartColorButton.setBackground(currTheme.getStateStartColor());
		stateStartColorButton.setOpaque(true);

		JLabel transLineColor = new JLabel("Transition Line Color:");
		JButton transLineColorButton = new JButton("Change");
		transLineColorButton.setBackground(currTheme.getTransLineColor());
		transLineColorButton.setOpaque(true);

		JLabel transTextColor = new JLabel("Transition Text Color:");
		JButton transTextColorButton = new JButton("Change");
		transTextColorButton.setBackground(currTheme.getTransTextColor());
		transTextColorButton.setOpaque(true);

		JLabel backgroundColor = new JLabel("Background Color:");
		JButton backgroundColorButton = new JButton("Change");
		backgroundColorButton.setBackground(currTheme.getBackgroundColor());
		backgroundColorButton.setOpaque(true);

		JButton saveButton = new JButton("Save Theme");

		JLabel themeTitle = new JLabel("Themes:");
		JComboBox<Theme> cb = new JComboBox<Theme>((Theme[])themes.toArray());

		JButton applyButton = new JButton("Apply");

		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme selected = (Theme)cb.getSelectedItem();
				currTheme = selected;
				gui.update();
			}
		});
	}
}
