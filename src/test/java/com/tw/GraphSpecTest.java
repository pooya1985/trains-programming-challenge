package com.tw;

import junit.framework.TestCase;

public class GraphSpecTest extends TestCase {

	public void testGraphString() {
		try {
			Graph g1 = new Graph("AB5, DFA, DB9 FA1");
			fail("Invalid DFA spec accepted");
		} catch(IllegalArgumentException e) {
		}
		
		try {
			Graph g2 = new Graph("AB5, DF3, DB , BA9");
			fail("Incomplete DB spec accepted");
		} catch(IllegalArgumentException e) {
		}
		
		Graph g3 = new Graph("AB1 BD8, \n FZ5");
		assertTrue(g3.getNodes().length==5);
	}

}
