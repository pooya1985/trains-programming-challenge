package com.tw;

import java.util.ArrayList;

public class TripsAction extends Action {

	public ArrayList<Path> findPaths(Graph graph, 
									 Node current, 
									 Node target, 
									 Path ancestor, 
									 Condition runCond,
									 Condition filterCond) {
		ArrayList<Path> paths = new ArrayList<Path>();
		
		for (Edge edge: current.getNeighbours()) {
			Node destination = edge.getDestination();
			int distance = edge.getDistance();
			Path newPath = new Path(ancestor.getDistance() + distance, ancestor.getHops() + 1);
			if (!runCond.evaluate(newPath)) {
				return paths;
			}
			if (destination.isEqual(target) && filterCond.evaluate(newPath)) {
				paths.add(newPath);
			}
			for (Path path: findPaths(graph, destination, target, newPath, runCond, filterCond)) {
				paths.add(path);
			}
		}
		return paths;
	}
	
	public Path[] findAllPaths(Graph graph, Node start, Node target, Condition runCond, Condition filterCond) {
		return findPaths(graph, start, target, new Path(0,0), runCond, filterCond).toArray(new Path[0]);
	}
	
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
		
		Node start = nodes[0];
		Node target = nodes[1];
		Condition filterCond = new Condition(operator, value, entity);
		Condition runCond = filterCond;
		
		if (operator.equals("=")) {
			runCond = new Condition("<", value + 1, entity);
		}
		
		Path[] paths = findAllPaths(graph, start, target, runCond, filterCond);
		
		return String.valueOf(paths.length);
	}
}
