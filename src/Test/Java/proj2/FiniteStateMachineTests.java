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
    	//uncomment to create test file
    	//t= new TestingSaveAndLoad();
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
    public void TestingRemoveVertexAndHasVertex()
    {
    	d.addVertex("A");
    	d.addVertex("B");
    	d.removeVertex("B");
    	assertTrue("Testing adding a single Vertex",d.hasVertex("A"));
    	assertFalse("Testing removed vertex is not found by hasVertex",d.hasVertex("B"));
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

	@Test
    public void TestingAddEdge()
    {
    	d.addVertex("A");
    	d.addVertex("B");
    	d.addEdge("A","B","r");
    	assertTrue("Testing hasEdge for a single added edge",d.hasEdge("A","B","r"));
    	assertFalse("Testing nonexistent Edge not found",d.hasEdge("B","A","z"));
    }

	@Test
    public void TestingRemoveEdge()
    {
    	d.addVertex("A");
    	d.addVertex("B");
    	d.addEdge("A","B","r");
    	assertTrue("Testing hasEdge for a single added edge",d.hasEdge("A","B","r"));
    	d.removeEdge("A","B","r");
    	assertFalse("Testing removed Edge not found",d.hasEdge("A","B","r"));
    }

    @Test
    public void TestingNumEdges()
    {
    	d.addVertex("A");
    	d.addVertex("B");
    	assertTrue("Testing hasEdge for a single added edge",d.numEdges("A")==0);
    	d.addEdge("A","B","r");
    	assertTrue("Testing Edges not both ways",d.numEdges("B")==0);
    	assertTrue("Testing hasEdge for a single added edge",d.numEdges("A")==1);
    	d.removeEdge("A","B","r");
    	assertTrue("Testing removed Edge not found",d.numEdges("A")==0);
    	assertTrue("Testing Edges not both ways",d.numEdges("B")==0);
    }
}

