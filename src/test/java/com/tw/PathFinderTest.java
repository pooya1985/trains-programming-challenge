package com.tw;

import junit.framework.TestCase;

public class PathFinderTest extends TestCase {

	public void testFindAllPaths() {
		Graph g1 = new Graph("AB3, BC3, CD3, DA3");
		
		PathFinder pf1_1 = new PathFinder(new Condition("<", 101, "stops"), null, false);
		assertTrue(pf1_1.findAllPaths(g1, g1.getNode("A"), g1.getNode("A")).length == 25);
		
		PathFinder pf1_2 = new PathFinder(new Condition("<", 12, "distance"), null, false);
		assertTrue(pf1_2.findAllPaths(g1, g1.getNode("D"), g1.getNode("D")).length == 0);
		assertTrue(pf1_2.findAllPaths(g1, g1.getNode("D"), g1.getNode("C")).length == 1);
		
		Graph g2 = new Graph("AB2, BA3, BD5, BE7, DB11, EB13, CE17, EC19, DE23, ED29, CF31 " +
		                     "FC37, EF41, FE43, FA53, AF59, DA61, AD67, EA71, AE73");
		
		PathFinder pf2_1 = new PathFinder(new Condition("<", 4, "stops"), null, true);
		assertTrue(pf2_1.findAllPaths(g2, g2.getNode("A"), g2.getNode("A")).length == 12);

		PathFinder pf2_2 = new PathFinder(new Condition("<", 5, "stops"), null, true);
		assertTrue(pf2_2.findAllPaths(g2, g2.getNode("A"), g2.getNode("A")).length == 34);
		
		// The acid test. If this one fails, might have to increase the Java Heap space
		PathFinder pf2_3 = new PathFinder(new Condition("<", 10, "stops"), null, false);
		assertTrue(pf2_3.findAllPaths(g2, g2.getNode("A"), g2.getNode("A")).length == 26776);
		
		PathFinder pf2_4 = new PathFinder(new Condition("<", 6, "stops"), 
										  new Condition("=", 88, "distance"), 
										  false);
		assertTrue(pf2_4.findAllPaths(g2, g2.getNode("A"), g2.getNode("A")).length == 1);
	}

}
