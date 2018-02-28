package proj2.filehandler.concretefilehandler;

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

    public void save() {
        try {
            String home = System.getProperty("user.home");
		    File file = new File(home+"/Downloads/" + "guiImage" + ".png");
        }
    }

}
