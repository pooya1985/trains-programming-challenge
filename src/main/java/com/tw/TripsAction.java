package com.tw;

public class TripsAction extends Action {
	public String execute(Graph graph) {
		String[] parameters = getParameters();

		// second & third parameter specify the condition
		// i.e. "<5 stops"
		// fourth parameter is the nodes specification
		if (parameters.length < 4) {
			throw new IllegalArgumentException("Invalid action:" + toString() + 
					": incomplete action specification");
		}
		
		String condition = parameters[1];
		String entity    = parameters[2];
		String nodesSpec = parameters[3];
		
		String operator  = condition.substring(0,1);
		int value        = Integer.parseInt(condition.substring(1));
		
		Node[] nodes     = getNodes(graph, nodesSpec);
		
		if (nodes.length != 2) {
			throw new IllegalArgumentException("Invalid action:" + toString() +
					": Must specify just a start and a target node");
		}
		return operator + ", " + value + ", " +entity+", "+ nodesSpec;
	}
}
