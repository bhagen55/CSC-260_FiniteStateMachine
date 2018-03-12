package test.java.testfiles;

import proj2.document.Document;
import proj2.filehandler.concretefilehandler.TextSave;
import proj2.filehandler.concretefilehandler.FSMSave;

import java.io.*;
import proj2.document.Action;
import proj2.document.actions.*;

public class TestingSaveAndLoad{
	Document d1;
	Document d2;
	TextSave text;
	FSMSave fsm;
	public TestingSaveAndLoad()
	{
		 
		d1= new Document();
		d2= new Document();
	    text= new TextSave(d1);
	    fsm= new FSMSave(d2);
	    Action print=new PrintAction();
	    Action none= new NoAction();
		Action sound=new SoundAction();
		
		d1.addState("A",10,52);
		d1.addAction("A",none);
		d1.addState("B",55,117);
		d1.addAction("B",sound);
		d1.addState("C",1,27);
		d1.addAction("C",print);
		d1.addTransition("A","B","z");
		d1.addTransition("C","A","r");
		d1.addTransition("A","A","s");
		d1.addTransition("B","A","q");
		d1.toggleAccept("A");
		
		d2.addState("A",10,52);
		d2.addAction("A",none);
		d2.addState("B",55,117);
		d2.addAction("B",sound);
		d2.addState("C",1,27);
		d2.addAction("C",print);
		d2.addTransition("A","B","z");
		d2.addTransition("C","A","r");
		d2.addTransition("A","A","s");
		d2.addTransition("B","A","q");
		d2.toggleAccept("A");



	 
		  
	}

	public void save()
	{
		text.save("FiniteStateMachine");
		FSMSave.save("FiniteStateMachine");
	}

	public void load()
	{
		text.load("FiniteStateMachine");
		FSMSave.load("FiniteStateMachine");
	}

	public void save2()
	{
		text.save("FiniteStateMachine_copy");
		FSMSave.save("FiniteStateMachine_copy");
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
