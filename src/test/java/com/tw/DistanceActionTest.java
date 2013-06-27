package com.tw;

import junit.framework.TestCase;

public class DistanceActionTest extends TestCase {

	public void testExecute() {
		Graph g1 = new Graph("AB2, BA3, BD5, BE7, DB11, EB13, CE17, EC19, DE23, ED29, CF31 " +
							  "FC37, EF41, FE43, FA53, AF59, DA61, AD67, EA71, AE73");	
		
		DistanceAction a1 = new DistanceAction();
		a1.setParameters(new String[] {"distance?", "EDBEF"});
		assertEquals(a1.execute(g1), "88");
		
		DistanceAction a2 = new DistanceAction();
		a2.setParameters(new String[] {"schmarrn", "EDBEF"});
		assertEquals(a2.execute(g1), "88");

		try {
			DistanceAction a3 = new DistanceAction();
			a3.setParameters(new String[] {"distance?"});
			a3.execute(g1);
			fail("accepted incomplete action");
		} catch(IllegalArgumentException e) {
			
		}
	}

}
