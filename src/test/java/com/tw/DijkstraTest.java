package com.tw;

import junit.framework.TestCase;

public class DijkstraTest extends TestCase {

	public void testRun() {
		Graph g1 = new Graph("AB5, AC9, BC2, BD7, CD4");
		assertTrue(Dijkstra.run(g1, g1.getNode("A"), g1.getNode("D")) == 11);
		
		Graph g2 = new Graph("AB5, AB1, BC2, CA1, CD4, BD6");
		assertTrue(Dijkstra.run(g2, g2.getNode("A"), g2.getNode("D")) == 7);
		assertTrue(Dijkstra.run(g2, g2.getNode("A"), g2.getNode("F")) == Integer.MAX_VALUE);
		assertTrue(Dijkstra.run(g2, g2.getNode("F"), g2.getNode("F")) == 0);
		assertFalse(Dijkstra.run(g2, g2.getNode("D"), g2.getNode("A")) == 7);
		
		Graph g3 = new Graph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		assertTrue(Dijkstra.run(g3, g3.getNode("A"), g3.getNode("C")) == 9);
		assertTrue(Dijkstra.run(g3, g3.getNode("B"), g3.getNode("B")) == 0);
	}

}
