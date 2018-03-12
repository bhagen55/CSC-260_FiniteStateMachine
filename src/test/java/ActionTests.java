package proj2.test;

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
import proj2.document.actions.*;

@RunWith(JUnit4.class)
public class ActionTests
{
	private Document doc;

	private Action noAction;
	private Action printAction;
	private Action soundAction;

	@Before
	public void setUp()
	{
		doc = new Document();
		noAction = new NoAction();
		printAction = new PrintAction();
		soundAction = new SoundAction();
	}

	@Test
	public void defaultAction()
	{
		doc.addState("a");
		assertEquals("Default state action is noAction", doc.getAction("a").toString(), noAction.toString());
	}

	@Test
	public void addAction()
	{
		doc.addState("a");
		doc.addAction("a", soundAction);
		assertEquals("Adding an action to a state works as it should", doc.getAction("a").toString(), soundAction.toString());
	}

	@Test
	public void changeAction()
	{
		doc.addState("a");
		doc.addAction("a", soundAction);
		doc.addAction("a", printAction);
		assertEquals("Changing an action works as it should", doc.getAction("a").toString(), printAction.toString());
	}
}
