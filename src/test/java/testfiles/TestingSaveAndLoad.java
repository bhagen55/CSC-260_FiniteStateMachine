package test.java.testfiles;

import proj2.document.Document;
import proj2.filehandler.concretefilehandler.TextSave;
import java.io.*;
import proj2.document.Action;
import proj2.document.actions.*;

public class TestingSaveAndLoad{
	Document d;
	TextSave f;
	public TestingSaveAndLoad()
	{
		 
		d= new Document();
	    f= new TextSave(d);
	    Action print=new PrintAction();
	    Action none= new NoAction();
		Action sound=new SoundAction();
		d= new Document();
		f= new TextSave(d);
		d.addState("A",10,52);
		d.addAction("A",none);
		d.addState("B",55,117);
		d.addAction("B",sound);
		d.addState("C",1,27);
		d.addAction("C",print);
		d.addTransition("A","B","z");
		d.addTransition("C","A","r");
		d.addTransition("A","A","s");
		d.addTransition("B","A","q");
		d.toggleAccept("A");
		




	 
		  
	}

	public void save()
	{
		f.save("FiniteStateMachine");
	}

	public void load()
	{
		f.load("FiniteStateMachine");
	}

	public void save2()
	{
		f.save("FiniteStateMachine_copy");
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
		// f.saveFile("FiniteStateMachine");

	}
}
