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

	private LinkedList<JLabel> colorLabels;
	private LinkedList<JButton> colorButtons;

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

	JLabel stateHighlightColor;
	JButton stateHighlightColorButton;

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

		colorLabels = new LinkedList<JLabel>();
		colorButtons = new LinkedList<JButton>();

		// construct default theme and set as current
		Theme defTheme = constructDefaultTheme();
		System.out.println(defTheme.toString());
		themes.add(defTheme);
		currTheme = (Theme)defTheme.clone();
		modTheme = currTheme.clone();

		// construct other default themes
		themes.add(constructBlueTheme());
		themes.add(constructGreenTheme());

		/*
		* Gui Setup
		*/
		buttonFrame = new JFrame();
		buttonFrame.setSize(1000,500);
		buttonFrame.setLayout(new GridLayout(1, 2));
		buttonFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(12, 1, 2, 2));


		title = new JLabel("Theme Colors:");

		/*
		* Color Change Buttons
		* with their labels, color choosers, and action listeners
		*/
		stateOutlineColor = new JLabel("State Outline Color:");
		stateOutlineColorButton = new JButton("Change");
		stateOutlineColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateOutlineColor(cc.getColor());
				updateMenus();
			}
		});
		colorLabels.add(stateOutlineColor);
		colorButtons.add(stateOutlineColorButton);

		stateFillColor = new JLabel("State Fill Color:");
		stateFillColorButton = new JButton("Change");
		stateFillColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateFillColor(cc.getColor());
				updateMenus();
			}
		});
		colorLabels.add(stateFillColor);
		colorButtons.add(stateFillColorButton);

		stateTextColor = new JLabel("State Text Color:");
		stateTextColorButton = new JButton("Change");
		stateTextColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateTextColor(cc.getColor());
				updateMenus();
			}
		});
		colorLabels.add(stateTextColor);
		colorButtons.add(stateTextColorButton);

		stateAcceptColor = new JLabel("State Accept Color:");
		stateAcceptColorButton = new JButton("Change");
		stateAcceptColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateAcceptColor(cc.getColor());
				updateMenus();
			}
		});
		colorLabels.add(stateAcceptColor);
		colorButtons.add(stateAcceptColorButton);


		stateStartColor = new JLabel("State Start Color:");
		stateStartColorButton = new JButton("Change");
		stateStartColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateStartColor(cc.getColor());
				updateMenus();
			}
		});
		colorLabels.add(stateStartColor);
		colorButtons.add(stateStartColorButton);

		stateHighlightColor= new JLabel("State Highlight Color:");
		stateHighlightColorButton = new JButton("Change");
		stateHighlightColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setStateHighlightColor(cc.getColor());
				updateMenus();
			}
		});
		colorLabels.add(stateHighlightColor);
		colorButtons.add(stateHighlightColorButton);

		transLineColor = new JLabel("Transition Line Color:");
		transLineColorButton = new JButton("Change");
		transLineColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setTransLineColor(cc.getColor());
				updateMenus();
			}
		});
		colorLabels.add(transLineColor);
		colorButtons.add(transLineColorButton);


		transTextColor = new JLabel("Transition Text Color:");
		transTextColorButton = new JButton("Change");
		transTextColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setTransTextColor(cc.getColor());
				updateMenus();
			}
		});
		colorLabels.add(transTextColor);
		colorButtons.add(transTextColorButton);

		backgroundColor = new JLabel("Background Color:");
		backgroundColorButton = new JButton("Change");
		backgroundColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modTheme.setBackgroundColor(cc.getColor());
				updateMenus();
			}
		});
		colorLabels.add(backgroundColor);
		colorButtons.add(backgroundColorButton);

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

		// Combo box that holds saved themes to be selected and applied
		cc = new JColorChooser(Color.BLACK);
		cc.setPreferredSize(new Dimension(100, 500));

		// Update menus so they show the right color
		updateMenus();

		// Add everything to the panel
		int i = 0;
		while (i < colorLabels.size()) {
			buttonPanel.add(colorLabels.get(i));
			buttonPanel.add(colorButtons.get(i));
			i++;
		}

		// buttonPanel.add(stateOutlineColor);
		// buttonPanel.add(stateOutlineColorButton);
		// buttonPanel.add(stateFillColor);
		// buttonPanel.add(stateFillColorButton);
		// buttonPanel.add(stateTextColor);
		// buttonPanel.add(stateTextColorButton);
		// buttonPanel.add(stateAcceptColor);
		// buttonPanel.add(stateAcceptColorButton);
		// buttonPanel.add(stateStartColor);
		// buttonPanel.add(stateStartColorButton);
		// buttonPanel.add(transLineColor);
		// buttonPanel.add(transLineColorButton);
		// buttonPanel.add(transTextColor);
		// buttonPanel.add(transTextColorButton);
		// buttonPanel.add(backgroundColor);
		// buttonPanel.add(backgroundColorButton);
		buttonPanel.add(themeTitle);
		buttonPanel.add(cb);
		buttonPanel.add(themeName);
		buttonPanel.add(saveButton);
		buttonPanel.add(applyButton);

		buttonFrame.add(buttonPanel);

		buttonFrame.add(cc);

	}

	/**
	* Returns a clone of the current theme
	*
	* @return the current theme
	*/
	public Theme getTheme() {
		return currTheme.clone();
	}

	/**
	* Constructs the default theme
	*
	* @return the default theme
	*/
	private Theme constructDefaultTheme() {
		Theme defaultTheme = new Theme("default theme", Color.BLACK, Color.WHITE,
									Color.BLACK, Color.BLACK, Color.RED, Color.YELLOW,
									Color.BLACK, Color.BLACK, Color.WHITE);
		return defaultTheme;
	}

	/**
	* Constructs the blue theme
	*
	* @return the blue theme
	*/
	private Theme constructBlueTheme() {
		Theme blueTheme = new Theme("blue theme", Color.BLUE, Color.WHITE,
									Color.BLUE, Color.BLUE, Color.BLACK, Color.YELLOW,
									Color.BLUE, Color.BLUE, Color.WHITE);
		return blueTheme;
	}

	/**
	* Constructs the green theme
	*
	* @return the green theme
	*/
	private Theme constructGreenTheme() {
		Theme greenTheme = new Theme("green theme", Color.GREEN, Color.WHITE,
									Color.GREEN, Color.GREEN, Color.BLACK, Color.YELLOW,
									Color.GREEN, Color.GREEN, Color.WHITE);
		return greenTheme;
	}

	/**
	* Constructs a custom theme
	*
	* @param name desired name of theme
	* @param stateOutlineColor desired state outline color
	* @param stateFillColor desired state fill color
	* @param stateTextColor desired state text color
	* @param stateAcceptColor desired accept state color
	* @param stateStartColor desired start state color
	* @param transLineColor desired transition line color
	* @param transTextColor desired transition text color
	* @param backgroundColor desired background color
	*
	* @return the custom theme
	*/
	private Theme constructCustomTheme(String name, Color stateOutlineColor,
									Color stateFillColor, Color stateTextColor,
									Color stateAcceptColor, Color stateStartColor,
									Color stateHighlightColor,
									Color transLineColor, Color transTextColor,
									Color backgroundColor) {
		Theme custTheme = new Theme(name, stateOutlineColor, stateFillColor,
									stateTextColor, stateAcceptColor,
									stateStartColor, stateHighlightColor, transLineColor,
									transTextColor, backgroundColor);
		return custTheme;
	}

	/**
	* Makes the theme manager visible
	*/
	public void showMenu() {
		buttonFrame.setVisible(true);
	}

	/**
	* Updates all the menu buttons with the color from the current theme
	*/
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

		stateHighlightColorButton.setBackground(modTheme.getStateHighlightColor());
		stateHighlightColorButton.setOpaque(true);

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
