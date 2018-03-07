package proj2.view.theme;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
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
import proj2.view.gui.Observer;

/**
* Provides a GUI management of themes for a finite state machine
* Contains three default themes as well as provisions for creating
* custom themes. Saves these themes so they can be utilized later.
*/
public class ThemeManager
{
	private ArrayList<Theme> themes;

	private Theme currTheme;

	private Theme modTheme;

	private DrawPanel gui;

	private LinkedList<Observer> observers;

	/*
	* Gui Setup
	*/

	JFrame buttonFrame;
	JPanel buttonPanel;

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

	/**
	* Constructs a theme manager
	*/
	public ThemeManager() {
		themes = new ArrayList<Theme>();

		observers = new LinkedList<Observer>();

		Theme defTheme = constructDefaultTheme();
		System.out.println(defTheme.toString());
		themes.add(defTheme);
		currTheme = (Theme)defTheme.clone();
		modTheme = currTheme.clone();

		themes.add(constructBlueTheme());
		themes.add(constructGreenTheme());

		System.out.println(themes.toString());

		/*
		* Gui Setup
		*/
		buttonFrame = new JFrame();
		buttonFrame.setSize(1000,500);
		buttonFrame.setLayout(new GridLayout(1, 2));
		buttonFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(11, 1, 2, 2));


		title = new JLabel("Theme Colors:");

		/*
		* Color Change Buttons
		* with their labels and color choosers
		*/
		stateOutlineColor = new JLabel("State Outline Color:");
		stateOutlineColorButton = new JButton("Change");
		stateOutlineColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateOutlineColor(cc.getColor());
				updateMenus();
			}
		});

		stateFillColor = new JLabel("State Fill Color:");
		stateFillColorButton = new JButton("Change");
		stateFillColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateFillColor(cc.getColor());
				updateMenus();
			}
		});

		stateTextColor = new JLabel("State Text Color:");
		stateTextColorButton = new JButton("Change");
		stateTextColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateTextColor(cc.getColor());
				updateMenus();
			}
		});

		stateAcceptColor = new JLabel("State Accept Color:");
		stateAcceptColorButton = new JButton("Change");
		stateAcceptColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateAcceptColor(cc.getColor());
				updateMenus();
			}
		});


		stateStartColor = new JLabel("State Start Color:");
		stateStartColorButton = new JButton("Change");
		stateStartColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateStartColor(cc.getColor());
				updateMenus();
			}
		});

		transLineColor = new JLabel("Transition Line Color:");
		transLineColorButton = new JButton("Change");
		transLineColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setTransLineColor(cc.getColor());
				updateMenus();
			}
		});

		transTextColor = new JLabel("Transition Text Color:");
		transTextColorButton = new JButton("Change");
		transTextColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setTransTextColor(cc.getColor());
				updateMenus();
			}
		});

		backgroundColor = new JLabel("Background Color:");
		backgroundColorButton = new JButton("Change");
		backgroundColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setBackgroundColor(cc.getColor());
				updateMenus();
			}
		});

		themeName = new JTextField("Theme Name");
		saveButton = new JButton("Save Theme");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theme custTheme = modTheme.clone();
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
				modTheme = (Theme)cb.getSelectedItem();
				updateMenus();
			}
		});

		applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currTheme = modTheme.clone();
				notifyObservers();
			}
		});

		cc = new JColorChooser(Color.BLACK);
		cc.setPreferredSize(new Dimension(100, 500));

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

		buttonFrame.add(cc);

	}

	public Theme getTheme() {
		return currTheme.clone();
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
	}

	private void updateMenus() {
		stateOutlineColorButton.setBackground(modTheme.getStateOutlineColor());
		stateOutlineColorButton.setOpaque(true);

		stateFillColorButton.setBackground(modTheme.getStateFillColor());
		stateFillColorButton.setOpaque(true);

		stateTextColorButton.setBackground(modTheme.getStateTextColor());
		stateTextColorButton.setOpaque(true);

		stateAcceptColorButton.setBackground(modTheme.getStateAcceptColor());
		stateAcceptColorButton.setOpaque(true);

		stateStartColorButton.setBackground(modTheme.getStateStartColor());
		stateStartColorButton.setOpaque(true);

		transLineColorButton.setBackground(modTheme.getTransLineColor());
		transLineColorButton.setOpaque(true);

		transTextColorButton.setBackground(modTheme.getTransTextColor());
		transTextColorButton.setOpaque(true);

		backgroundColorButton.setBackground(modTheme.getBackgroundColor());
		backgroundColorButton.setOpaque(true);
	}

	/**
	 * Notify method to tell views/observers to update
	 */
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}

	/**
	 * Adds an observer to the list of observers stored in the document
	 *
	 * @param o observer of type Observer to be added
	 */
	public void addObserver(Observer o)
	{
		observers.add(o);
		System.out.println("Observer Added");
	}

	/**
	 * Removes an observer from the list of observers stored in the document
	 *
	 * @param o observer of type Observer to be removed
	 */
	public void removeObserver(Observer o)
	{
		observers.remove(o);
	}
}
