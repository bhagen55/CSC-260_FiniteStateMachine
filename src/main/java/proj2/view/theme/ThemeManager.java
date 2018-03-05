package proj2.view.theme;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

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

	/*
	* Gui Setup
	*/

	JFrame f;
	JPanel p;

	JLabel title;

	JLabel stateOutlineColor;
	JButton stateOutlineColorButton;

	JLabel stateFillColor;
	JButton stateFillColorButton;

	JLabel stateTextColor;
	JButton stateTextColorButton;

	JLabel stateAcceptColor;
	JButton stateAcceptColorButton;

	JLabel stateStartColor;
	JButton stateStartColorButton;

	JLabel transLineColor;
	JButton transLineColorButton;

	JLabel transTextColor;
	JButton transTextColorButton;

	JLabel backgroundColor;
	JButton backgroundColorButton;

	JTextField themeName;

	JButton saveButton;

	JLabel themeTitle;

	JComboBox<Theme> cb;

	JButton applyButton;


	public ThemeManager(DrawPanel gui) {
		this.gui = gui;

		themes = new ArrayList<Theme>();

		Theme defTheme = constructDefaultTheme();
		System.out.println(defTheme.toString());
		themes.add(defTheme);
		currTheme = defTheme;

		themes.add(constructBlueTheme());
		themes.add(constructGreenTheme());

		System.out.println(themes.toString());

		/*
		* Gui Setup
		*/
		f = new JFrame();
		f.setSize(500,500);
		f.setLayout(new GridLayout(1, 1));

		p = new JPanel();
		p.setLayout(new GridLayout(11, 1, 2, 2));

		title = new JLabel("Theme Colors:");

		stateOutlineColor = new JLabel("State Outline Color:");
		stateOutlineColorButton = new JButton("Change");

		stateFillColor = new JLabel("State Fill Color:");
		stateFillColorButton = new JButton("Change");

		stateTextColor = new JLabel("State Text Color:");
		stateTextColorButton = new JButton("Change");

		stateAcceptColor = new JLabel("State Accept Color:");
		stateAcceptColorButton = new JButton("Change");

		stateStartColor = new JLabel("State Start Color:");
		stateStartColorButton = new JButton("Change");

		transLineColor = new JLabel("Transition Line Color:");
		transLineColorButton = new JButton("Change");

		transTextColor = new JLabel("Transition Text Color:");
		transTextColorButton = new JButton("Change");

		backgroundColor = new JLabel("Background Color:");
		backgroundColorButton = new JButton("Change");

		themeName = new JTextField("Theme Name");
		saveButton = new JButton("Save Theme");

		themeTitle = new JLabel("Themes:");
		cb = new JComboBox<Theme>(themes.toArray(new Theme[themes.size()]));

		applyButton = new JButton("Apply");

		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme selected = (Theme)cb.getSelectedItem();
				currTheme = selected;
				gui.update();
			}
		});

		updateMenus();

		p.add(stateOutlineColor);
		p.add(stateOutlineColorButton);
		p.add(stateFillColor);
		p.add(stateFillColorButton);
		p.add(stateTextColor);
		p.add(stateTextColorButton);
		p.add(stateAcceptColor);
		p.add(stateAcceptColorButton);
		p.add(stateStartColor);
		p.add(stateStartColorButton);
		p.add(transLineColor);
		p.add(transLineColorButton);
		p.add(transTextColor);
		p.add(transTextColorButton);
		p.add(backgroundColor);
		p.add(backgroundColorButton);
		p.add(themeTitle);
		p.add(cb);
		p.add(themeName);
		p.add(saveButton);
		p.add(applyButton);

		f.add(p);

	}

	public Theme getTheme() {
		return currTheme;
	}


	private Theme constructDefaultTheme() {
		Theme defaultTheme = new Theme("default theme", Color.BLACK, Color.WHITE,
									Color.BLACK, Color.BLACK, Color.RED,
									Color.BLACK, Color.BLACK, Color.WHITE);
		return defaultTheme;
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
		f.setVisible(true);
	}

	private void updateMenus() {
		stateOutlineColorButton.setBackground(currTheme.getStateOutlineColor());
		stateOutlineColorButton.setOpaque(true);

		stateFillColorButton.setBackground(currTheme.getStateFillColor());
		stateFillColorButton.setOpaque(true);

		stateTextColorButton.setBackground(currTheme.getStateTextColor());
		stateTextColorButton.setOpaque(true);

		stateAcceptColorButton.setBackground(currTheme.getStateAcceptColor());
		stateAcceptColorButton.setOpaque(true);

		stateStartColorButton.setBackground(currTheme.getStateStartColor());
		stateStartColorButton.setOpaque(true);

		transLineColorButton.setBackground(currTheme.getTransLineColor());
		transLineColorButton.setOpaque(true);

		transTextColorButton.setBackground(currTheme.getTransTextColor());
		transTextColorButton.setOpaque(true);

		backgroundColorButton.setBackground(currTheme.getBackgroundColor());
		backgroundColorButton.setOpaque(true);
	}
}
