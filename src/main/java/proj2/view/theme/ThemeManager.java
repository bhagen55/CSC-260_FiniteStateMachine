package proj2.view.theme;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JColorChooser;
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

	JFrame buttonFrame;
	JPanel buttonPanel;

	JFrame ccFrame;

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

	JColorChooser cc;


	public ThemeManager(DrawPanel gui) {
		this.gui = gui;

		themes = new ArrayList<Theme>();

		Theme defTheme = constructDefaultTheme();
		System.out.println(defTheme.toString());
		themes.add(defTheme);
		currTheme = (Theme)defTheme.clone();

		themes.add(constructBlueTheme());
		themes.add(constructGreenTheme());

		System.out.println(themes.toString());

		/*
		* Gui Setup
		*/
		buttonFrame = new JFrame();
		buttonFrame.setSize(500,500);
		buttonFrame.setLayout(new GridLayout(1, 1));
		buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(11, 1, 2, 2));

		ccFrame = new JFrame();
		ccFrame.setSize(500,500);
		ccFrame.setLayout(new GridLayout(1, 1));
		ccFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		title = new JLabel("Theme Colors:");

		/*
		* Color Change Buttons
		* with their labels and color choosers
		*/
		stateOutlineColor = new JLabel("State Outline Color:");
		stateOutlineColorButton = new JButton("Change");
		stateOutlineColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme.setStateOutlineColor(cc.getColor());
				updateMenus();
			}
		});

		stateFillColor = new JLabel("State Fill Color:");
		stateFillColorButton = new JButton("Change");
		stateFillColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme.setStateFillColor(cc.getColor());
				updateMenus();
			}
		});

		stateTextColor = new JLabel("State Text Color:");
		stateTextColorButton = new JButton("Change");
		stateTextColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme.setStateTextColor(cc.getColor());
				updateMenus();
			}
		});

		stateAcceptColor = new JLabel("State Accept Color:");
		stateAcceptColorButton = new JButton("Change");
		stateAcceptColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme.setStateAcceptColor(cc.getColor());
				updateMenus();
			}
		});


		stateStartColor = new JLabel("State Start Color:");
		stateStartColorButton = new JButton("Change");
		stateStartColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme.setStateStartColor(cc.getColor());
				updateMenus();
			}
		});

		transLineColor = new JLabel("Transition Line Color:");
		transLineColorButton = new JButton("Change");
		transLineColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme.setTransLineColor(cc.getColor());
				updateMenus();
			}
		});

		transTextColor = new JLabel("Transition Text Color:");
		transTextColorButton = new JButton("Change");
		transTextColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme.setTransTextColor(cc.getColor());
				updateMenus();
			}
		});

		backgroundColor = new JLabel("Background Color:");
		backgroundColorButton = new JButton("Change");
		backgroundColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme.setBackgroundColor(cc.getColor());
				updateMenus();
			}
		});

		themeName = new JTextField("Theme Name");
		saveButton = new JButton("Save Theme");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme custTheme = currTheme.clone();
				custTheme.setName(themeName.getText());
				themes.add(custTheme);
				cb.addItem(custTheme);
				updateMenus();
			}
		});

		themeTitle = new JLabel("Themes:");
		cb = new JComboBox<Theme>(themes.toArray(new Theme[themes.size()]));
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme = (Theme)cb.getSelectedItem();
				updateMenus();
			}
		});

		applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme selected = (Theme)cb.getSelectedItem();
				currTheme = selected;
				gui.update();
			}
		});

		cc = new JColorChooser(Color.BLACK);

		updateMenus();

		buttonPanel.add(stateOutlineColor);
		buttonPanel.add(stateOutlineColorButton);
		buttonPanel.add(stateFillColor);
		buttonPanel.add(stateFillColorButton);
		buttonPanel.add(stateTextColor);
		buttonPanel.add(stateTextColorButton);
		buttonPanel.add(stateAcceptColor);
		buttonPanel.add(stateAcceptColorButton);
		buttonPanel.add(stateStartColor);
		buttonPanel.add(stateStartColorButton);
		buttonPanel.add(transLineColor);
		buttonPanel.add(transLineColorButton);
		buttonPanel.add(transTextColor);
		buttonPanel.add(transTextColorButton);
		buttonPanel.add(backgroundColor);
		buttonPanel.add(backgroundColorButton);
		buttonPanel.add(themeTitle);
		buttonPanel.add(cb);
		buttonPanel.add(themeName);
		buttonPanel.add(saveButton);
		buttonPanel.add(applyButton);

		buttonFrame.add(buttonPanel);

		ccFrame.add(cc);

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
		buttonFrame.setVisible(true);
		ccFrame.setVisible(true);
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
