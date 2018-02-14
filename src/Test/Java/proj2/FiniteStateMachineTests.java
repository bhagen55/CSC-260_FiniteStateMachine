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
	TestingSaveAndLoad t;
    
    
    @Before
    public void setUp()
    {
        d= new Document();
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
    public void TestingAddVertex()
    {
    	d.addVertex("A");
    	assertTrue("Testing adding a single Vertex",d.hasVertex("A");
    }



    
}

