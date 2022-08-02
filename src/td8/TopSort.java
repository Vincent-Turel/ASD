package td8;

import java.util.*;

import utilGraph.DiGraph;
import utilGraph.Vertex;

/**
 * A class for topological sorting
 */
public class TopSort {
	
	/**
	 * Returns the list of vertices of 'G' in
	 * (one) topological order
	 */
	public static List<Vertex> sort1(DiGraph G) {
		Map<Vertex,Integer> inDegree = new HashMap<>();
		Queue<Vertex> queue = new LinkedList<>();
		for (Vertex vertex: G.vertices()) {
			inDegree.put(vertex, G.inDegree(vertex));
		}
		while (queue.size() < G.nbVertices()) {
			Vertex v = null;
			for (Map.Entry<Vertex, Integer> entry : inDegree.entrySet()) {
				if (entry.getValue() == 0) {
					v = entry.getKey();
					inDegree.remove(entry.getKey(), 0);
					break;
				}
			}
			queue.add(v);
			for (Vertex vertex : G.adjacents(v)) {
				inDegree.replace(vertex, inDegree.get(vertex),  inDegree.get(vertex) - 1);
			}
		}
		return new LinkedList<>(queue);
	}
	
	/**
	 * Returns the list of vertices of 'G' in
	 * (one) topological order
	 */
	public static List<Vertex> sort2(DiGraph G) {
		Set<Vertex> visited = new HashSet<>();
		List<Vertex> sorted = new LinkedList<>();
		for (Vertex vertex : G.vertices()) {
			if (G.inDegree(vertex) == 0) {
				visit(G, vertex ,visited, sorted);
				break;
			}
		}
		return sorted;
	}
	
	/**
	 * Visit the graph 'G' using DFS from vertex 'u' and add all the visited
	 * vertices in 'sorted' such that they appear in topological order
	 */
	private static void visit(DiGraph G, Vertex u, Set<Vertex> visited, List<Vertex> sorted) {
		visited.add(u);
		for (Vertex vertex : G.adjacents(u)) {
			if (!visited.contains(vertex)) {
				visit(G, vertex, visited, sorted);
			}
		}
		sorted.add(0, u);
	}

	/////////////// 
	
	public static void main(String[] s) {
		DiGraph G = GraphReader.DD4;
		System.out.println(TopSort.sort2(G));
	}	
}
