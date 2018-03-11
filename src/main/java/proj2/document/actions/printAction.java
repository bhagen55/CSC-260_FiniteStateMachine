package proj2.document.actions;

import proj2.document.Action;

public class printAction implements Action
{
    public printAction()
    {

    }

    public void execute()
    {
        System.out.println("This action is a print action");
    }

    public String toString()
    {
        return "PrintAction";
    }
}
