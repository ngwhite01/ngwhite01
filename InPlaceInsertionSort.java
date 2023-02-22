package cs2321;
/**
 * Nathan White
 * ngwhite
 * @author Nathan White
 *
 */
public class InPlaceInsertionSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	@TimeComplexityAmortized("O(n^2)")
	/*
	 * Time Complexity:
	 * this method iterates fully through the array n times in the while loop 
	 * while also going through the array another time in the for loop ending with a time complexity of n^2
	 */

	public void sort(E[] array) {
		/*
		 * K is an generic, but comparable type. 
		 * Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
		 */
		E holder; //create a holder variable

		for(int i=1; i<array.length;i++) { //iterate through the array
			holder=array[i]; //set holder equal to the current element
			int j=i; //set j equal to i
			while((j>0 && array[j]!=null) && array[j-1].compareTo(holder)>0) { //while the previous element is greater than the current
				
				array[j]=array[j-1]; //current equals next
				j--; ; //decreases j
			}
			E holder2=null; //creates another holder
			for(int k=j+1; k<array.length-1;k++) { //iterates through the array from j+1 to the end
				holder2 = array[k+1]; //sets holder2 equal to the next element
				array[k+1]=array[k]; //replaces the next element with the current
			}
			array[array.length-1]=holder2; //sets the final index equal to holder2
			array[j]=holder; //sets the array at j+1 to holder
		}
	}

}
