package proj2;

import proj2.document.Document;
import java.io.*;

public class TestingSaveAndLoad{
	//Static Document d;
	//Static FileFormatController f;
	public TestingSaveAndLoad()
	{
		try{
		Document d;
	    FileFormatController f;
		d= new Document();
		f= new FileFormatController(d);
		d.addVertex("A",10,52);

		d.addVertex("B",55,117);
		d.addVertex("C",1,27);
		d.addEdge("A","B","z");
		f.saveFile("FiniteStateMachine");

	}
		 catch(FileNotFoundException ex) {
             System.out.println(
                 "Unable to open file '" +
                 "FiniteStateMachine" + "'");
         }
         // catch(IOException ex) {
         //     System.out.println(
         //         "Error reading file '"
         //         + "FiniteStateMachine" + "'");
         //
         // }
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
		f.saveFile("FiniteStateMachine");

	}
}
