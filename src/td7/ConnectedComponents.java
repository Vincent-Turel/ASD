package td7;

import java.util.*;
import utilGraph.*;

/**
 * A class to find connected components from an undirected graphs
 */
public class ConnectedComponents {
	
	private static Map<Vertex,Integer> cc; // the resulting map
	private static UnDiGraph G; // the undirected graph

	/**
	 * Returns the map of the connected components of 'G'
	 * If cc is the returned map, then, for all verticies u and v,
	 * cc.get(u) = cc.get(v) if and only if u and v are in the same
	 * connected component
	 */
	public static Map<Vertex,Integer> find(UnDiGraph Graph) {
		cc = new HashMap<>();
		G = Graph;
		int count = 1;
		Iterable<Vertex> verticies = G.vertices();
		for (Vertex verticy : verticies) {
			if (notNumbered(verticy)) {
				setComponent(verticy, count);
				count++;
			}
		}
		return cc;
	}
	
	/**
	 * Set the component number to 'number' for 'u' and
	 * all the vertices reachable from u
	 */
	private static void setComponent(Vertex u, int number) {
		if (notNumbered(u)) {
			setNumber(u, number);
			Iterable<Vertex> verticies = G.adjacents(u);
			for (Vertex verticy : verticies) {
				setComponent(verticy, number);
			}
		}
	}
	
	private static boolean notNumbered(Vertex u) {
		return ! cc.containsKey(u);
	}
	
	private static void setNumber(Vertex u, int number) {
		cc.put(u, number);
	}
	
	public static void main(String[] s) {
		
		Map<Vertex,Integer> cc = find(GraphReader.U1);
		System.out.println(cc);
		
		cc = find(GraphReader.U2);
		System.out.println(cc);
	}
}
