package utilGraph;

/**
 * An interface for the vertices of a graph.
 */
public interface Vertex extends Comparable<Vertex> {
	
	/**
	 * Returns the tag of the vertex
	 */
	public String getTag();
	
	/**
	 * Returns the weight of the vertex
	 */	
	public double getWeight();
	
	/**
	 * Sets the weight of the vertex to 'weight'
	 */		
	public void setWeight(double weight);

}
