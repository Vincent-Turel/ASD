package td7;

import java.util.*;
import utilGraph.*;

/**
 * A class to find a path between two vertices in a graph (directed or undirected)
 */
public class PathFinder {
	
	private static Graph G;
	
	private static Set<Vertex> visited;
	
	/**
	 * Returns a path as from vertex 'u' to vertex 'v' in the graph 'G'
	 * as a list of vertices if such a path exists, the empty list otherwise
	 */
	public static List<Vertex> findPath(Graph G, Vertex u, Vertex v) {
		List<Vertex> path = new LinkedList<>();
		visited = new HashSet<>();
		PathFinder.G = G;
		findPath(u,v, path);
		return path;
	}
	
	/**
	 * If there is a path from vertex 'u' to vertex 'v' in the graph, the
	 * vertices of this path are stored in the list 'path' and the method
	 * returns true. Else the list 'path' remains unchanged and the method
	 * returns false
	 */
	private static boolean findPath(Vertex u, Vertex v, List<Vertex> path) {
		visited.add(u);
		path.add(u);

		if (u.getTag().equals(v.getTag())) {
			return true;
		}

		for (Vertex vertex : G.adjacents(u)) {
			if (!visited.contains(vertex)) {
				if (findPath(vertex, v, path)) {
					return true;
				}
			}
		}
		path.remove(u);

		return false;
	}
	
	public static void main(String[] s) {
		DiGraph G = GraphReader.D3;
		System.out.println(G);
		
		System.out.println(findPath(G,G.getVertex("F"),G.getVertex("E")));
	}
}
