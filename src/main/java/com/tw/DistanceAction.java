package com.tw;

public class DistanceAction extends Action {
	public String execute(Graph graph) {
		// first parameter is the nodes specification
		String nodesSpec = getParameters()[1];
		Node[] nodes = getNodes(graph, nodesSpec);
				
		int distance = 0;
		
		// initial "last node" is the start node...
		Node lastNode = nodes[0];
		
		// iterate from the second node onwards...
		for (int i = 1; i < nodes.length; ++i) {
			Node node = nodes[i];
			boolean routeFound = false;
			// find out if there is indeed a path from
			// the previous node visited to this one...
			for (Edge neighbour: lastNode.getNeighbours()) {
				if (neighbour.getDestination().isEqual(node)) {
					//found a path
					distance += neighbour.getDistance();
					routeFound = true;
					break;
				}
			}
			if (!routeFound) {
				// route doesn't exist. Distance is infinity
				distance = Integer.MAX_VALUE;
				break;
			}
			lastNode = node;
		}
		
		if (distance == Integer.MAX_VALUE) {
			return App.getProperty("no_route");
		}
		return String.valueOf(distance);
	}
}
