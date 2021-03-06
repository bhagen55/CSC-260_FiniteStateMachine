package proj2.view.gui;

import java.awt.image.BufferedImage;

/**
* Allows printing of any object that can return
* a buffered image of itself
*/
public interface Printable
{
	/**
    * Get a printable representation of the jpanel
    * but only with states and transitions
    *
    * @return bufferedimage representation of jpanel without buttons
    */
    public BufferedImage getPrintable();
}
