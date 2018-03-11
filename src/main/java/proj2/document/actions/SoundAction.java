package proj2.document.actions;

import java.awt.Toolkit;

import proj2.document.Action;

public class SoundAction implements Action
{
    /**
     * Default SoundAction constructor
     */
    public SoundAction()
    {

    }

    /**
     * Executes a sound action
     */
    public void execute()
    {
        Toolkit.getDefaultToolkit().beep();
    }

    /**
     * toString method for the SoundAction class.
     *
     * @return string saying this is the SoundAction class
     */
    public String toString()
    {
        return "SoundAction";
    }
}
