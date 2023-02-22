package cs2321;
/**
 * Nathan White
 * ngwhite
 * @author Nathan White
 *
 */
public class HeapPQSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an PrioiryQueue Sort using the heap implementation of PQ.
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n log n)")
	@TimeComplexityAmortized("O(n log n)")
	/*
	 * Time Complexity:
	 * This method uses queue.insert which takes log n time and it is 
	 * called n times as it iterates through the array. This ends up with a runtime of n log n 
	 */

	public void sort(E[] array) {
		/*
		 * K is an generic, but comparable type. 
		 * Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
		 */
		HeapAPQ<E, E> queue = new HeapAPQ<>(); //creats a new heapAPQ
		for(int j=0;j<array.length;j++) { //iterates through the array
			queue.insert(array[j], null); //insert the current value of the array into the queue as the key value
		}
		int size = queue.size(); //set the size equal to the size of the queue
		for(int i=0; i<size;i++) { //iterate through the queue
			E element = queue.removeMin().getKey(); //passes in the lowest value to the variable element
			array[i]=element; //sets the current index equal to element
		}
	}

}
