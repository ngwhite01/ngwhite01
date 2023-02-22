package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author
 */
/**
 * This program creates a heap data type using an arraylist as entries.
 * @author Nathan White
 * CS2321
 * R02
 * ngwhite
 */
public class HeapAPQ<K,V> implements AdaptablePriorityQueue<K,V> {

	public static class DefaultComparator<K> implements Comparator<K> {

		// This compare method simply calls the compareTo method of the argument. 
		// If the argument is not a Comparable object, and therefore there is no compareTo method,
		// it will throw ClassCastException. 
		public int compare(K a, K b) throws IllegalArgumentException {
			if (a instanceof Comparable ) {
				return ((Comparable <K>) a).compareTo(b);
			} else {
				throw new  IllegalArgumentException();
			}
		}
	}
	private ArrayList<Entry<K,V>> heap = new ArrayList<Entry<K, V>>();
	private int capacity; //variable to hold capacity
	private static class apqEntry<K,V> implements Entry<K,V> {
		private int index; //variable to hold the index of the entry
		private K key; //variable to hold the key of the entry
		private V value; //variable to hold the value of the entry
		@Override
		public K getKey() {

			return this.key; //returns the key value

		}

		@Override
		public V getValue() {
 
			return this.value; //returns the value of the value variable
		}

		public int getIndex() {

			return this.index; //returns the index of the entry
		}

		public void setIndex(int index) {

			this.index = index; //sets the index of this entry to the input index
		}

	}

	/* If no comparator is provided, use the default comparator. 
	 * See the inner class DefaultComparator above. 
	 * If no initial capacity is specified, use the default initial capacity.
	 */

	private Comparator<K> c; //creates a comparator called c

	public HeapAPQ() {
		super(); //superclass
		c = new DefaultComparator(); //sets c equal to a new default comparator
	}

	/* Start the PQ with specified initial capacity */
	public HeapAPQ(int capacity) {
		super(); //superclass
		this.capacity=capacity; //sets capacity equal to the input 
		c = new DefaultComparator(); //sets c equal to a new default comparator

	}


	/* Use specified comparator */
	public HeapAPQ(Comparator<K> c) {
		super(); //superclass
		this.c=c; //sets the c comparator equal to the input
	}

	/* Use specified comparator and the specified initial capacity */
	public HeapAPQ(Comparator<K> c, int capacity) {
		super(); //superclass
		this.capacity=capacity; //sets the capacity equal to the input
		this.c=c; //sets the c comparator equal to the input
	}
	private int parent(int j) { //method to find the parent
		return ((j-1) / 2); }
	private int left(int j) { //method to find the left
		return (2*j + 1); } 
	private int right(int j) { //method to find the right
		return (2*j + 2); } 
	private boolean hasLeft(int j) { //method to check if there is a left
		return left(j) < heap.size(); } 
	private boolean hasRight(int j) { //method to check if there is a right
		return right(j) < heap.size(); }

/**
 * This method takes in an int and then resets the order of the heap based on if the parent is greater than the child
 * @param j
 */
	@TimeComplexity("O(log n)")
	private void upheap(int j) {
		/* TC
		 * upheap checks each level of the heap until it is in the right order
		 * The number of entries to check is cut in half but worst case it has to go through log n iterations 
		 */
		int p; 
		while (j > 0) {	// continue until reaching root (or break statement) 
			p = parent(j); 
			if (c.compare(heap.get(j).getKey(), heap.get(p).getKey()) < 0) { // heap property verified swap(j, p);
				swap(j, p);
				j = p;	
			}else {
				break;
			}
		}
	}

	/**
	 * this method swaps 2 entries passed to it and sets their new indexes
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		apqEntry<K,V> temp = (apqEntry<K,V>)heap.get(i); 
		heap.set(i, heap.get(j)); 
		heap.set(j, temp);


		((apqEntry<K, V>) heap.get(i)).setIndex(i);
		((apqEntry<K, V>) heap.get(j)).setIndex(j);
	}

	/**
	 * this method resets the order of the heap based on the input location and the values of the children versus the parents
	 * @param j
	 */
	@TimeComplexity("O(log n)")
	protected void downheap(int j) {
		/* TC
		 * downheap checks each level of the heap until it is in the right order
		 * The number of entries to check is cut in half but worst case it has to go through log n iterations 
		 */
		while (hasLeft(j)) {
			int leftIndex = left(j); 
			int smallChildIndex = leftIndex; 
			if(hasRight(j)) {
				int rightIndex = right(j); 
				if (c.compare(heap.get(leftIndex).getKey(), heap.get(rightIndex).getKey()) > 0) {
					smallChildIndex = rightIndex;
				}
			}
			if (c.compare(heap.get(smallChildIndex).getKey(), heap.get(j).getKey()) < 0) {
				swap(j, smallChildIndex); 
				j = smallChildIndex;
			}else {
				break;
			}

		}
	}


	/* 
	 * Return the array in arraylist that is used to store entries  
	 * This method is purely for testing purpose of auto-grader
	 */
	public Object[] data() {
		

		return  heap.toArray();
	}

	/**
	 * this method returns the size of the heap
	 */
	@Override
	@TimeComplexity("O(1)")
	public int size() {
		/* TC
		 * it has to access the size so it has to run once 
		 */
		return heap.size();
	}
	
	/**
	 * this method returns true if the heap is empty
	 */
	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		/* TC
		 * it has to access the size and then return a value so it only runs once
		 */
		if(heap.size()==0) {
			return true;
		}
		return false;
	}

	
	@Override
	@TimeComplexity("O(log n)")
	@TimeComplexityAmortized("O(log n)")
	/*
	 * this method takes in a key and value and inserts them into the heap as a new entry
	 * it also resets the order of the heap once the new value is inputted to the end of the heap
	 */
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		/* TC
		 * this method calls upheap so it has a runtime of log n
		 */
		apqEntry<K, V> newest = new apqEntry<K, V>();
		newest.key=key;
		newest.value=value;
		newest.index=(heap.size());
		heap.addLast(newest);
		upheap(heap.size() - 1);

		return newest;
	}

	@Override
	@TimeComplexity("O(1)")
	/*
	 * this method returns the value of the entry at the top of the heap
	 */
	public Entry<K, V> min() {
		/* TC
		 * this method only runs once as it calls the isEmpty method which has a runtime complexity of O(1) 
		 */
		if(heap.isEmpty()) {
			return null;
		}
		return heap.get(0);
	}

	@Override
	@TimeComplexity("O(log n)")
	/**
	 * this method removes the min key value of the heap and then resets the order of the heap
	 */
	public Entry<K, V> removeMin() {
		/* TC
		 * this method has a runtime of log n since swap and removeLast both only run once and downheap has a runtime of log n
		 */
		if(heap.isEmpty()) {
			return null;
		}
		Entry<K,V> answer=heap.get(0);
		swap(0, heap.size()-1);
		heap.removeLast();
		if(heap.size() > 0) {
		downheap(0);
		}
		return answer;
	}

	@Override
	@TimeComplexity("O(log n)")
	/**
	 * this method removes the entry with the index of the entry passed in
	 * it then resets the order of the heap to make sure its in order
	 */
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		/* TC
		 * this method has a runtime of log n because swap and remove last run once and downheap has a runtime of log n
		 */
		if(heap.isEmpty()) {
			throw new IllegalArgumentException();
		}
		apqEntry<K,V> answer=(apqEntry<K,V>)entry;
		int index = answer.getIndex();
		swap(index, heap.size()-1);
		heap.removeLast();
		downheap(index);

	}

	@Override
	@TimeComplexity("O(log n)")
	/**
	 * this method replaces the key value of the entry you input with the key value inputted into the method
	 */
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		/* TC
		 * this method has a runtime of log n because it runs through most the code once but then calls the upheap or downheap methods which have a runtime of log n
		 */
		apqEntry<K, V> holder = (apqEntry<K, V>) entry;
		
		K original = holder.getKey();
		int index=holder.getIndex();
		apqEntry<K, V> newEntry = new apqEntry<K, V>();
		newEntry.key=key;
		newEntry.value=holder.getValue();
		newEntry.index=index;
		heap.set(index, newEntry);
		if(c.compare(key, original) < 0) {
			upheap(index);
		} else {
			downheap(index);
		}

	}

	@Override
	@TimeComplexity("O(1)")
	/**
	 * this method replaces the value of the entry passed in with the value passed in as an argument for the method
	 */
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		/* TC
		 * this method has a run time of 1 since it only sets the new entry at the index without iterating through it
		 */
		apqEntry<K, V> holder = (apqEntry<K, V>) entry;
	
		holder.value=value;
		entry=holder;

	}




}
