package utilGraph;

import java.util.*;

/**
 * A class for abstract graph. An abstract graph is the common 
 * implementation between directed graph (DiGraph) and undirected
 * graph (UnDiGraph)
 */
public abstract class AbstractGraph implements Graph {

	// the adjacency list
	protected Map<Vertex,List<Vertex>> adjacencyList;
	// the number of edges
	protected int nbEdges;
	// the set of all tags
	private Map<String,Vertex> tags;
	// the set of edges
	protected Map<Vertex,Map<Vertex,Edge>> edges;

	/**
	 * Builds an abstract graph with no vertices
	 */
	public AbstractGraph() {
		adjacencyList = new HashMap<Vertex,List<Vertex>>();
		tags = new HashMap<String,Vertex>();
		edges = new HashMap<Vertex,Map<Vertex,Edge>>();
		nbEdges = 0;
	}

	/////////////// public methods ///////////////
	
	/**
	 * Returns the number of vertices
	 */
	public int nbVertices() {
		return adjacencyList.size();
	}

	/**
	 * Returns the number of edges
	 */
	public int nbEdges() {
		return nbEdges;
	}
	
	/**
	 * Add a new vertex of tag 'tag' to the graph
	 * and returns that vertex
	 * If 'tag' is already used in that graph, the
	 * method raises a DuplicateTagException exception
	 */
	public Vertex addVertex(String tag) {
		if ( tags.containsKey(tag) )
			throw new DuplicateTagException(tag);
		InnerVertex v = new InnerVertex(this,tag);
		tags.put(tag, v);
		adjacencyList.put(v,new LinkedList<Vertex>());
		return v;
	}
	
	/**
	 * Return the vertex of tag 'tag' from the graph
	 * If no vertex has tag 'tag', the method
	 * returns null
	 */
	public Vertex getVertex(String tag) {
		if ( ! tags.containsKey(tag) )
			return null;
		return tags.get(tag);
	}
	
	
	/**	
	 * Returns a random vertex from the graph
	 */
	public Vertex getRandomVertex() {
		return adjacencyList.keySet().iterator().next();
	}
	
	/**
	 * Adds the new edge ('u','v') to the graph unless
	 * this edge is already present in the graph
	 */
	public void addEdge(Vertex u, Vertex v) {
		checkVertex(u);
		checkVertex(v);
		addEdge(u,v,0.0);
	}

	/**
	 * Removes edge 'e' from the graph unless
	 * this edge is not present in the graph
	 */
	public void removeEdge(Edge e) {
		removeEdge(e.origin(), e.destination());
	}
	
	/**
	 * Returns an iterable object over the vertices
	 * of the graph. The vertices come in random
	 * order
	 */
	public Iterable<Vertex> vertices() {
		return adjacencyList.keySet();
	}
	
	/**
	 * Returns an iterable object over the edges
	 * of the graph. The edges come in random
	 * order
	 */		
	public Iterable<Edge> edges() {
		return new EdgeIterator();
	}

	/**
	 * Returns an iterable object over the adjacent
	 * vertices of vertex 'u' in the graph. The adjacents
	 * come in random order
	 */
	public Iterable<Vertex> adjacents(Vertex u) {
		checkVertex(u);
		return adjacencyList.get(u);
	}

	/**
	 * Returns true if 'v' is adjacent to 'u' in
	 * the graph, false otherwise
	 */
	public boolean adjacents(Vertex u, Vertex v) {
		checkVertex(u);
		checkVertex(v);		
		for ( Vertex ve : adjacencyList.get(u) )
			if ( ve == v )
				return true;
		return false;
	}

	/**
	 * Returns an iterable object over the incident
	 * edges of vertex 'u' in the graph. The incident
	 * edges come in random order. For all incident
	 * edge e, e.origin() = u
	 */
	public Iterable<Edge> incidents(Vertex u) {
		checkVertex(u);
		return new IncidentEdgeIterator(u);
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "number of verticies: " + nbVertices();
		s += "\nnumber of edges: " + nbEdges;
		s += "\nvertices:";
		for ( Vertex u : vertices() )
			s += " " + u;
		s += "\nedges :";
		for ( Edge e : edges() )
			s += " " + e;
		return s;
	}
	
	/////////////// public abstract methods ///////////////

	/**
	 * Returns the total degree of vertex 'u'
	 * Notice: for DiGraph, degree(v) = inDegree(v) + outDegree(v)
	 */
	public abstract int degree(Vertex u);

	/**
	 * Adds the new edge ('u','v') with weight 'weight'
	 * to the graph unless this edge is already present
	 * in the graph
	 */
	public abstract void addEdge(Vertex u, Vertex v, double weight);

	/**
	 * Removes edge 'e' from the graph unless
	 * this edge is not present in the graph
	 */
	public abstract void removeEdge(Vertex u, Vertex v);
	
	/////////////// protected abstract methods ///////////////
	
	protected abstract Edge findEdge(Vertex u, Vertex v);
	
	/////////////// protected methods ///////////////
	
	protected boolean add(Vertex u, Vertex v, double weight) {
		if ( adjacencyList.get(u).contains(v) )
			return false;
		adjacencyList.get(u).add(v);
		return true;
	}
	
	protected boolean remove(Vertex u, Vertex v) {
		if ( adjacencyList.get(u).contains(v) )
			return false;
		adjacencyList.get(u).remove(v);
		return false;
	}
	
	protected void checkVertex(Vertex v) {
		if ( ((InnerVertex) v).fromGraph != this )
			throw new BadVertexException(v.getTag());
	}
	
	protected void storeEdge(Vertex u , Vertex v, double weight) {
		if ( edges.get(u) == null )
			edges.put(u, new HashMap<Vertex,Edge>());
		edges.get(u).put(v,new InnerEdge(u,v,weight));
	}
	
	/////////////// private class and methods ///////////////

	// A class for vertices
	private class InnerVertex implements Vertex {

		AbstractGraph fromGraph; // back link to the host graph
		String tag; // the tag of the vertex
		double weight; // the weight of the vertex (mainly for the Dijkstra algorithm)

		// Builds a vertex of tag 'tag' and linked to the
		// graph 'fromGraph'
		InnerVertex(AbstractGraph fromGraph, String tag) {
			this.tag = tag;
			this.fromGraph = fromGraph;
		}		

		/**
		 * Returns the tag of the vertex
		 */		
		public String getTag() {
			return tag;
		}
		
		/**
		 * Returns the weight of the vertex
		 */	
		public double getWeight() {
			return weight;
		}

		/**
		 * Sets the weight of the vertex to 'weight'
		 */			
		public void setWeight(double weight) {
			this.weight = weight;
		}

		/**
		 * Vertices can have a weight and be comparable
		 * on that weight (mainly for the Dijkstra algorithm)
		 */
		public int compareTo(Vertex v) {
			InnerVertex vv = (InnerVertex) v;
			if ( weight < vv.weight )
				return -1;
			if ( weight > vv.weight )
				return 1;
			return 0;
		}

		@Override
		public String toString() {
			return tag;
		}
	}

	// a class for edges
	private class InnerEdge implements Edge {

		Vertex x; // the origin of the edge
		Vertex y; // the destination of the edge
		double weight; // the weight of the edge

		/**
		 * builds the edge (x,y,weight)
		 */
		InnerEdge(Vertex x, Vertex y, double weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		/**
		 * Returns the origin of the edge
		 */
		public Vertex origin() {
			return x;
		}

		/**
		 * Returns the destination of the edge
		 */
		public Vertex destination() {
			return y;
		}

		/**
		 * Returns the weight of the edge
		 */		
		public double weight() {
			return weight;
		}

		/**
		 * edges can have a weight and be compared on that weight
		 * (mainly for Prim and Kruskal algorithms)
		 */
		public int compareTo(Edge e) {
			InnerEdge ee = (InnerEdge) e;
			if ( weight < ee.weight )
				return -1;
			if ( weight > ee.weight )
				return 1;
			return 0;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		@Override
		public boolean equals(Object o) {
			InnerEdge e = (InnerEdge) o;
			return x == e.x && y == e.y;
		}
	}
	
	// an inner class to build iterators over all edges
	private class EdgeIterator implements Iterable<Edge>, Iterator<Edge> {
		
		Iterator<Map.Entry<Vertex,Map<Vertex,Edge>>> firstMapIterator;
		Iterator<Map.Entry<Vertex,Edge>> secondMapIterator;
		
		EdgeIterator() {
			firstMapIterator = edges.entrySet().iterator();
			if ( firstMapIterator.hasNext() ) {
				secondMapIterator = firstMapIterator.next().getValue().entrySet().iterator();
			}
				
		}
		
		public Iterator<Edge> iterator() {
			return this;
		}

		public boolean hasNext() {
			return secondMapIterator.hasNext() || firstMapIterator.hasNext();
		}
		
		public Edge next() {
			if ( ! secondMapIterator.hasNext() )
				secondMapIterator = firstMapIterator.next().getValue().entrySet().iterator();
			return secondMapIterator.next().getValue();
		}		
	}

	// an inner class to iterate over the incident edges
	private class IncidentEdgeIterator implements Iterable<Edge>, Iterator<Edge> {

		Vertex origin;
		Iterator<Vertex> adjacents;

		IncidentEdgeIterator(Vertex u) {
			origin = u;
			adjacents = adjacencyList.get(u).iterator();
		}

		public Iterator<Edge> iterator() {
			return this;
		}

		public boolean hasNext() {
			return adjacents.hasNext();
		}

		public Edge next() {
			return findEdge(origin,adjacents.next());
		}

		public void remove() {
			throw new UnsupportedOperationException(); 
		}		
	}
}
