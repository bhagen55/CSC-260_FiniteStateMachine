package proj2.document.actions;

import proj2.document.Action;

public class PrintAction implements Action
{
    /**
     * Default constructor for the print action.
     */
    public PrintAction()
    {

    }

    /**
     * Executes the print action by printing out a message.
     */
    public void execute()
    {
        System.out.println("This action is a print action");
    }

    /**
     * toString method for the PrintAction class.
     *
     * @return string saying that this is the PrintAction class
     */
    public String toString()
    {
        return "PrintAction";
    }
}
