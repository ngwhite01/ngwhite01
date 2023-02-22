package cs2321;

import net.datastructures.*;

public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {
	
	/* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	 * 
	 */
	private UnorderedMap<K,V>[]  table;
	int 	size;  // number of mappings(entries) 
	int 	capacity; // The size of the hash table. 
	int     DefaultCapacity = 17; //The default hash table size
	
	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor= 0.75;  
	
	/**
	 * Constructor that takes a hash size
	 * @param hashtable size: the number of buckets to initialize 
	 */
	public HashMap(int hashtablesize) {
		size=0;
		capacity=hashtablesize;
		table = new UnorderedMap[capacity];
	}
	
	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
		this(17);
	}
	
	/* This method should be called by map an integer to the index range of the hash table 
	 */
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	/*
	 * The purpose of this method is for testing if the table was doubled when rehashing is needed. 
	 * Return the the size of the hash table. 
	 * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
	 */
	public int tableSize() {
		return capacity;
	}
	
	/**
	 * this method resizes the hash map when it exceeds the capacity it started with
	 * @param capacity
	 */
	private void resize(int capacity) {
		ArrayList<Entry<K,V>> holder = new ArrayList(size); 
		for(Entry<K,V> entry : entrySet()) { 
			holder.addLast(entry);
		}
		this.capacity=capacity;
		table=(UnorderedMap<K,V>[]) new UnorderedMap[capacity];
		size = 0;
		for(Entry<K,V> entry : holder) {
			put(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * this method puts the value into the hashvalue of the inputed key
	 * @param key
	 * @param value
	 * @return
	 */
	private V bucketPut(K key, V value) {
		UnorderedMap<K,V> bucket = table[hashValue(key)];
		if(bucket==null) {
			bucket=table[hashValue(key)] = new UnorderedMap<>();
		}
		int oldSize = bucket.size();
		V value2=bucket.put(key, value);
		size += (bucket.size()-oldSize);
		return value2;
	}
	
	
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * just returns the size variable so it has a runtime of O(1)
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
	 * just checks if the size is 0 so it has a runtime of O(1)
	 */
	/**
	 * this method returns a boolean whether the size is 0 or not
	 */
	public boolean isEmpty() {
		if(size==0) { //if the size is 0 
			return true; //returns true
		}
		return false; //returns false
	}

	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * gets the key of the bucket so it only takes O(1) since it only accesses an index
	 */
	/**
	 * this method gets the value of the inputed key
	 */
	public V get(K key) {
		UnorderedMap<K,V> bucket = table[hashValue(key)]; //grabs the value at the index of the hash value of the key
		if(bucket==null) { //if the bucket is null
			return null; //return null
		}
		return bucket.get(key); //returns value at the index of the key
	}

	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * this method has a runtime of O(1) because it simply puts the hashvalue into the bucket and then returns it.
	 */
	/**
	 * this method puts the value input into the bucket where the key hash value is. If the key already exists it simply replaces the value
	 * if the loadfactor exceeds 0.75 then it resizes the table
	 */
	public V put(K key, V value) {
		V value2 = bucketPut(key, value); //grabs the old value at the key
		if(size/(double) capacity > loadfactor) { //if the loadfactor is greater than 0.75
			resize(2*capacity); //resizes
		}
			
		return value2; //returns the old value
	}

	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * this method has a runtime of O(1) because it gets the value of the hashed key in the table map and then removes it at that index.
	 */
	/**
	 * this method removes the value at the key inputed and returns the old value
	 */
	public V remove(K key) {
		UnorderedMap<K,V> bucket = table[hashValue(key)]; //gets the value at the index of the hashed key
		if(bucket==null) { //if the bucket is null
			return null; //return null
		}
		int oldSize = bucket.size(); //the size of the bucket
		V value = bucket.remove(key); //gets the old value at the key
		size -= (oldSize - bucket.size()); //creates the new size
		return value; //returns the old value
	}

	@Override
	/**
	 * returns an iterable list of entries
	 */
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> holder = new ArrayList<>(size);
		for(int i=0;i<capacity;i++) {
			if(table[i]!=null) {
				for(Entry<K,V> entry : table[i].entrySet()) {
					holder.addLast(entry);
				}
			}
		}
		return holder;
	}
	
	

}
