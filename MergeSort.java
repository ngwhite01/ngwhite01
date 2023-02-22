package cs2321;

import java.util.Arrays;
/**
 * Nathan White
 * ngwhite
 * @author Nathan White
 *
 */
public class MergeSort<E extends Comparable<E>> implements Sorter<E> {
/**
 * This method merges the two arrays that are input into it into the 3rd ray inputed
 * @param Array1 -1st array to merge
 * @param Array2 -2nd array to merge
 * @param Array -the array in which the previous 2 are merged
 */
	
	public void merge(E[] Array1, E[] Array2, E[] Array) {
		int i=0;
		int j=0;
		int k=0;
		int size1=Array1.length;
		int size2=Array2.length;

		while(i<size1 && j<size2) { //goes through the 2 halves of the array that are inputed through array 1 and 2
			E element1=Array1[i]; //grabs the current element of the 1st array
			E element2=Array2[j]; //grabs the current element of the 2nd array
			if(j==Array2.length || (i<Array1.length && element1.compareTo(element2)<0)){ //determines the order in which the values will be sorted into
				Array[k]=Array1[i];
				i++;
				k++;
			}
			else {
				Array[k]=Array2[j];
				j++;
				k++;
			}
		}
	while(i<size1) {
		Array[k]=Array1[i];
		i++;
		k++;
	}
	while(j<size2) {
		Array[k]=Array2[j];
		j++;
		k++;
	}
	}

	/**
	 * This method recursively calls itself as well as calls merge in order to sort and array passed into it
	 * @param array -array to be sorted
	 * @param left -left index
	 * @param right -right index
	 * @return
	 */
	
	public E[] mergeSort(E[] array, int left, int right) {
		int size=array.length; //creates a variable to hold the size of the array
		if(size<2) { //checks if there are only 2 elements in the array
			return array; //returns the array
		}
		int mid= size/2; //calculates the midpoint in the array
		E[] array1 = Arrays.copyOfRange(array,0,mid);
		E[] array2 = Arrays.copyOfRange(array, mid, size);
		E[] newarray= mergeSort(array1, left, mid); //creates a new array of half the array sorted
		E[] newarray2= mergeSort(array2, mid, right); //creates a new array of the latter half of the inputed array

		merge(newarray, newarray2, array); //calls the merge method inorder to merge the 2 arrays 
		return array; //returns the array
	}
	/**
	 * sort - Perform merge sort. -Feel free to create other methods. 
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n log n)")
	@TimeComplexityAmortized("O(n log n)")
	/*
	 * Time Complexity:
	 * this method has to call merge sort which goes through half the array as many times as there are elements in the array in order to sort them. 
	 * This ends up having a time complexity of n log n
	 */
	public void sort(E[] array) {
		/*
		 * K is an generic, but comparable type. 
		 * Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
		 * 
		 * If you need to create an array of E,  use the following line as E is Comparable
		 * E[]  newarray = (E[]) Comparable[];
		 */

		mergeSort(array, 0, array.length-1); //calls the mergeSort method with the inputed array its 1st index and its last index

	}
}

