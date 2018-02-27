package proj2.view.theme;

/**
* Interface for themes that can be applied to the
* of the fsm gui
*/
public interface Observer {

    public Color getBackgroundColor();
    public Color getStateColor();
    public Color getTransitionColor();
    public Color getStartStateColor();

    public Shape getStateShape();
    public Shape getTransitionShape();
}
