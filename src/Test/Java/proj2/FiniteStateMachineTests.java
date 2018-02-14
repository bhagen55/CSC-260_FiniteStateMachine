package proj2;



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
    	t= new TestingSaveAndLoad();
    }

    @Test
    public void TestingAddVertexAndHasVertex()
    {
    	d.addVertex("A");
    	assertTrue("Testing adding a single Vertex",d.hasVertex("A"));
    }

    @Test
    public void TestingAddAndRemoveVertex()
    {
    	d.addVertex("A");
    	d.removeVertex("A");
    	assertTrue("Testing adding and removing a single Vertex",d.toString().compareTo(d2.toString())==0);
    }

    @Test
    public void TestingAddVertexDoesNotMakeMultipulsNumVertexes()
    {
    	d.addVertex("A");
    	d.addVertex("A");
    	d2.addVertex("A");
    	assertTrue("Testing vertex is added",d.hasVertex("A"));
    	assertTrue("Testing num Vertexes does not increase when duplicates are attempted",d.numVertices()==1);
    	assertTrue("Testing duplicate is the same as if only adding a vertex once",d.toString().compareTo(d2.toString())==0);
    }




    
}

