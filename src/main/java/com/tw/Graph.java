package com.tw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Graph {
    
    Map<String, Node> nodeMap = new TreeMap<String, Node>();
    
    public Node[] getNodes() {
    	return nodeMap.values().toArray(new Node[0]);
    }
    
    /**
     * Looks up a given node in this graph by name. If it doesn't exist, a new
     * node is created and inserted in the graph.
     * @param name
     * @return
     */
    public Node getNode(String name) {
    	Node node = nodeMap.get(name);
    	if (node == null) {
    		node = new Node(name);
    		nodeMap.put(name, node);
    	}
    	return node;
    }
    
	/**
	 * reads a series of graph edges (i.e. connections between nodes) from
	 * a given string containing a comma or whitespace separated list of
	 * connections of the form XYZ, where X and Y correspond to a source
	 * and target nodes respectively and Z corresponds to the distance
	 * or weight between the two nodes. For example "AB3".
	 * 
	 * @param str
	 */
	public void read(String str) {
		for (String s : str.split("[\\s,]")) {
			if (!s.isEmpty()) {
				// An edge spec must be at least 3 characters.. i.e. 
				// AB3
				if (s.length() < 3) {
					throw new IllegalArgumentException("Bad graph specification:" + s);
				}
				String sourceNodeName = s.substring(0,1);
				String targetNodeName = s.substring(1,2);
				int distance = Integer.parseInt(s.substring(2));
				
				Node source = getNode(sourceNodeName);
				Node target = getNode(targetNodeName);
				
				source.addEdge(new Edge(target, distance));
			}
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Map.Entry<String, Node> entry : nodeMap.entrySet()) {
			s.append(entry.getKey() + ":\n");
			for (Edge edge : entry.getValue().neighbours) {
				s.append("  -> " + edge + "\n");
			}
		}
		return s.toString();
	}
	
	/**
	 * Constructs a Graph based on a specification read from an InputStream
	 * The specification is a comma (or whitespace) separated list of
	 * connections of the form XYZ, where X and Y correspond to a source
	 * and target nodes respectively and Z corresponds to the distance
	 * or weight between the two nodes. For example "AB3".
	 * @param stream
	 * @throws IOException
	 */
	public Graph(InputStream stream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String str;
		while((str = reader.readLine()) != null) {
			read(str);
		}
	}
}
