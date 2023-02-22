package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TravelNoPathTest {
	Travel T;
	@Before
	public void setUp() throws Exception {
		/*
	
		              A
                    /   \             
                   /      \  
		          8         1             
 		         /           \         
		       /              \
		      B --11-- C --1-- D
		  
		              O
                    /   \             
                   /      \  
		          8         1             
 		         /           \         
		       /              \
		      P --11-- Q --1-- R

		 */
		String routes[][] = {  {"A","B","8"},
								 {"A","D","1"},
								 {"B","C","11"},
								 {"C","D","1"},
								 
								 {"O","P","8"},
								 {"O","R","1"},
								 {"P","Q","11"},
								 {"Q","R","1"}	 
								 
							};
		
		T = new Travel(routes);
	}


	
	@Test
	public void testDFSRoute() {		
		assertNull(T.DFSRoute("A", "O"));
	}
	
	
	@Test
	public void testBFSRoute() {		
		assertNull(T.BFSRoute("A", "O"));
	}
	
	@Test
	public void testDijkstraRoute() {		
		DoublyLinkedList<String> path = new DoublyLinkedList<String>();
		assertEquals(-1, T.DijkstraRoute("A", "O",  path));
	}
	

}
