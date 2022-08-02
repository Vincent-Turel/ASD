package utilGraph;

import java.util.*;

/**
 * This is a convenience class to build a graph from an input String.
 * 
 * Non weighted graphs are given as a sequence of pairs of vertex tags like "A B A C ..."
 * which will add the vertices A, B, C, etc. and the edge (A,B), (A,C), etc. to the graph.
 * 
 * Weighted graphs are given as sequence of three items, two vertices followed by a weight
 * (a double value) like "A B 2.5 A C 5.2 ..." which will add vertices A, B, C, etc. and the
 * weighted edges (A,B,2.5), (A,C,5.2), etc. to the graph.
 * 
 * Additionally the class has the graphs mentioned in the labs already defined as public 
 * static items which can be used in lab code, like GraphReader.U1, GraphReader.D1, etc.
 * 
 */
public class GraphReader {
	
	public static UnDiGraph U1 = unDiGraph("A B C D C E D E E F G H G K H I H J H K I J");
	public static UnDiGraph U2 = unDiGraph("A D A E A J B C B F B G B I C F C G C H D E D F G H");
	public static UnDiGraph U3 = unDiGraph("A E B D B F B H C G G I G J");
	public static UnDiGraph U4 = unDiGraph("A C A D B E B K C E C J D F D H E G E I");
	public static DiGraph D1 = diGraph("A E B D B F C E D F F C F E G A G B G C");
	public static DiGraph D2 = diGraph("A C A E B D D F D G E C F B");
	public static DiGraph D3 = diGraph("A C B D C E C G D A D F E A F B");
	
	/**
	 * Returns an UnDiGraph build from the String
	 * representation 'input'
	 */
	public static UnDiGraph unDiGraph(String input) {
		UnDiGraph G = new UnDiGraph();
		readGraph(G,input);
		return G;
	}
	
	/**
	 * Returns an DiGraph build from the String
	 * representation 'input'
	 */	
	public static DiGraph diGraph(String input) {
		DiGraph G = new DiGraph();
		readGraph(G,input);
		return G;
	}
	
	private static void readGraph(Graph G, String input) {
		Scanner scanner = new Scanner(input);
		if ( weighted(input) )
			readWeightedGraph(G,scanner);
		else
			readGraph(G,scanner);
		scanner.close();
	}
	
	@SuppressWarnings("resource")
	private static boolean weighted(String input) {
		Scanner scanner = new Scanner(input);
		if ( scanner.hasNext() )
			scanner.next();
		else
			throw new BadInputGraphException(input);
		if ( scanner.hasNext() )
			scanner.next();
		else
			throw new BadInputGraphException(input);
		if ( scanner.hasNextDouble() )
			return true;
		return false;
	}
	
	private static void readWeightedGraph(Graph G, Scanner input) {
		String u, v;
		double w;
		while ( input.hasNext() ) {
			u = input.next();
			if ( input.hasNext() )
				v = input.next();	
			else
				throw new BadInputGraphException("");
			if ( input.hasNextDouble() )
				w = input.nextDouble();
			else
				throw new BadInputGraphException("");
			Vertex uu = addVertex(G,u);
			Vertex vv = addVertex(G,v);
			G.addEdge(uu, vv, w);
		}
	}
	
	private static void readGraph(Graph G, Scanner input) {
		String u, v;
		while ( input.hasNext() ) {
			u = input.next();
			if ( input.hasNext() )
				v = input.next();	
			else
				throw new BadInputGraphException("");
			Vertex uu = addVertex(G,u);
			Vertex vv = addVertex(G,v);
			G.addEdge(uu, vv);
		}
	}
	
	private static Vertex addVertex(Graph G, String u) {
		Vertex v = G.getVertex(u);
		if ( v == null )
			return G.addVertex(u);
		return v;
	}
}
