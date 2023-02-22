package cs2321;


import net.datastructures.Entry;
import net.datastructures.Map;

public class UnorderedMap<K,V> extends AbstractMap<K,V> {
	
	
	 private ArrayList<Entry<K,V>> table; 
	
	
	public UnorderedMap() {
		table = new ArrayList<>();
	}
	
	
	@Override

	/*
	 * Time Complexity:
	 * returns the size of the table so it takes O(1) time
	 */
	/**
	 * this method returns the size of the table
	 */
	public int size() {
		
		return table.size(); //returns size
	}

	@Override

	/*
	 * Time Complexity:
	 * checks if the size is 0 so O(1)
	 */
	/**
	 * this method returns a boolean whether the table is empty or not
	 */
	public boolean isEmpty() {
		if(table.size()==0) { //if the size is 0
			return true; //returns true
		}
		return false; //returns false
	}

	@Override
	
	/*
	 * Time Complexity:
	 * goes through all the entries in the table so it takes O(n) time
	 */
	/**
	 * this method gets the value located at the key inputed
	 */
	public V get(K key) {
		for(Entry<K,V> entry : table) { //iterates through the table
			if(entry.getKey().equals(key)) { //if the current entry's key equals the key
				return entry.getValue(); //returns the value
			}
		}
		return null; //returns null
	}

	@Override
	
	/*
	 * Time Complexity:
	 * goes through all entries and then sets the value to the new value if it already exists so it only takes O(n) time
	 */
	/**
	 * this method puts a new entry into the table if it doesn't exist
	 * if it does exist is replaces the old value with the inputed one and returns the old one 
	 */
	public V put(K key, V value) {
		for(Entry<K,V> entry : table) { //goes through the table
			if(entry.getKey().equals(key)) { //if the current entry's key equals the key
				V old = entry.getValue(); //gets the old value
				((mapEntry<K,V>) entry).setValue(value); //sets the value of the entry equal to the new value
				return old; //returns the old value
			}
		}
		table.addLast(new mapEntry<>(key, value)); //adds a new entry to the end of the table
		return null; //returns null
		
	}

	@Override

	/*
	 * Time Complexity:
	 * goes through the entire table until if finds the entry with the correct key and then it removes it so it takes O(n) time
	 */
	/**
	 * this method goes through the array and finds the index of the key, it then removes it and returns the old value 
	 */
	public V remove(K key) {
		for(int i=0; i<table.size();i++) { //iterates through the table
			if(table.get(i).getKey().equals(key)) { //if the index is equal to the inputed key
				V old = table.get(i).getValue(); //get the value
				table.remove(i); //remove it
				return old; //return the old value
			}
		}
		return null; //returns null if it doesn't exist
	}


	@Override
	/**
	 * this method returns the table
	 */
	public Iterable<Entry<K, V>> entrySet() {
		return table; //returns the table
	}

}

