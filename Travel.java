package cs2321;

import net.datastructures.*;

/**
 * @author Ruihong Zhang
 * Reference textbook R14.16 P14.81 
 *
 */
/**
 * this program finds the optimal path to travel
 * ngwhite
 * @author Nathan White
 *
 */
public class Travel {
	Graph<String, Integer> graph; 
	Map<String, Vertex<String>> cities;
	Map<Vertex<String>, Boolean> visited;
	Map<Vertex<String>, Edge<Integer>> forest;
	DoublyLinkedList<String> itinerary;

	/**
	 * @param routes: Array of routes between cities. 
	 *                routes[i][0] and routes[i][1] represent the city names on both ends of the route. 
	 *                routes[i][2] represents the cost in string type. 
	 *                
	 *                Example:
	 *                  routes = {  {"A","B","8"},
	 *							    {"A","D","1"},
	 *							    {"B","C","11"},
	 * 							    {"C","D","1"}
	 *                           };
	 *                           
	 *                Hint: In Java, use Integer.valueOf to convert string to integer. 
	 */
	/**
	 * this method takes in an array of strings and then initializes and populates all the data structures with the information it needs from the inputed array
	 * @param routes
	 */
	public Travel(String [][] routes) {
		graph = new AdjacencyGraph<String, Integer>();
		cities = new HashMap<>();
		visited = new HashMap<>();
		forest = new HashMap<>();

		for(String[] route: routes) {
			Vertex<String> v1;
			Vertex<String> v2;
			if(cities.get(route[0])!= null) {
				v1=cities.get(route[0]);
			} else {
				v1= graph.insertVertex(route[0]);
				cities.put(route[0], v1);
			}
			if(cities.get(route[1])!= null) {
				v2= cities.get(route[1]);
			}else {
				v2= graph.insertVertex(route[1]);
				cities.put(route[1], v2);
			}
			graph.insertEdge(v1, v2, Integer.valueOf(route[2]));
		}
	}
	/**
	 * this method is a helper method in order to find the route between two locations
	 * @param graph
	 * @param u
	 * @param v
	 * @param forest
	 * @param path
	 * @return
	 */
	private DoublyLinkedList<String> getPath(Graph<String, Integer> graph, Vertex<String> u, Vertex<String> v, Map<Vertex<String>, Edge<Integer>> forest, DoublyLinkedList<String> path){
		if(forest.get(v) != null) {
			Vertex<String> walk = v;
			path.addFirst(walk.getElement());
			while(!walk.equals(u)) {
				Edge<Integer> edge = forest.get(walk);
				walk=graph.opposite(walk, edge);
				path.addFirst(walk.getElement());
			}
		}

		return path;
	}
	/**
	 * this method returns the vertex path between two locations
	 * @param graph
	 * @param u
	 * @param v
	 * @param forest
	 * @return
	 */
	private DoublyLinkedList<String> constructVertexPath(Graph<String, Integer> graph, Vertex<String> u, Vertex<String> v, Map<Vertex<String>, Edge<Integer>> forest){
		return getPath(graph,u,v,forest, new DoublyLinkedList<>());
	}
	/**
	 * this method returns the vertex path between two locations
	 * @param graph
	 * @param u
	 * @param v
	 * @param forest
	 * @return
	 */
	private DoublyLinkedList<String> constructVertexPath(Graph<String, Integer> graph, Vertex<String> u, Vertex<String> v, Map<Vertex<String>, Edge<Integer>> forest, DoublyLinkedList<String> itinerary){
		return getPath(graph, u,v, forest, itinerary);
	}
	/**
	 * this method returns an iterable list of edges from the inputed graph and range
	 * @param graph
	 * @param v
	 * @return
	 */
	private Iterable<Edge<Integer>> sortedOutgoingEdges(Graph<String, Integer> graph, Vertex<String> v){
		Iterable<Edge<Integer>> outgoingEdges = graph.outgoingEdges(v);
		ArrayList<Edge<Integer>> edges = new ArrayList<>();
		for(Edge<Integer> edge : outgoingEdges) {
			edges.addLast(edge);
		}
		for(int i=1;i<edges.size();i++) {
			for(int j=1;j>0;j--) {
				String vj = graph.opposite(v, edges.get(j)).getElement();
				String vjprev = graph.opposite(v, edges.get(j-1)).getElement();
				if(vj.compareTo(vjprev)<0) {
					Edge<Integer> temp = edges.get(j);
					edges.set(j, edges.get(j-1));
					edges.set(j-1, temp);
				}
			}
		}
		return edges;

	}

	/**
	 * this method returns the path between all the edges in the range inputed
	 * @param graph
	 * @param u
	 * @param v
	 * @param forest
	 * @return
	 */
	private DoublyLinkedList<Edge<Integer>> constructEdgePath(Graph<String,Integer> graph, Vertex<String> u, Vertex<String> v, Map<Vertex<String>, Edge<Integer>> forest){
		DoublyLinkedList<Edge<Integer>> path = new DoublyLinkedList<>();
		if(forest.get(v)!=null) {
			Vertex<String> walk=v;
			while(!walk.equals(u)) {
				Edge<Integer> edge= forest.get(walk);
				path.addFirst(edge);
				walk=graph.opposite(walk, edge);
			}
		}
		return path;
	}


	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name, which is different with the departure. 
	 * @return Return the path from departure city to destination using Depth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *		   When there is no path, return null. 
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	              
	 *              See the method sortedOutgoingEdges below. 
	 */
	/**
	 * this method returns an iterable list of strings to represent the route that it would take
	 * @param departure
	 * @param destination
	 * @return
	 */
	public Iterable<String> DFSRoute(String departure, String destination ) {

		boolean exists = DFS(graph, cities.get(departure),cities.get(destination), visited, forest);
		return exists ? constructVertexPath(graph, cities.get(departure),cities.get(destination), forest) : null;


	}
	/**
	 * this is a helper method that calculates the path for the Depth First algorithm
	 * @param graph
	 * @param u
	 * @param v
	 * @param visited
	 * @param forest
	 * @return
	 */
	private boolean DFS(Graph<String, Integer> graph, Vertex<String> u, Vertex<String> v, Map<Vertex<String>, Boolean> visited, Map<Vertex<String>, Edge<Integer>> forest) {
		visited.put(u, true);
		if(u.equals(v)) {
			return true;
		}
		for(Edge<Integer> e : sortedOutgoingEdges(graph, u)) {
			Vertex<String> w = graph.opposite(u, e);
			if(visited.get(w)==null) {
				forest.put(w, e);
				return DFS(graph, w, v, visited, forest);
			}
		}
		return false;
	}


	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name,which is different with the departure. 
	 * @return Return the path from departure city to destination using Breadth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *         When there is no path, return null. 
	 *            
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	             
	 *              See the method sortedOutgoingEdges below. 
	 */
	/**
	 * this method returns an iterable list of strings to represent the route that it would take
	 * @param departure
	 * @param destination
	 * @return
	 */
	public Iterable<String> BFSRoute(String departure, String destination ) {

		return BFS(graph, cities.get(departure), cities.get(destination), forest);

	}

	/**
	 * this is a helper method that calculates the path for the Breadth First algorithm
	 * @param graph
	 * @param u
	 * @param v
	 * @param visited
	 * @param forest
	 * @return
	 */
	private Iterable<String> BFS(Graph<String, Integer> graph, Vertex<String> s, Vertex<String> z, Map<Vertex<String>, Edge<Integer>> forest){
		DoublyLinkedList<Vertex<String>> level = new DoublyLinkedList<>();
		level.addLast(s);
		visited.put(s, true);
		boolean found= false;
		while(!level.isEmpty()) {
			DoublyLinkedList<Vertex<String>> nextLevel = new DoublyLinkedList<>();
			for(Vertex<String> u : level) {
				for(Edge<Integer> edge : sortedOutgoingEdges(graph, u)) {
					Vertex<String> v=graph.opposite(u, edge);
					if(visited.get(v)==null) {
						visited.put(v, true);
						forest.put(v, edge);
						nextLevel.addLast(v);
						if(v.equals(z)) {
							found = true;
							break;
						}
					}
				}
			}
			if(found) break;

			level = nextLevel;
		}
		return constructVertexPath(graph,s,z,forest);
	}
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name, which is different with the departure. 
	 * @param itinerary: an empty PositionalList object will be passed in to the method. 
	 * 	       When a shorted path is found, the city names in the path should be added to the list in the order. 
	 * @return return the cost of the shortest path from departure to destination. 
	 * 			return -1 where there is no path. 
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              
	 *              See the method sortedOutgoingEdges below. 
	 */
	/**
	 * this method outputs the sum of the values in the path of this algorithm
	 * @param departure
	 * @param destination
	 * @param itinerary
	 * @return
	 */
	public int DijkstraRoute(String departure, String destination, DoublyLinkedList<String> itinerary ) {
		PositionalList<Edge<Integer>> edges=Dijkstra(graph, cities.get(departure),cities.get(destination),forest);
		int sum =0;
		for(Edge<Integer> edge:edges) {
			sum +=edge.getElement();
		}
		if(edges.size()==0) {
			return -1;
		}


		constructVertexPath(graph,cities.get(departure),cities.get(destination),forest, itinerary);
		return sum;
	}
	/**
	 * this method outputs a positionalList to represent the path that the algorithm takes
	 * @param graph
	 * @param s
	 * @param z
	 * @param forest
	 * @return
	 */
	private PositionalList<Edge<Integer>> Dijkstra(Graph<String, Integer> graph, Vertex<String> s, Vertex<String > z, Map<Vertex<String>, Edge<Integer>> forest){
		Map<Vertex<String>,Integer> d =new HashMap();
		AdaptablePriorityQueue<Integer, Vertex<String>> apq = new HeapAPQ<>();
		Map<Vertex<String>, Entry<Integer, Vertex<String>>> apqTokens = new HashMap<>();
		Map<Vertex<String>, Integer> cloud = new HashMap<>();
		for(Vertex<String> u : graph.vertices()) {
			if(u.equals(s)) {
				d.put(u,0);
			}else {
				d.put(u,Integer.MAX_VALUE);
			}
			apqTokens.put(u, apq.insert(d.get(u), u));
		}
		while(!apq.isEmpty()) {
			Entry<Integer, Vertex<String>> entry=apq.removeMin();
			Integer distance = entry.getKey();
			Vertex<String> u=entry.getValue();

			if(u.equals(z)) break;

			cloud.put(u, distance);
			apqTokens.remove(u);
			for(Edge<Integer> edge : sortedOutgoingEdges(graph,u)) {
				Vertex<String> v = graph.opposite(u, edge);
				if(cloud.get(v)==null) {
					int newDistance = distance+edge.getElement();
					if(newDistance<d.get(v)) {
						d.put(v, newDistance);
						forest.put(v, edge);
						apq.replaceKey(apqTokens.get(v), newDistance);
					}
				}
			}
		}
		return constructEdgePath(graph,s,z,forest);
	}
}
