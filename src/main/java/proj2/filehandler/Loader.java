package proj2.filehandler;

import proj2.filehandler.Saver;

/**
* Interface for loaders of finite state machines
*/
public interface Loader{

	public void load(String filename);

	public String toString();
}
