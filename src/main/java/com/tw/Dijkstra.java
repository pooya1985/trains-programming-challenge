package com.tw;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dijkstra {
	
	/**
	 * Performs a complete run of the Dijkstra algorithm to calculate the shortest
	 * possible route between a start and target node
	 * @param graph
	 * @param start
	 * @param target
	 * @return
	 */
	public static int run(Graph graph, Node start, Node target) {
		Set<Node> unsettled = new HashSet<Node>();
		Set<Node> settled = new HashSet<Node>();
		
		Node[] nodes = graph.getNodes();
		Map<String, Integer> distance = new TreeMap<String, Integer>();

		// Start by setting the distances from all nodes to the start node to 
		// infinity
		for (Node node: nodes) {
			distance.put(node.getName(), Integer.MAX_VALUE);
		}
		
		// move start node to the list of unvisited nodes and set the 
		// distance to 0 (i.e. the distance from the start node to itself)
		unsettled.add(start);
		distance.put(start.getName(), 0);
		
		// while the list of unvisited nodes is not empty...
		while(!unsettled.isEmpty()) {
			
			// find the node in the unvisited list with the 
			// shortest known distance to the source so far (i.e. the 
			// "nearest" node
			Node nearest=null;
			int minDistance = Integer.MAX_VALUE;
			for(Node n : unsettled) {
				Integer dist = distance.get(n.getName());
				if ( dist < minDistance) {
					nearest = n;
					minDistance = dist ;
				}
			}
			
			// move the nearest node to the list of visited nodes
			unsettled.remove(nearest);
			settled.add(nearest);
			
			// for all neighbours of the current node...
			for (Edge edge : nearest.getNeighbours()) {
				// which haven't been visited...
				if (!settled.contains(edge.getDestination())) {
					// find the ones for which we find a better (shorter) path
					// to the source than the one we had previously calculated for it...
					int newDistance = distance.get(nearest.getName()) + edge.getDistance();
					Integer dist = distance.get(edge.getDestination().getName());
					if (newDistance < dist) {
						// save the new shortest distance for this neighbour
						// and move it to the list of unvisited nodes.
						distance.put(edge.getDestination().getName(), newDistance);
						unsettled.add(edge.getDestination());
					}
				}
			}
			
			// if the target node has already been visited, we can stop searching
			if (settled.contains(target)) {
				break;
			}
		}
		// returns the shortest distance recorded from the target node to the start node
		return distance.get(target.getName());
	}
}
