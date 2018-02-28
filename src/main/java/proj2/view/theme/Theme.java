package proj2.view.theme;

import java.awt.Color;
import java.awt.Shape;

/**
* Interface for themes that can be applied to the
* of the fsm gui
*/
public interface Theme {

    public Color getBackgroundColor();
    public Color getStateColor();
    public Color getTransitionColor();
    public Color getStartStateColor();

    public Shape getStateShape();
    public Shape getTransitionShape();
}
