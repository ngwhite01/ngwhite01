package cs2321;

import net.datastructures.Stack;

/**
 * This program has many methods that are unsed in order to implement a linked list stack. It has all the methods to edit this data structure
 * @author Nathan White
 * CS2321
 * R02
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E> {

	private int listsize = 0; //creates a value to hold the size of the list
	class Node{ //creates a node class to create a linked list
		E token; //token is the value
		Node next; //next is the next value in the linked list
	}
	
	private Node head; //creates a node called head
	
	public LinkedListStack(){ //creates a constructor for no arguments 
		this.head=null;
	}
	
	public LinkedListStack(int size){ //creates a constructor for the argument of size
		this.head=null;
		this.listsize=size;
	}
	
	@Override
	public int size() {
		return listsize; //returns the current size of the list
	}

	@Override
	public boolean isEmpty() {
		if(listsize==0) { //if the size of the list is 0
			return true; //returns true
		}
		return false; //returns false
	}

	@Override
	public void push(E e) {
		Node holder = new Node(); //creates a holder node
		holder.token=e; //sets the value of the holder node equal to the inputed value
		holder.next=head; //sets the next node to the holder equal to the head
		head=holder; //sets the new head equal to the holder
		listsize++; //increases the size of the list
	}

	@Override
	public E top() {
		if(listsize==0) { //if the size of the list is 0
			return null; //return null
		}
		return head.token; //return the value of the head node
	}

	@Override
	public E pop() {
		Node holder = new Node(); //creates a holder node			
		if(listsize==0) { //if the size of the list is 0
			return null; //return null
		}
		holder.token=head.token; //sets the value of holder equal to the value of head
		head=head.next; //sets head equal to the next node
		listsize--; //decreases the size of the list 
		return holder.token; //returns the value of the holder node
	}

}
