package com.tw;

import junit.framework.TestCase;

public class ConditionTest extends TestCase {

	public void testEvaluate() {
		Condition c1 = new Condition("<", 5, "stops");
		Path p1_1 = new Path(10, 2);
		Path p1_2 = new Path(3, 8);
		assertTrue(c1.evaluate(p1_1));
		assertFalse(c1.evaluate(p1_2));
		
		Condition c2 = new Condition("=", 7, "distance");
		Path p2_1 = new Path(1, 7);
		Path p2_2 = new Path(7, 5);
		assertFalse(c2.evaluate(p2_1));
		assertTrue(c2.evaluate(p2_2));
		
		Condition c3 = new Condition(">", 3, "stops");
		Path p3_1 = new Path(3, 5);
		Path p3_2 = new Path(3, 3);
		Path p3_3 = new Path(3, 4);
		assertTrue(c3.evaluate(p3_1));
		assertFalse(c3.evaluate(p3_2));
		assertTrue(c3.evaluate(p3_3));
	}
}
