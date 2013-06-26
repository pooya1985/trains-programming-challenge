package com.tw;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ShortestPathAction extends Action {
	private static int runDijkstra(Graph graph, Node start, Node target) {
		Set<Node> unsettled = new HashSet<Node>();
		Set<Node> settled = new HashSet<Node>();
		
		Node[] nodes = graph.getNodes();
		Map<String, Integer> distance = new TreeMap<String, Integer>();

		for (Node node: nodes) {
			distance.put(node.getName(), Integer.MAX_VALUE);
		}
				
		unsettled.add(start);
		distance.put(start.getName(), 0);
		
		while(!unsettled.isEmpty()) {
			Node nearest=null;
			int minDistance = Integer.MAX_VALUE;
			for(Node n : unsettled) {
				Integer dist = distance.get(n.getName());
				if ( dist < minDistance) {
					nearest = n;
					minDistance = dist ;
				}
			}
			unsettled.remove(nearest);
			settled.add(nearest);
			
			for (Edge edge : nearest.getNeighbours()) {
				if (!settled.contains(edge.getDestination())) {
					int newDistance = distance.get(nearest.getName()) + edge.getDistance();
					Integer dist = distance.get(edge.getDestination().getName());
					if (newDistance < dist) {
						distance.put(edge.getDestination().getName(), newDistance);
						unsettled.add(edge.getDestination());
					}
				}
			}
		}
		return distance.get(target.getName());
	}

	public String execute(Graph graph) {
		String[] parameters = getParameters();

		// second parameter contains the nodes specification
		if (parameters.length < 2) {
			throw new IllegalArgumentException("Invalid action:" + toString() + 
					": missing nodes specification");
		}
		
		String nodesSpec = parameters[1];
		Node[] nodes     = getNodes(graph, nodesSpec);
	
		if (nodes.length != 2) {
			throw new IllegalArgumentException("Invalid action:" + toString() +
					": Must specify just a start and a target node");
		}
		
		Node start = nodes[0];
		Node target = nodes[1];
		
		int shortestDistance = Integer.MAX_VALUE;

		if (start.isEqual(target)) {
			// This is a bit of a special case. The Dijkstra algorithm finds the
			// shortest path between two nodes. When the start and end nodes are
			// the same, the distance will of course be 0. The problem requests
			// (implicitly) that in this case a different path be found instead
			// of just returning the obvious answer (no need to travel).
			for (Edge edge : start.getNeighbours()) {
				int distance = runDijkstra(graph, edge.getDestination(), target);
				if (distance != Integer.MAX_VALUE) {
					// a path has been found from a neighbour of the start node
					// to the start node. Total distance will of course include
					// the distance from the start to this neighbour.
					distance += edge.getDistance();
					if (distance < shortestDistance) {
						// a new shortest distance has been found
						shortestDistance = distance;
					}
				}
			}
		} else {
			shortestDistance = runDijkstra(graph, nodes[0], nodes[1]);
		}
		
		if (shortestDistance == Integer.MAX_VALUE) {
			return App.getProperty("no_route");
		}
		return String.valueOf(shortestDistance);
	}
}
