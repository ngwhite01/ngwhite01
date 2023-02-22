package cs2321;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.SortedMap;


public class BinarySearchTree<K extends Comparable<K>,V> extends AbstractMap<K,V>  {
	
	/* all the data will be stored in the tree*/
	LinkedBinaryTree<Entry<K,V>> tree; 
	int size=0;  //the number of entries (mappings)
	
	/* 
	 * default constructor
	 */
	public BinarySearchTree() {
		super();
		size=0;
		tree= new LinkedBinaryTree<>();
		tree.addRoot(null);
	}
	
	/* 
	 * Return the tree. The purpose of this method is purely for testing. 
	 * You don't need to make any change. Just make sure to use variable tree to store your entries. 
	 */
	private int compare(K key, K key2) {
		return key.compareTo(key2);
	}
	/**
	 * this method searches through the tree from to position inputed in order to find the position of the key
	 * @param p
	 * @param key
	 * @return
	 */
	private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key){
		if(tree.isExternal(p)) {
			return p;
		}
		
		int comp=compare(key, p.getElement().getKey());
		if(comp==0) {
			return p;
		}
		else if(comp<0) {
			return treeSearch(tree.left(p),key);
		}else {
			return treeSearch(tree.right(p),key);
		}
	}
	
	/**
	 * this method returns the tree
	 * @return
	 */
	public LinkedBinaryTree<Entry<K,V>> getTree() {
		return tree;
	}
	
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * just has to return the value of a variable
	 */
	/**
	 * this method returns the size of the tree
	 */
	public int size(){
		return ((tree.size()-1)/2);
	}
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	/*
	 * Time Complexity:
	 * it has to search through the entire tree to find the key
	 */
	/**
	 * this method returns the value of the key inputed 
	 */
	public V get(K key) {
		Position<Entry<K,V>> p = treeSearch(tree.root(), key); //searches through the entire tree for the key
		if(tree.isExternal(p)) //if the position is a sentry node
			return null; //return null
		return p.getElement().getValue(); //retuns the value
	}

	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	/*
	 * Time Complexity:
	 * it has to search through the entire tree in order to find the value and then puts it in if it doesn't already exist
	 */
	/**
	 * this method puts the inputed value into the position of the key inputed
	 */
	public V put(K key, V value) {
		Entry<K,V> entry = new mapEntry(key,value); //creates a new entry with the inputed values
		Position<Entry<K,V>> p=treeSearch(tree.root(), key); //searches the tree for the position of the key
		if(tree.isExternal(p)) { //if its a sentry node 
			tree.setElement(p, entry); //sets the element of the sentry node to the created entry
			tree.addLeft(p, null); //sets the left to null
			tree.addRight(p, null); //sets the right to null
			return null; //returns null
		}
		V old = p.getElement().getValue(); //if the node isn't a sentry then it grabs the old value
		tree.setElement(p, entry); //sets the element at the position equal to the new entry's values
		return old; //returns the old value
	}

	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	/*
	 * Time Complexity:
	 * it has to search the whole tree to find the correct entry and then it removes it and fixes the table
	 */
	/**
	 * this method removes the entry at the key inputed
	 */
	public V remove(K key) {
		Position<Entry<K,V>> p=treeSearch(tree.root(), key); //find the position of the key
		if(tree.isExternal(p)) { //checks if it is a sentinel node
			return null; //returns null
		}
		V old = p.getElement().getValue(); //grabs the old value
		if(tree.isExternal(tree.left(p))) { //if the left node is a sentinel
			removeEntryandParent(tree.left(p)); //removes the sentinel and the parent
		}else if(tree.isExternal(tree.right(p))) {  //if the right node is a sentinel
			removeEntryandParent(tree.right(p)); //removes the sentinel and the parent
		}else { //if it isn't a sentinel
			Position<Entry<K,V>> successor = treeMin(tree.right(p)); //finds the successor node of the parent
			Position<Entry<K,V>> external = tree.left(successor); //finds the next sentinel node
			tree.setElement(p, successor.getElement()); //sets the element 
			removeEntryandParent(external); //removes the entry and the parent of the sentinel 
		}
		return old; //returns the old value
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> list = new ArrayList<>(); //creates a new array list
		for(Position<Entry<K, V>> p: tree.inorder()) { // goes through the entire tree
				list.addLast(p.getElement()); //adds the element to the list
		}
		return list; //returns the list
	}



	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * just checks if the size is 0
	 */
	/**
	 * this method returns whether the tree is empty or not
	 */
	public boolean isEmpty() {
		if(tree.size()==0) { //if the size is 0
			return true; //returns true
		}
		return false; //returns false
	}

	/**
	 * this method removes the entry and the parent of the inputed position
	 * @param p
	 */
	private void removeEntryandParent(Position<Entry<K,V>> p) {
		Position<Entry<K,V>> parent = tree.parent(p); //gets the parent of the inputed position
		tree.remove(p); //removes the current
		tree.remove(parent); //removes the parent
	}
	
	private Position<Entry<K,V>> treeMin (Position<Entry<K,V>> p) {
		Position<Entry<K,V>> holder=p; //creates a position called holder to contain the position inputed
		while(tree.isInternal(holder)) { //while the node isn't a sentinel
			holder=tree.left(holder); //holder equals the left value of the holder
		}
		return tree.parent(holder); //returns the parent of the holder node
	}

}
