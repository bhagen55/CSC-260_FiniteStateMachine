package proj2.filehandler;

/**
* Interface for savers of finite state machines
*/
public interface Saver{

	public void save(String filename);

	public String toString();
}
