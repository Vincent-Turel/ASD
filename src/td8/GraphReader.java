package td8;

import utilGraph.*;

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
	public static DiGraph DD1 = diGraph("A D C A C E D B E A E D");
	public static DiGraph DD2 = diGraph("A D A F C A C E D B E D F B");
	public static DiGraph DD3 = diGraph("A C A E B D B F D F D G E C");
	public static DiGraph DD4 = diGraph("A C A E B D C E C G D A F B F D");
	public static UnDiGraph C1 = unDiGraph("A C 1.0 A D 5.0 A E 2.0 B D 3.0 C E 6.0 D E 4.0");
	public static UnDiGraph C2 = unDiGraph("A D 8.0 A E 9.0 A F 2.0 B D 1.0 B E 5.0 C E 6.0 C F 7.0 D E 3.0 E F 4.0");
	public static UnDiGraph C3 = unDiGraph("A D 9.0 A F 2.0 A G 8.0 B D 4.0 B E 11.0 B G 10.0 C E 6.0 C F 7.0 C G 12.0 D G 3.0 E G 5.0 F G 1.0");
	public static UnDiGraph C4 = unDiGraph("A C 4.0 A F 8.0 B E 2.0 B F 7.0 B H 6.0 C E 8.0 C F 11.0 D E 4.0 D G 10.0 D H 2.0 D I 14.0 E I 7.0 F H 1.0 G I 9.0");
	public static UnDiGraph NC1 = unDiGraph("A C 1.0 A E 2.0 B D 4.0 C E 6.0");
	public static UnDiGraph NC2 = unDiGraph("A D 8.0 A E 9.0 B D 1.0 B E 5.0 C F 7.0 C H 6.0 D E 3.0 F G 2.0 F H 4.0 G H 5.0");
	public static UnDiGraph NC3 = unDiGraph("A D 9.0 A F 2.0 A G 8.0 B C 5.0 B E 11.0 C E 6.0 D G 3.0 F G 1.0");
	public static UnDiGraph NC4 = unDiGraph("A C 4.0 A F 8.0 B H 6.0 C F 11.0 D E 4.0 D G 10.0 D I 14.0 E I 7.0 G I 9.0");	
	
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
