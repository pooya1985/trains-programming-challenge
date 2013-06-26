package com.tw;

import java.util.ArrayList;

/**
 * A PathFinder object performs a tree search on a graph looking for all possible
 * paths that satisfy a given condition.
 * @author erick
 *
 */
public class PathFinder {
	private Condition runCond;
	private Condition filterCond;
	private boolean stopOnTarget;
	
	private ArrayList<Path> findPaths(Graph graph, Node current,  Node target, Path ancestor) {
		ArrayList<Path> paths = new ArrayList<Path>();

		// For all neighbours directly reachable from the current node...
		for (Edge edge: current.getNeighbours()) {
			Node destination = edge.getDestination();
			int distance = edge.getDistance();
			Path newPath = new Path(ancestor.getDistance() + distance, ancestor.getHops() + 1);
			
			// if the path reaching this neighbour means the running condition is no longer
			// satisfied, this search branch should be aborted
			if (!runCond.evaluate(newPath)) {
				continue;
			}
			
			// if this neighbour is the target we are looking for...
			if (destination.isEqual(target)) {
				// if no filter was specified *or* the filter selects this path,
				// then it is added to the current list of results
				 if (filterCond == null || filterCond.evaluate(newPath)) {
					paths.add(newPath);
				 }
				 
				 // this might look contradicting but it really means we
				 // are stopping the search on this branch of the tree and go on
				 // with other search branches
				 if (stopOnTarget) {
					 continue;
				 }
			}
			
			// Look recursively for all paths that can be found via this neighbour
			for (Path path: findPaths(graph, destination, target, newPath)) {
				paths.add(path);
			}
		}
		return paths;
	}

	/**
	 * Executes a tree search under the constraints specified in this PathFinder object
	 * @param graph
	 * @param start
	 * @param target
	 * @return
	 */
	public Path[] findAllPaths(Graph graph, 
			                   Node start, 
			                   Node target) {
		return findPaths(graph, start, target, new Path(0,0)).toArray(new Path[0]);
	}
	
	/**
	 * Construct a PathFinder object. The search will be directed according to the provided
	 * constraints. 
	 * @param runCond Determines under what circumstances the search should
	 * carry on.
	 * @param filterCond This filter specifies which paths should be included in the result.
	 * @param stopOnTarget Specifies if the search should stop once the target node has been reached.
	 */
	public PathFinder(Condition runCond, Condition filterCond, boolean stopOnTarget) {
		this.runCond = runCond;
		this.filterCond = filterCond;
		this.stopOnTarget = stopOnTarget;
	}
}
