package com.tw;

public class Action {
	String [] parameters;

	public void setParameters(String[] parameters)  {
		this.parameters = parameters;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String s : parameters) {
			str.append(s + " ");
		}
		return str.toString();
	}
	
	public String execute(Graph g) {
		return "<not implemented>";
	}
}
