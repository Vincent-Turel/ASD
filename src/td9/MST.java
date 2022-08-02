package td9;

import java.util.*;

import td5.*;
import utilGraph.Edge;
import utilGraph.GraphReader;
import utilGraph.UnDiGraph;
import utilGraph.Vertex;

/**
 * A class for Minimum Spanning Tree algorithms (Prim ad Kruskal)
 */
public class MST {

	/**
	 * Returns the list of edges of an MST of weighted undirected graph G
	 * * using the Prim algorithm
	 * Precondition: G is connected
	 */
	public static List<Edge> prim(UnDiGraph G) throws FullHeapException, EmptyHeapException {
		// the edges of the MST
		List<Edge> mst = new LinkedList<>();
		// to make a minimum-heap of weighted edges
		Comparator<Edge> c = new Comparator<>() {
			public int compare(Edge e1, Edge e2) {
				return e2.compareTo(e1);
			}
		};
		// the minimum-heap of weighted edges
		BinaryHeap<Edge> minHeap = new BinaryHeap<>(G.nbEdges(),c);
		// known.contains(u) == true <==> u is known
		Set<Vertex> known = new HashSet<>();
		// we start from a random vertex u
		Vertex u = G.getRandomVertex();
		known.add(u);
		// adding the edges incident to u in the heap
		for ( Edge e : G.incidents(u) )
			minHeap.add(e);
		// while not all vertices are known (i.e. connected)
		while ( known.size() < G.nbVertices() ) {
			Edge min = minHeap.deleteExtreme();
			Vertex x = notKnown(min,known);
			if ( x != null ) {
				mst.add(min);
				known.add(x);
				for ( Edge e : G.incidents(x) ) {
					Vertex y = notKnown(e,known);
					if ( y != null )
						minHeap.add(e);
				}
			}
		}
		return mst;
	}

	/**
	 * Given the edge 'e' = (u,v) and the current set of known vertices
	 * returns the vertex (u or v) which is not in 'know', or null if they
	 * are both in 'known'
	 * Precondition: if 'e' = (u,v), at least one of u and v is in 'known'
	 */
	private static Vertex notKnown(Edge e, Set<Vertex> known) {
		Vertex v = null;
		if ( ! known.contains(e.origin()) )
			v = e.origin();
		else if ( ! known.contains(e.destination()))
			v = e.destination();
		return v;
	}

	/**
	 * Returns the list of edges of an MST of weighted undirected graph G
	 * using the Kruskal algorithm
	 * Precondition: G is connected
	 */
	public static List<Edge> kruskal(UnDiGraph G) throws FullHeapException, EmptyHeapException {
		// the edges of the MST
		List<Edge> mst = new LinkedList<>();
		// to make a minimum-heap of weighted edges
		Comparator<Edge> c = new Comparator<>() {
			public int compare(Edge e1, Edge e2) {
				return e2.compareTo(e1);
			}
		};
		// a minimum-heap
		BinaryHeap<Edge> minHeap = new BinaryHeap<>(G.nbEdges(),c);
		// fill the minimum-heap with all the weighted edges from the graph G
		for ( Edge e : G.edges() )
			minHeap.add(e);
		// disjoint sets of all the vertices of the graph G
		Partition<Vertex> P = new Partition<>();
		// add each vertex as a new tree in the partition
		for ( Vertex v : G.vertices() )
			P.newTree(v);
		// while not all the vertices are connected
		while ( P.nbTrees() > 1 ) {
			Edge min = minHeap.deleteExtreme();
			Vertex u = min.origin();
			Vertex v = min.destination();
			Partition.Tree ru = P.find(u);
			Partition.Tree rv = P.find(v);
			if ( ru != rv ) {
				mst.add(min);
				P.union(ru, rv);
			}
		}
		return mst;
	}

	public static void main(String[] s) throws FullHeapException, EmptyHeapException {
		UnDiGraph G = GraphReader.unDiGraph("0 2 4.0 0 5 8.0 1 4 2.0 1 5 7.0 1 7 6.0 2 4 8.0 2 5 11.0 3 4 4.0 3 6 10.0 3 7 2.0 3 8 14.0 4 8 7.0 5 7 1.0 6 8 9.0");
		System.out.println(G);
		System.out.println(prim(G));
		// System.out.println(kruskal(G));
	}
}
