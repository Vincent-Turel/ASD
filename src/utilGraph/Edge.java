package utilGraph;

/**
 * An interface for the edges of a graph.
 */
public interface Edge extends Comparable<Edge> {
		
	/**
	 * Returns the origin of the edge
	 */
	public Vertex origin();
	
	/**
	 * Returns the destination of the edge
	 */
	public Vertex destination();
	
	/**
	 * Returns the weight of the edge
	 */
	public double weight();
}
