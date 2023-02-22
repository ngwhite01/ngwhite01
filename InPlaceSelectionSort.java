package cs2321;
/**
 * Nathan White
 * ngwhite
 * @author Nathan White
 *
 */
public class InPlaceSelectionSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	@TimeComplexityAmortized("O(n^2)")
	/*
	 * Time Complexity:
	 * this method iterates through the array initially to find the miniindex 
	 * inside of the for loop that is sorting that value versus the current value of the loop. This ends up having a time complexity of n^2
	 */

	public void sort(E[] array) {
		/*
		 * K is an generic, but comparable type. 
		 * Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
		 */
		int miniindex=0; //creates a variable to hold the lowest value
		E holder=null; //creates a holder variable
		for(int i=0; i<array.length;i++) { //iterates through the array
			miniindex=i; //sets the miniindex equal to the current value
			for(int j=i+1;j<array.length;j++) { //iterates through the array at every point after the current
				if(array[j].compareTo(array[miniindex])<0) { //if any value after is smaller than the miniindex
					miniindex=j; //sets the miniindex to j
				}
			}
			if(miniindex != i) { //if the current isn't the minindex
				holder=array[i]; //holds the value of array at index i 
				array[i]=array[miniindex]; //sets array at i to the miniindex value
				array[miniindex]=holder; //sets the miniindex equal to the current index's value
			}
		}
	}

}
