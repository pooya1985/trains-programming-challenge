package com.tw;

public class Path {
	private int distance;
	private int hops;
	
	public int getDistance() {
		return distance;
	}
	public int getHops() {
		return hops;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public void setHops(int hops) {
		this.hops = hops;
	}
	public Path(int distance, int hops) {
		this.distance = distance;
		this.hops = hops;
	}
	public String toString() {
		return "(" + distance + ", " + hops +")";
	}
}
