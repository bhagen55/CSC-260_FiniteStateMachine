package proj2;

import proj2.document.Document;
import java.io.PrintWriter;

public class FileFormatController
{
	Document d;
	PrintWriter writer;
	public FileFormatController(Document given)
	{
		d=given;
	}

	public void saveFile(){
		writer = new PrintWriter("FiniteStateMachine.txt");
		writer.print(d.toString());
	}

	public void loadFile(){

	}

}
