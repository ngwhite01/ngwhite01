package cs2321;
/**
 * Nathan White
 * ngwhite
 * @author Nathan White
 *
 */
public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

		/**
	 * This method recursively calls itself in order to sort itself
	 * @param array
	 * @param p
	 * @param r
	 * @return
	 */
	
	public E[] quickSort(E[] array, int p, int r) {
		if(p<r) { //checks if front is less than the end
			int q=partition(array,p,r); //calls the partition method
			quickSort(array, p, q-1); //calls the quickSort method but with one fewer index on the end 
			quickSort(array,q+1,r); //calls the quickSort method but with one fewer index in the front
		}
		return array; //returns the array
	}
	/**
	 * This method returns the index of the pivot point of the array
	 * @param array
	 * @param p
	 * @param r
	 * @return
	 */
	
	public int partition(E[] array, int p, int r) {
		E holder=array[r]; //sets holder equal to the final index
		int i=p-1; //creates an int i to be 1 before the previous
		for(int j=p;j<r;j++) { //iterate through the range
			if(array[j].compareTo(holder)<=0) { //if the previous is less than or equal to the final value
				i=i+1; //increases the value of i
				E holder2=array[i]; //sets holder equal to the the index at the new i value
				array[i]=array[j]; //sets the value at i equal to j
				array[j]=holder2; //sets the value at j equal to the held value of array i
			}
		}
		array[r]=array[i+1]; //the value at r becomes the value at i+1
		array[i+1]=holder; //sets the value at i+1 to holder
		return i+1; //returns i+1

	}
	/**
	 * sort - Perform quick sort. -Feel free to create other methods. 
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n log n)")
	@TimeComplexityAmortized("O(n^2)")
	/*
	 * Time Complexity:
	 * This method calls quickSort which takes log n time however it calls itself n times which ends up being n log n. 
	 * The best case scenario would be it being in order and just have to go through both the loops to check first which would end up with n^2
	 */

	public void sort(E[] array) {
		quickSort(array, 0, array.length-1); //calls the quickSort method with the array passed in along with its 1st and last element
	}
}
