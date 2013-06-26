package com.tw;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dijkstra {
	public static int run(Graph graph, Node start, Node target) {
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
}
