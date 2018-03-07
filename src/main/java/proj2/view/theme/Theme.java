package proj2.view.theme;

import java.awt.Color;
import java.awt.Shape;

/**
* Class that defines the Theme objects that determine the theme of the FSM
*/
public class Theme
{
	private String name;

	private Color stateOutlineColor;
	private Color stateFillColor;
	private Color stateTextColor;
	private Color stateAcceptColor;
	private Color stateStartColor;

	private Color transLineColor;
	private Color transTextColor;

	private Color backgroundColor;

	/**
	* Constructor for a theme, requires all colors and name string
	*/
	public Theme(String name, Color stateOutlineColor, Color stateFillColor,
				Color stateTextColor, Color stateAcceptColor,
				Color stateStartColor, Color transLineColor,
				Color transTextColor, Color backgroundColor)
	{
		this.name = name;

		this.stateOutlineColor = stateOutlineColor;
		this.stateFillColor = stateFillColor;
		this.stateTextColor = stateTextColor;
		this.stateAcceptColor = stateAcceptColor;
		this.stateStartColor = stateStartColor;

		this.transLineColor = transLineColor;
		this.transTextColor = transTextColor;

		this.backgroundColor = backgroundColor;
	}


	/*
	* Getters
	*/

	/**
	* Gets name of theme
	*
	* @return name of string
	*/
	public String toString() {
		return name;
	}

	/**
	* Gets color of state outline
	*
	* @return color of state outline
	*/
	public Color getStateOutlineColor() {
		return stateOutlineColor;
	}

	/**
	* Gets color state fill
	*
	* @return color of state fill
	*/
	public Color getStateFillColor() {
		return stateFillColor;
	}

	/**
	* Gets color of state text
	*
	* @return color of state text
	*/
	public Color getStateTextColor() {
		return stateTextColor;
	}

	/**
	* Gets color of accept state
	*
	* @return color of accept state
	*/
	public Color getStateAcceptColor() {
		return stateAcceptColor;
	}

	/**
	* Gets color of start state
	*
	* @return color of start state
	*/
	public Color getStateStartColor() {
		return stateStartColor;
	}

	/**
	* Gets color of transition line
	*
	* @return color of transition line
	*/
	public Color getTransLineColor() {
		return transLineColor;
	}

	/**
	* Gets color of transition text
	*
	* @return color of transition text
	*/
	public Color getTransTextColor() {
		return transTextColor;
	}

	/**
	* Gets background color
	*
	* @return background color
	*/
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/*
	* Setters
	*/

	/**
	* Sets name of theme
	*
	* @param name desired name of theme
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Sets color of state outline
	*
	* @param color desired color of state outline
	*/
	public void setStateOutlineColor(Color color) {
		stateOutlineColor = color;
	}

	/**
	* Sets color of state fill
	*
	* @param color desired color of state fill
	*/
	public void setStateFillColor(Color color) {
		stateFillColor = color;
	}

	/**
	* Sets color of state text
	*
	* @param color desired color of state text
	*/
	public void setStateTextColor(Color color) {
		stateTextColor = color;
	}

	/**
	* Sets color of accept state
	*
	* @param color desired color of accept state
	*/
	public void setStateAcceptColor(Color color) {
		stateAcceptColor = color;
	}

	/**
	* Sets color of start state
	*
	* @param color desired color of start state
	*/
	public void setStateStartColor(Color color) {
		stateStartColor = color;
	}

	/**
	* Sets color of transition line
	*
	* @param color desired color of transition line
	*/
	public void setTransLineColor(Color color) {
		transLineColor = color;
	}

	/**
	* Sets color of transition text
	*
	* @param color desired color of transition text
	*/
	public void setTransTextColor(Color color) {
		transTextColor = color;
	}

	/**
	* Sets color of background
	*
	* @param color desired color of background
	*/
	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}

	/**
	* Clones theme object
	*
	* @return copy of clone object
	*/
	public Theme clone() {
		Theme copy = new Theme(name, stateOutlineColor, stateFillColor,
					stateTextColor, stateAcceptColor,
					stateStartColor, transLineColor,
					transTextColor, backgroundColor);
		return copy;
	}
}
