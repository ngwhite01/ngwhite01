package cs2321;


import net.datastructures.*;

public class SortedTable<K extends Comparable<K>, V> extends AbstractMap<K,V>  {
	
 
	  //Use Sorted ArrayList for the Underlying storage for the map of entries.
	private ArrayList<mapEntry<K,V>> table; 

	private int size=0;
	public SortedTable(){
		 table=new ArrayList<mapEntry<K,V>>();
	}
	/**
	 * this method finds the index of the key inputed within the number range
	 * @param key
	 * @param low
	 * @param high
	 * @return
	 */
	private int findIndex(K key, int low, int high) {
		if(high<low) { //if high is less than low
			return high+1; //return high + 1 
		}
		int mid =(low+high)/2; //finds the middle of the 2 numbers
		int comp = key.compareTo(table.get(mid).getKey()); //compares the key to the mid key
		if(comp==0) { //if it's the same
			return mid; //return the mid index
		}else if(comp<0) { //if it's less
			return findIndex(key, low, mid-1); //recursively calls at a new range
		}else { //if it's greater
			return findIndex(key, mid+1, high); //recursively calls at a new range
		}
	}
	/**
	 * this method finds the index throughout the whole table
	 * @param key
	 * @return
	 */
	private int findIndex(K key) {
		return findIndex(key, 0, table.size()-1);
	}
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	/*
	 * Time Complexity:
	 * 
	 */
	/**
	 * this method gets the value of the key inputed
	 */
	public V get(K key) {
		int j = findIndex(key); //finds the index of the key 
		if(j==size() || key.compareTo(table.get(j).getKey())!=0){ //if it is out of range or doesn't exist
		return null; //returns null
		}
		return table.get(j).getValue(); //returns the value of the key inputed
	}

	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	/*
	 * Time Complexity:
	 * 
	 */
	/**
	 * this method puts the value inputed at the index of the key
	 */
	public V put(K key, V value) {
		int j=findIndex(key); //finds the index of the key
		if(j<size() && (key.compareTo(table.get(j).getKey())==0)) { //if the index exists and is in range
			V old = table.get(j).getValue(); //sets the old variable equal to the previos value
			table.get(j).setValue(value); //sets the value at j equal to the inputed value
			return old; //returns the old value
		}
		table.add(j, new mapEntry<K,V>(key, value)); //adds a new entry with that key and value to the index j
		size++; //increments size
		return null; //returns null
	}

	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	/*
	 * Time Complexity:
	 * 
	 */
	/**
	 * this method removes the entry at the key inputed
	 */
	public V remove(K key) {
		int j = findIndex(key); //finds the index of the key
		if(j==size() || (key.compareTo(table.get(j).getKey())!=0)){ //if it doesn't exist
		return null; //returns null
		}
		size--; //decreases size
		return table.remove(j).getValue(); //returns the old value and removes at the index j
	}


	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> list = new ArrayList<>(size());
		for(mapEntry<K, V> p : table) {
			list.addLast(p);
		}
		return list;
	}


	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * this method simply returns the size so it has a time complexity of O(1)
	 */
	/**
	 * this method returns the size variable
	 */
	public int size() {
		return size;
	}


	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * this method checks if the size is 0 so it has a runtime of O(1)
	 */
	/**
	 * this method returns a boolean whether the size is 0 or not
	 */
	public boolean isEmpty() {
		if(size()==0) { //if the size is 0
			return true; //return true
		}
		return false; //return false
	}


}
