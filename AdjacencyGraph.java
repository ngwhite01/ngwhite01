package cs2321;

/**
 * Nathan White
 * ngwhite
 * this program implements a graph interface.
 */
import net.datastructures.*;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author CS2321 Instructor
 */
public class AdjacencyGraph<V, E> implements Graph<V, E> {
	private final boolean isDirected; //boolean for the graph being directed
	/**
	 * this class creates a vertex object and all the related methods and values associated with it
	 * @author Nathan White
	 *
	 * @param <V>
	 * @param <E>
	 */
	private static class myVertex<V,E> implements Vertex<V> {
		private V element; //variable to hold the value
		private Position<Vertex<V>> position; //variable for position
		private final HashMap<Vertex<V>,Edge<E>> outgoing; //hashmap for outgoing
		private  final HashMap<Vertex<V>,Edge<E>> incoming; //hashmap for incoming
		/**
		 * constructor for the vertex object
		 * @param e
		 * @param isDirected
		 */
		public myVertex(V e, boolean isDirected) {
			this.element=e;
			outgoing= new HashMap<>();
			if(isDirected) {
				incoming = new HashMap<>();
			}
			else {
				incoming=outgoing;
			}
		}
		@Override
		/**
		 * this method returns the element
		 */
		public V getElement() {
			
			return element;
		}
		/**
		 * this method sets the position to the inputed value
		 * @param p
		 */
		public void setPosition(Position<Vertex<V>> p) {
			this.position=p;
		}
		/**
		 * this method returns the value of the position variable
		 * @return
		 */
		public Position<Vertex<V>> getPosition(){
			return position;
		}
		/**
		 * outputs the outgoing hashmap
		 * @return
		 */
		public HashMap<Vertex<V>, Edge<E>> getOutgoing(){
			return outgoing;
		}
		/**
		 * outputs the ingoing hashmap
		 * @return
		 */
		public HashMap<Vertex<V>, Edge<E>> getIncoming(){
			return incoming;
		}
		/**
		 * sets the element variable equal to the value input in 
		 * @param e
		 */
		public void setElement(V e) {
			this.element=e;
		}
	}
	/**
	 * this class creates an edge object and all the related methods and values associated with it 
	 * @author Nathan White
	 *
	 * @param <V>
	 * @param <E>
	 */
	private static class myEdge<V,E> implements Edge<E> {
		private E element; //variable to hold the element
		private Position<Edge<E>> position; //variable to hold the position
		private final Vertex<V>[] endpoints; //variable to hold an array of endpoints
		/**
		 * this is a constructor for the edge class
		 * @param u
		 * @param v
		 * @param e
		 */
		public myEdge(Vertex<V> u, Vertex<V> v, E e) { 
			this.element=e;
			endpoints= new Vertex[] {u,v};
		}
		@Override
		/**
		 * this method returns the element variable
		 */
		public E getElement() {
			
			return element;
		}
		/**
		 * returns the endpoints variable
		 * @return
		 */
		public Vertex<V>[] getEndpoints(){
			return endpoints;
		}
		/**
		 * this method sets the position variable equal to the inputed value
		 * @param p
		 */
		public void setPosition(Position<Edge<E>> p) {
			this.position=p;
		}
		/**
		 * this method returns the position variable 
		 * @return
		 */
		public Position<Edge<E>> getPosition(){
			return position;
		}
		/**
		 * this method sets the element variable equal to the inputed value
		 * @param e
		 */
		public void setElement(E e) {
			this.element=e;
		}
	}
	/**
	 * this is a constructor to create an adjacency graph
	 * @param directed
	 */
	public AdjacencyGraph(boolean directed) {
		isDirected=directed;
		edges=new DoublyLinkedList<>();
		vertices=new DoublyLinkedList<>();
	}
	/**
	 * this is a basic constructor if nothing is passed
	 */
	public AdjacencyGraph() { 
		this(false);
	}
	private final PositionalList <Vertex<V>> vertices; //a list to hold the vertexes
	private final PositionalList <Edge<E>> edges; //a list to hold the edges
	
	/**
	 * this method validates whether an inputed edge is real or not. It checks if it is an edge data type and if the position is not null
	 * @param e
	 * @return
	 * @throws IllegalArgumentException
	 */
	private myEdge<V,E> validate(Edge<E> e) throws IllegalArgumentException { //method to validate the position of an input
		if (!(e instanceof myEdge)) throw new IllegalArgumentException("Invalid edge"); //checks if the input is a node
		myEdge<V,E> edge = (myEdge<V,E>) e; //creates a node to hold the value
		if(edge.getPosition() == null) { //if there isn't a next value 
			throw new IllegalArgumentException("e is no longer in the list"); //throws an exception
		}
		return edge; //returns the new node
	}
	/**
	 * this method validates whether an inputed vertex is real or not. It checks if it is a vertex data type and if the position is not null
	 * @param e
	 * @return
	 * @throws IllegalArgumentException
	 */
	private myVertex<V,E> validate(Vertex<V> v) throws IllegalArgumentException { //method to validate the position of an input
		if (!(v instanceof myVertex)) throw new IllegalArgumentException("Invalid vertex"); //checks if the input is a node
		myVertex<V,E> vertex = (myVertex<V,E>) v; //creates a node to hold the value
		if(vertex.getPosition() == null) { //if there isn't a next value 
			throw new IllegalArgumentException("v is no longer in the list"); //throws an exception
		}
		return vertex; //returns the new node
	}
	/* (non-Javadoc)
	 * @see net.datastructures.Graph#edges()
	 */
	/**
	 * returns the list of edges to iterate through
	 */

	/**
	 * this method returns the edges doubly linked list so it takes O(1) time
	 */
	public Iterable<Edge<E>> edges() {
		return edges;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
	 */
	/**
	 * this method returns the end points of the edge inputed
	 */

	/**
	 * this method takes O(1) time since it only has to validate the edge inputed and then return it's end points
	 */
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		myEdge<V,E> edge = validate(e);
		return edge.getEndpoints();
	}
	
	
	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
	 */
	/**
	 * this method inserts an edge into the edges linked list unless it already exists in which it returns an exception
	 */

	/**
	 * this method takes O(1) time since it doesn't have to iterate through anything at all and simply inserts at the end
	 */
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
		if(getEdge(u,v)==null) {
			myEdge<V,E> edge = new myEdge<>(u,v,o);
			edge.setPosition(edges.addLast(edge));
			myVertex<V,E> origin = validate(u);
			myVertex<V,E> dest = validate(v);
			origin.getOutgoing().put(v, edge);
			dest.getIncoming().put(u, edge);
			return edge;
		}else {
			throw new IllegalArgumentException("Edge from u to v exists");
		}
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertVertex(java.lang.Object)
	 */
	/**
	 * this method inserts a new vertex based on the value inputed
	 */

	/**
	 * this method takes O(1) time since it only has to create a new vertex object and then add it into the vertices linked list
	 */
	public Vertex<V> insertVertex(V o) {
		myVertex<V,E> vertex = new myVertex<>(o,isDirected);
		vertex.setPosition(vertices.addLast(vertex));
		return vertex;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numEdges()
	 */
	/**
	 * this method returns the size of the edges linked list
	 */
	
	/**
	 * this method only has to return a linked list so it takes O(1) time
	 */
	public int numEdges() {
		return edges.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numVertices()
	 */
	/**
	 * this method returns the size of the vertices linked list
	 */

	/**
	 * this method only has to return a linked list so it takes O(1) time
	 */
	public int numVertices() {
		return vertices.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
	 */
	/**
	 * this method returns one the opposite end point of the inputed edge based on whether or not the inputed vertex it equal to it.
	 */

	/**
	 * this method only validates the inputed value and then determines if it is equal to another input so it never has to iterate through the linked lists so it takes O(1)
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
		myEdge<V,E> edge = validate(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		if(endpoints[0].equals(v)) {
			return endpoints[1];
		}else if(endpoints[1].equals(v)) {
			return endpoints[0];
		}else {
			throw new IllegalArgumentException("v is not incident to this edge");
		}
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
	 */
	/**
	 * this method removes the inputed edge value
	 */

	/**
	 * this method only validates the edge inputed and then removes both end points related to it as well as the position so it only takes O(1) time
	 */
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		myEdge<V,E> edge = validate(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		((myVertex<V,E>) endpoints[0]).getOutgoing().remove(endpoints[1]);
		((myVertex<V,E>) endpoints[1]).getIncoming().remove(endpoints[0]);
		edges.remove(edge.getPosition());
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
	 */
	/**
	 * this method removes the vertex inputed after validating that it exists
	 */

	/**
	 * this method has to iterate through all the vertex incoming values in order to find the correct value to remove so it takes O(n)
	 */
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		myVertex<V,E> vertex = validate(v);
		for(Edge<E> e:vertex.getIncoming().values()) {
			removeEdge(e);
		}
		for(Edge<E> e:vertex.getOutgoing().values()) {
			removeEdge(e);
		}
		vertices.remove(vertex.getPosition());
		
	}

	/* 
     * replace the element in edge object, return the old element
     */
	/**
	 * this method returns the old value of the inputed edge while replacing its value with the inputed value 
	 */

	/**
	 * this method validates the edge inputed and and then sets the new element so it only takes O(1) time
	 */
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		myEdge<V,E> edge = validate(e);
		E old = edge.getElement();
		edge.setElement(o);
		return old;
	}

    /* 
     * replace the element in vertex object, return the old element
     */
	/**
	 * this method returns the old value of the inputed edge while replacing its value with the inputed value 
	 */
	
	/**
	 * this method validates the vertex inputed and and then sets the new element so it only takes O(1) time
	 */
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		myVertex<V,E> vertex = validate(v);
		V old = vertex.getElement();
		vertex.setElement(o);
		return old;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#vertices()
	 */
	/**
	 * this method returns the vertices linked list
	 */
	

	/**
	 * this method only takes O(1) time since it just returns a linked list
	 */
	public Iterable<Vertex<V>> vertices() {

		return vertices;
	}

	@Override
	/**
	 * this method outputs the size of the outgoing hashmap of the inputed vertex
	 */

	/**
	 * this method only takes O(1) time since it just has to output an integer after validating the input
	 **/
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		myVertex<V,E> vertex = validate(v);
		return vertex.getOutgoing().size();
	}

	@Override
	/**
	 * this method outputs the size of the incoming hashmap of the inputed vertex
	 */
	
	/**
	 * this method only takes O(1) time since it just has to output an integer after validating the input
	 **/
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		myVertex<V,E> vertex = validate(v);
		return vertex.getIncoming().size();
	}

	@Override
	/**
	 * this method outputs the size of the outgoing hashmap of the inputed edge
	 */
	
	/**
	 * this method only takes O(1) time since it just has to output an integer after validating the input
	 **/
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		myVertex<V,E> vertex = validate(v);
		return vertex.getOutgoing().values();
	}

	@Override
	/**
	 * this method outputs the size of the incoming hashmap of the inputed edge
	 */
	/**
	 * this method only takes O(1) time since it just has to output an integer after validating the input
	 **/
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		myVertex<V,E> vertex = validate(v);
		return vertex.getIncoming().values();
	}

	@Override
	/**
	 * this method returns the outgoing vertex at the input v after validating the input
	 */
	
	/**
	 * this method only takes O(1) time since it only validates the input and then returns a value
	 **/
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		myVertex<V,E> vertex = validate(u);
		return vertex.getOutgoing().get(v);
	}
	
}

