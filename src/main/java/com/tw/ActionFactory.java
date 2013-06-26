package com.tw;

import java.util.Map;
import java.util.TreeMap;

public class ActionFactory {
	private Map<String, Class<?> > actionsMap;
	
	public Action createAction(String s) {
		Class<?> c = actionsMap.get(s);
		Action action = null;

		if (c != null) {
			try {
				action = (Action) c.newInstance();
			} catch (Exception e) {
				System.err.println("Internal error creating " + s + " action: " + e.getMessage());
			}
		}		

		return action;
	}
	
	public ActionFactory() {
		actionsMap = new TreeMap<String, Class<?> >();
		
		actionsMap.put("distance?", DistanceAction.class);
		actionsMap.put("trips?", TripsAction.class);
		actionsMap.put("shortest?", ShortestPathAction.class);
	}
}
