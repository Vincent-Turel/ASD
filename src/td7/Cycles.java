package td7;

import java.util.*;
import utilGraph.*;

/**
 * A class to find cycles in undirected and directed graphs
 */
public class Cycles {
	
	private static Graph G;
	
	/////////////// Cycle detection for undirected graphs
	
	private static Set<Vertex> visited;

	public static boolean plusMoins(UnDiGraph G) {
		Iterable<Vertex> verticies = G.vertices();
		Map<Vertex, Integer> vertexValue = new HashMap<>();
		for (Vertex verticy : verticies) {
			if (!vertexValue.containsKey(verticy) && !plusMoins(G, verticy, 1, vertexValue)) {
				return false;
			}
		}
		return true;
	}

	private static boolean plusMoins(UnDiGraph G, Vertex u, int c, Map<Vertex,Integer> vertexValue) {
		if (vertexValue.containsKey(u)) {
			return vertexValue.get(u) == c;
		}
		vertexValue.put(u, c);
		for (Vertex vertex: G.adjacents(u)) {
			if (!plusMoins(G, vertex, -c, vertexValue)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns true if the graph 'G' is cyclic
	 * false otherwise
	 */
	public static boolean hasCycle(UnDiGraph Graph) {
		G = Graph;
		visited = new HashSet<>();
		Iterable<Vertex> verticies = G.vertices();
		boolean res = false;
		for (Vertex verticy : verticies) {
			visited.clear();
			res = res || hasCycle(verticy, null);
		}
		return res;
	}
	
	/**
	 * Returns true if a cycle was found by traversing
	 * the graph, coming from vertex from and going by vertex u
	 * Precondition: vertex 'from' is visited and vertex 'u' is
	 * not visited
	 */
	private static boolean hasCycle(Vertex u, Vertex from) {
		if (visited.contains(u)) {
			return true;
		}
		visited.add(u);
		for (Vertex verticy : G.adjacents(u)) {
			if (!verticy.equals(from)) {
				return hasCycle(verticy, u);
			}
		}
		return false;
	}
	
	/////////////// Cycle detection for directed graphs
	
	enum Status { UnDiscovered, InProgress, Completed } // status of vertex
	
	private static Map<Vertex,Status> vertexStatus; // to set the status of each vertex

	/**
	 * Returns true if the graph 'G' is cyclic
	 * false otherwise
	 */
	public static boolean hasCycle(DiGraph Graph) {
		G = Graph;
		vertexStatus = new HashMap<>();
		Iterable<Vertex> verticies = G.vertices();
		for (Vertex verticy : verticies) {
			vertexStatus.clear();
			setStatus(verticy, Status.UnDiscovered);
			if (hasCycle(verticy)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if a cycle was found by traversing
	 * the graph from vertex u
	 * Precondition: vertex 'u' is 'UnDiscovered'
	 */
	private static boolean hasCycle(Vertex u) {
		setStatus(u, (status(u) == Status.UnDiscovered ? Status.InProgress : Status.Completed));

		if (status(u) == Status.Completed) {
			return true;
		}

		for (Vertex verticy : G.adjacents(u)) {
			return hasCycle(verticy);
		}
		return false;
	}
	
	
	/**
	 * Returns the status of vertex 'u'
	 */
	private static Status status(Vertex u) {
		Status s = vertexStatus.get(u);
		if ( s == null )
			return Status.UnDiscovered;
		return s;
	}
	
	/**
	 * Sets the status of vertex 'u' to 's'
	 */	
	private static void setStatus(Vertex u, Status s) {
		vertexStatus.put(u,s);
	}
	
	/////////////// 
	
	public static void main(String[] s) {
		DiGraph G = GraphReader.D2;
		
		System.out.println(hasCycle(G));
	}
}
