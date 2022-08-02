package td9;

import java.util.*;

import td8.GraphReader;
import utilGraph.Edge;
import td5.*;
import utilGraph.UnDiGraph;
import utilGraph.Vertex;

/**
 * A class for Prim Minimum Spanning Forest algorithm
 */
public class MSF {

    /**
     * Returns the list of edges of an MSF of weighted undirected graph G
     * using the Prim algorithm
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
        // we go through all unknown vertices
        for ( Vertex u : G.vertices() ) {
            if ( ! known.contains(u) ) {
                known.add(u);
                // adding the edges incident to u in the heap
                for ( Edge e : G.incidents(u) )
                    minHeap.add(e);
                // while there are remaining vertices in the heap
                while ( ! minHeap.isEmpty() ) {
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

    ////////////////////

    public static void main(String[] s) throws FullHeapException, EmptyHeapException {
        UnDiGraph G = GraphReader.NC4;
        System.out.println(G);
        System.out.println(prim(G));
    }

}
