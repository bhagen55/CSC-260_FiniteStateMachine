package proj2;

import proj2.document.Document;
import java.io.*;


public class FileFormatController
{
	Document d;
	PrintWriter writer;
	public FileFormatController(Document given) throws FileNotFoundException
	{
		d=given;
	}

	public void saveFile() throws FileNotFoundException {
		writer = new PrintWriter("FiniteStateMachine.txt");
		writer.println(d.toString());
	}

	public void loadFile(){

	}

}
