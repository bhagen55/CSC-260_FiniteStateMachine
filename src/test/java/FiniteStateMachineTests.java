package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import proj2.document.*;
import proj2.*;
import test.java.testfiles.TestingSaveAndLoad;

@RunWith(JUnit4.class)
public class FiniteStateMachineTests
{
	Document d;
	Document d2;

	TestingSaveAndLoad t;


    @Before
    public void setUp()
    {
        d= new Document();
        d2= new Document();

    }

    @After
    public void tearDown()
    {
       d=null;
    }

    @Test
    public void TestingSaveAndLoad()
    {
    	//creates two save files that should be identitcal, one will be named FiniteStateMachine.txt 
        // the other will be named FiniteStateMachine_copy.txt
    	t= new TestingSaveAndLoad();
        t.save();
        t.load();
        t.save2();
    }

    @Test
    public void TestingAddStateAndHasState()
    {
    	d.addState("A");
    	assertTrue("Testing adding a single State",d.hasState("A"));
    }

    @Test
    public void TestingAddAndRemoveState()
    {
    	d.addState("A");
    	d.removeState("A");
    	assertTrue("Testing adding and removing a single State",d.toString().compareTo(d2.toString())==0);
    }

    @Test
    public void TestingRemoveStateAndHasState()
    {
    	d.addState("A");
    	d.addState("B");
    	d.removeState("B");
    	assertTrue("Testing adding a single State",d.hasState("A"));
    	assertFalse("Testing removed state is not found by hasState",d.hasState("B"));
    }

    @Test
    public void TestingAddStateDoesNotMakeMultipulsNumStatees()
    {
    	d.addState("A");
    	d.addState("A");
    	d2.addState("A");
    	assertTrue("Testing state is added",d.hasState("A"));
    	assertTrue("Testing num Statees does not increase when duplicates are attempted",d.numVertices()==1);
    	assertTrue("Testing duplicate is the same as if only adding a state once",d.toString().compareTo(d2.toString())==0);
    }

	@Test
    public void TestingAddTransition()
    {
    	d.addState("A");
    	d.addState("B");
    	d.addTransition("A","B","r");
    	assertTrue("Testing hasTransition for a single added transition",d.hasTransition("A","B","r"));
    	assertFalse("Testing nonexistent Transition not found",d.hasTransition("B","A","z"));
    }

	@Test
    public void TestingRemoveTransition()
    {
    	d.addState("A");
    	d.addState("B");
    	d.addTransition("A","B","r");

    	assertTrue("Testing hasTransition for a single added transition",d.hasTransition("A","B","r"));
    	d.removeTransition("A","B","r");
    	assertFalse("Testing removed Transition not found",d.hasTransition("A","B","r"));
    }

    @Test
    public void TestingNumTransitions()
    {
    	d.addState("A");
    	d.addState("B");
    	assertTrue("Testing hasTransition for a single added transition",d.numTransitions("A")==0);
    	d.addTransition("A","B","r");
    	assertTrue("Testing Transitions not both ways",d.numTransitions("B")==0);
    	assertTrue("Testing hasTransition for a single added transition",d.numTransitions("A")==1);
    	d.removeTransition("A","B","r");
    	assertTrue("Testing removed Transition not found",d.numTransitions("A")==0);
    	assertTrue("Testing Transitions not both ways",d.numTransitions("B")==0);
    }


	// BEGINNING OF STRING SIMULATOR TESTS


	//@Test
	//public void Testing

}
