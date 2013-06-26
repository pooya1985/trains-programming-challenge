package com.tw;

import java.util.ArrayList;
import java.util.List;

public class Node {
	List<Edge> neighbours;
	String name;
	
	String getName() { 
		return name;
	}
	
	List<Edge> getNeighbours() {
		return neighbours;
	}
	
	public Node(String name) {
		neighbours = new ArrayList<Edge>();
		this.name = name;
	}
	
	public boolean isEqual(Node n) {
		return n.name == this.name;
	}
	
	public void addEdge(Edge edge) {
		neighbours.add(edge);
	}
}
