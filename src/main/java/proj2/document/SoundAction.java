package proj2.document;

import java.util.ArrayList;
import java.awt.Toolkit;

public Class SoundAction implements Action
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
        Toolkit.getDefaultToolkit().beep()
    }
}
