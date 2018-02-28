package proj2.filehandler.concretefilehandler;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

import proj2.view.gui.Printable;

/**
* Takes in a printable object and a filepath and saves an
* image of the printable to the filepath in png format
*/
public class ImageSave
{
    BufferedImage bufimg;

    public ImageSave(Printable gui, String filepath) {
        bufimg = gui.getPrintable();
    }

    /**
    * Saves a printable gui as a png in the user's download folder
    */
    public void save() {
        try {
            // Create a file object to hold the path to the text file
            String home = System.getProperty("user.home");
		    File file = new File(home+"/Downloads/" + "guiImage" + ".png");
            // Create file if it doesn't exist
		    file.getParentFile().mkdirs();
            ImageIO.write(bufimg, "png", file);
        }
        catch (IOException e) {
            System.out.println("Saving as Image failed.");
        }
    }
}
