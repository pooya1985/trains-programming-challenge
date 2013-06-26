package com.tw;

public class TripsAction extends Action {
	
	public String execute(Graph graph) {
		String[] parameters = getParameters();

		// second & third parameter specify the condition
		// i.e. "<5 stops"
		// fourth parameter is the nodes specification
		if (parameters.length < 4) {
			throw new IllegalArgumentException(App.getProperty("invalid_action") + ": " + toString() + 
					App.getProperty("incomplete_action"));
		}
		
		String condition = parameters[1];
		String entity    = parameters[2];
		String nodesSpec = parameters[3];
		
		String operator  = condition.substring(0,1);
		int value        = Integer.parseInt(condition.substring(1));
		
		Node[] nodes     = getNodes(graph, nodesSpec);
		
		if (nodes.length != 2) {
			throw new IllegalArgumentException(App.getProperty("invalid_action") + ": " + toString() +
					App.getProperty("must_specify_source_and_target"));
		}
		
		Node start = nodes[0];
		Node target = nodes[1];
		Condition filterCond = new Condition(operator, value, entity);
		Condition runCond = filterCond;
		
		if (operator.equals("=")) {
			runCond = new Condition("<", value + 1, entity);
		}
		
		Path[] paths = new PathFinder(runCond, filterCond, false).findAllPaths(graph, start, target);
		
		return String.valueOf(paths.length);
	}
}
