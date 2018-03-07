package test.java.testfiles;

import proj2.document.Document;
import proj2.filehandler.concretefilehandler.TextSave;
import java.io.*;

public class TestingSaveAndLoad{
	//Static Document d;
	//Static TextSave f;
	public TestingSaveAndLoad()
	{
		// try{
		Document d;
	    TextSave f;
		d= new Document();
		f= new TextSave(d);
		d.addState("A",10,52);

		d.addState("B",55,117);
		d.addState("C",1,27);
		d.addTransition("A","B","z");
		f.saveFile("FiniteStateMachine");

	// }
		 // catch(FileNotFoundException ex) {
         //     System.out.println(
         //         "Unable to open file '" +
         //         "FiniteStateMachine" + "'");
         // }
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
	    TextSave f;
		d= new Document();
		f= new TextSave(d);
		d.addState("A");
		d.addState("B");
		d.addState("C");
		d.addTransition("A","B","z");
		f.saveFile("FiniteStateMachine");

	}
}
