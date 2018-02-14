package proj2;

import proj2.document.Document;
import java.io.*;

public class FileFormatController
{
	Document d;
	PrintWriter writer;
	public FileFormatController(Document given) throws IOException
	{
		d=given;
	}

	public void saveFile() {
		//writer = new PrintWriter("FiniteStateMachine.txt");
		//writer.print(d.toString());
	}

	public void loadFile(){

	}

}
