package proj2;

import proj2.document.Document;
import java.io.PrintWriter;

public class FileFormatController
{
	Document d;
	public FileFormatController(Document given)
	{
		d=given;
	}

	public void saveFile(){
		PrintWriter writer = new PrintWriter("FiniteStateMachien.txt");
		writer.print(d.toString());
	}

	public void loadFile(){

	}

}