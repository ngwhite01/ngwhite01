package cs2321;
import java.util.Iterator;

import net.datastructures.*;
	

/**
 * @author ruihong-adm
 *
 * @param <E>
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>{

	/**
	 * creates a class that makes node objects
	 * @author Nathan White
	 *
	 * @param <E>
	 */
	private static class Node<E> implements Position<E>{
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent=above;
			left=leftChild;
			right=rightChild;
			
		}
		public E getElement() {
			return element;
		}
		public Node<E> getParent() {
			return parent;
		}
		public Node<E> getLeft() {
			return left;
		}
		public Node<E> getRight() {
			return right;
		}
		public void setElement(E e) {
			element=e;
		}
		public void setParent(Node<E> parentNode) {
			parent=parentNode;
		}
		public void setLeft(Node<E> leftChild) {
			left=leftChild;
		}
		public void setRight(Node<E> rightChild) {
			right=rightChild;
			}
		}
		protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
			return new Node<E>(e, parent, left, right);
		}
		
		public int numChildren(Position<E> p) throws IllegalArgumentException{
			int count = 0;
			if(left(p)!=null) {
				count++;
			}
			if(right(p)!=null) {
				count++;
			}
			return count;
			
		}
		/**
		 * this method validates the position of the inputed position
		 * @param p
		 * @return
		 * @throws IllegalArgumentException
		 */
		protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
			if(!(p instanceof Node)) { //checks that the position is a node
				throw new IllegalArgumentException("Not valid position type");
			}
			Node<E> node = (Node<E>) p; //creates the node with the position inputed
			if(node.getParent()==node) { //if the node equals its parent
				throw new IllegalArgumentException("p is no longer in the tree");
			}
			return node; //returns the node
			
		}
		
		public void inorderSubtree(Position<E> p, ArrayList<Position<E>> list) {
			if(left(p) != null) { //if the left isn't null
				inorderSubtree(left(p), list); //calls it recursively
			}
			list.addLast(p); //adds the position to the list
			if(right(p) != null) { //if the right isn't null
				inorderSubtree(right(p), list); //calls it recursively
			}
			
		}
		
		/**
		 * this method returns a list of positions in order
		 * @return
		 */
		public Iterable<Position<E>> inorder() {
			ArrayList<Position<E>> list = new ArrayList<>();
			if(size()==0) {
				inorderSubtree(root(), list);
			}
			
			return list;
		}
		
		protected Node<E> root;
		private int size=0;
		
	@Override
	/**
	 * this method returns the root variable
	 */
		public Position<E> root() {
		return root;
	}
	
	public  LinkedBinaryTree( ) {
		root = null;
	}
	

	
	
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}
	
	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		return (numChildren(p)>0);
	}

	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p)==0;
	}

	
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * returns the value of a variable so O(1)
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
	 * simply checks if the size is 0 so O(1)
	 */
	/**
	 * thie method returns a boolean whether the size variable is 0 or not
	 */
	public boolean isEmpty() {
		if(size()==0) { //if the size is 0
			return true; //returns true
		}
		return false; //returns false
	}
	
	/* creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * this method checks if the tree is empty and simply creates a new node if it is so O(1)
	 */
	/**
	 * this method adds a node at the root if the tree is empty
	 * @param e
	 * @return
	 * @throws IllegalStateException
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if(!isEmpty()) {
			throw new IllegalStateException("Tree is not empty");
		}
		root = createNode(e, null, null, null);
		size=1;
		return root;
	}
	
	
	/* creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * this method takes O(1) time since it just validates a node and creates a new node to be its left child if it doesn't have one already
	 */
	/**
	 * this method adds a node to the left
	 * @param p
	 * @param e
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p); //validates the position
		if(parent.getLeft() !=null) { //if the node to the left isn't null
			throw new IllegalArgumentException("p already has a left child");
		}
		Node<E> child = createNode(e, parent, null, null); //creates a new child if the position doesn't already have a left child
		parent.setLeft(child); //sets the parent's left node to the child
		size++; //increments the size
		return child; //returns the child
	}

	/* creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * this method takes O(1) time since it just validates a node and creates a new node to be its right child if it doesn't have one already
	 */
	/**
	 * this method adds a node to the right
	 * @param p
	 * @param e
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p); //validates the position
		if(parent.getRight() !=null) { //if the parent already has a right child
			throw new IllegalArgumentException("p already has a right child");
		}
		Node<E> child = createNode(e, parent, null, null); //creates a new node if it doesn't have right child
		parent.setRight(child); //sets the right of the parent to the child
		size++; //increments the size
		return child; //returns the child
	}
	
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * validates the node and then sets the element of that node so it takes O(1)
	 */
	/**
	 * this method sets the element of the position inputed to the inputed element
	 * @param p
	 * @param element
	 * @throws IllegalArgumentException
	 */
	public void setElement(Position<E> p, E element) throws IllegalArgumentException {
		Node<E> node=validate(p); //validates the position
		node.setElement(element); //sets the element equal to the inputed element
	}

	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the in-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	/*
	 * Time Complexity:
	 * calls inorderSubtree method which iterates through the tree and outputs an arraylist and that takes O(n) time
	 */
	/**
	 * this method outputs a list of the elements in order
	 * @param p
	 * @return
	 */
	public List<E> inOrderElements(Position<E> p) {
		ArrayList<Position<E>> list = new ArrayList<>(); //creates an array list
		inorderSubtree(p, list); //calls inorderSubtree to fill the list
		return (List<E>)list; //returns the list
	}

	/**
	 * If p has two children, throw IllegalAugumentException. 
	 * If p is an external node ( that is it has no child), remove it from the tree.
	 * If p has one child, replace it with its child. 
	 * If p is root node, update the root accordingly. 
	 * @param p who has at most one child. 
	 * @return the element stored at position p if p was removed.
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	/*
	 * Time Complexity:
	 * this method calls multiple methods that take O(1) time but none of them iterate through the tree so it takes O(1)
	 */
	/**
	 * this method removes the node at the position inputed
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node= validate(p); //validates the position
		if(numChildren(p)==2) { //checks if it has 2 children
			throw new IllegalArgumentException("p has two children");
		}
		Node<E> child= (node.getLeft() !=null ? node.getLeft() : node.getRight()); //creates a child node
		if(child !=null) { //if child isn't null
			child.setParent(node.getParent()); //sets the parent of the child equal to the parent of the current node
		}
		if(node.equals(root)) { //if the node equals the root
			root = child; //sets the root equal to the child
		}
		else {
			Node<E> parent = node.getParent(); //creates a parent node
			if(node.equals(parent.getLeft())) { //if the node equals the left child of the parent 
				parent.setLeft(child); //sets the left child of the parent equal to the child node
			}
			else {
				parent.setRight(child); //sets the right child of the parent equal to the child
			}
		}
		size--; //lowers the size
		E temp = node.getElement(); //gets the element of the node
		node.setElement(null); //sets the element of the node to null
		node.setLeft(null); //sets the left child of the node to null
		node.setRight(null); //sets the right child of the node to null
		node.setParent(node); //sets the parent of the node to the node
		return temp; //returns the node's previous element
	}
}
