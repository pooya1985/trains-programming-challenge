package com.tw;

public class ShortestPathAction extends Action {

	public String execute(Graph graph) {
		String[] parameters = getParameters();

		// second parameter contains the nodes specification
		if (parameters.length < 2) {
			throw new IllegalArgumentException(App.getProperty("invalid_action") + ": " + toString() + 
					App.getProperty("missing_nodes_specification"));
		}
		
		String nodesSpec = parameters[1];
		Node[] nodes     = getNodes(graph, nodesSpec);
	
		if (nodes.length != 2) {
			throw new IllegalArgumentException(App.getProperty("invalid_action") + ": " + toString() +
					App.getProperty("must_specify_source_and_target"));
		}
		
		Node start = nodes[0];
		Node target = nodes[1];
		
		int shortestDistance = Integer.MAX_VALUE;
		
		// Why use Dijkstra?
		// I *could* just as well reuse the PathFinder class for this action, and
		// after finding all possible paths between the start and target nodes, simply
		// iterate thru the paths and select the smallest one. This however, implicates
		// selecting an arbitrary maximum number of hops, after which the search for the
		// shortest path should be given up. Furthermore it is highly inefficient. So I
		// decided to implement the search for the shortest path using the well known
		// Dijkstra algorithm

		if (start.isEqual(target)) {
			// This is a bit of a special case. The algorithm finds the
			// shortest path between two nodes. When the start and end nodes are
			// the same, the distance will of course be 0. The problem requests
			// (implicitly) that in this case a different path be found instead
			// of just returning the obvious answer (no need to travel).
			for (Edge edge : start.getNeighbours()) {
				int distance = Dijkstra.run(graph, edge.getDestination(), target);
				if (distance != Integer.MAX_VALUE) {
					// a path has been found from a neighbour of the start node
					// to the start node. Total distance will of course include
					// the distance from the start node to this neighbour.
					distance += edge.getDistance();
					if (distance < shortestDistance) {
						// a new shortest distance has been found
						shortestDistance = distance;
					}
				}
			}
		} else {
			shortestDistance = Dijkstra.run(graph, nodes[0], nodes[1]);
		}
		
		if (shortestDistance == Integer.MAX_VALUE) {
			return App.getProperty("no_route");
		}
		return String.valueOf(shortestDistance);
	}
}
