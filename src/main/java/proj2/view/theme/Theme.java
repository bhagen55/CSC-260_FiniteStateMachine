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

	public Theme(String name, Color stateOutlineColor, color stateFillColor,
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

	public String toString() {
		return name;
	}


	public Color getStateOutlineColor() {
		return stateOutlineColor;
	}

	public Color getStateFillColor() {
		return stateFillColor;
	}

	public Color getStateTextColor() {
		return stateTextColor;
	}

	public Color getStateAcceptColor() {
		return stateAcceptColor;
	}

	public Color getStateStartColor() {
		return stateStartColor;
	}

	public Color getTransLineColor() {
		return transLineColor;
	}

	public Color getTransTextColor() {
		return transTextColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/*
	* Setters
	*/

	public void setStateOutlineColor(Color color) {
		stateOutlineColor = color;
	}

	public void setStateFillColor(Color color) {
		stateFillColor = color;
	}

	public void setStateTextColor(Color color) {
		stateTextColor = color;
	}

	public void setStateAcceptColor(Color color) {
		stateAcceptColor = color;
	}

	public void setStateStartColor(Color color) {
		stateStartColor = color;
	}

	public void setTransLineColor(Color color) {
		transLineColor = color;
	}

	public void setTransTextColor(Color color) {
		transTextColor = color;
	}

	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}
}
