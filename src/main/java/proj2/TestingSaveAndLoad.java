package proj2;

import proj2.document.Document;
import java.io.*;

public class TestingSaveAndLoad{
	//Static Document d;
	//Static FileFormatController f;
	public TestingSaveAndLoad()
	{
		/*
		d= new Document();
		f= new FileFormatController(d);
		d.addVertex("A");
		d.addVertex("B");
		d.addVertex("C");
		d.addEdge("A","B","z");
		f.saveFile();
		*/
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		Document d;
	    FileFormatController f;
		d= new Document();
		f= new FileFormatController(d);
		d.addVertex("A");
		d.addVertex("B");
		d.addVertex("C");
		d.addEdge("A","B","z");
		f.saveFile();
	}
}