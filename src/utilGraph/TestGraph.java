package utilGraph;

/**
 * A class to demonstrate the use of the graph methods
 */
public class TestGraph {
	
	/**
	 * Displays all information about the graph 'G'
	 * (directed or undirected) named 'name'
	 */
	private static void testGraphMethods(String name, Graph G) {
		System.out.println(name);
		System.out.println(G);
		
		System.out.println("\nVertices enumeration:");
		for ( Vertex vertex :  G.vertices() )
			System.out.print(vertex.getTag() + " ");
		System.out.println();
		
		System.out.println("\nEdges enumeration:");
		for ( Edge edge :  G.edges() )
			System.out.print("(" + edge.origin() + "," + edge.destination() + ") ");
		System.out.println();
		
		System.out.println("\nAdjacents enumeration:");
		for ( Vertex vertex :  G.vertices() ) {
			System.out.print("Adjacents of vertex " + vertex + ": ");
			for ( Vertex adjacent : G.adjacents(vertex) )
				System.out.print(adjacent + " ");
			System.out.println();
		}	
		
		System.out.println("\nIncidents enumeration:");
		for ( Vertex vertex :  G.vertices() ) {
			System.out.println("Incident edges of vertex " + vertex + ":");
			for ( Edge edge : G.incidents(vertex) )
				System.out.println("   " + edge + ", origin = " + edge.origin() + ", destination = " + edge.destination());
			System.out.println();
		}
		
		System.out.println("\nIn-degree of vertices:");
		for ( Vertex vertex :  G.vertices() )
			System.out.println("in-degree(" + vertex + ") = " + G.inDegree(vertex));
		System.out.println();		
		
		System.out.println("\nOut-degree of vertices:");
		for ( Vertex vertex :  G.vertices() )
			System.out.println("out-degree(" + vertex + ") = " + G.outDegree(vertex));
		System.out.println();
		
		System.out.println("\n(total) degree of vertices:");
		for ( Vertex vertex :  G.vertices() )
			System.out.println("degree(" + vertex + ") = " + G.degree(vertex));
		System.out.println();
		
	}

	/**
	 * You can display information about graph here
	 */
	public static void main(String[] args) {
		testGraphMethods("U1:",GraphReader.U1);
		System.out.println("-------------------------------\n");
		testGraphMethods("D1:",GraphReader.D1);
		//// you can test more graphs here
	}
}
