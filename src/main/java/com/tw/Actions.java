package com.tw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Actions {
	private List<Action> actionsList;
	private ActionFactory actionFactory;
	
	/**
	 * Reads an action specification and instantiates the required Action object
	 * and puts it on the actions list for execution later.
	 * @param str
	 * @throws IllegalArgumentException
	 */
	public void read(String str) throws IllegalArgumentException {
		if (!str.isEmpty()) {
			String words[] = str.split("\\s");
			
			// The general syntax of actions is:
			// <action query> [param1] [param2]...
			String name = words[0];
			Action action = actionFactory.createAction(name);
			
			if (action == null) {
				throw new IllegalArgumentException(App.getProperty("unknown_action") + ": " + name);
			}
			
			action.setParameters(words);
			
			actionsList.add(action);
		}
	}
	
	/**
	 * Constructs an Actions object reading the actions to be executed from an
	 * input stream
	 * @param stream
	 * @throws IOException
	 */
	public Actions(InputStream stream) throws IOException {
		this();
		
		// Read actions from input stream 
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String str;
		while((str = reader.readLine()) != null) {
			read(str);
		}
	}
	
	public Actions() {
		actionFactory = new ActionFactory();
		actionsList   = new ArrayList<Action>();
	}
	
	/**
	 * Executes this action on the given graph
	 * @param graph
	 * @return the result of the action
	 */
	public String execute(Graph graph) {
		StringBuilder s = new StringBuilder();

		for (Action action : actionsList) {
			s.append(action + " => " + action.execute(graph) + "\n");
		}
		
		return s.toString();
	}
}
