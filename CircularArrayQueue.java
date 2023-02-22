/**
 * 
 */
package cs2321;

import net.datastructures.Queue;

/**
 * This program has multiple methods used in Josephus.java
 * @author Nathan White
 * CS2321
 * R02
 * @param <E>
 */
public class CircularArrayQueue<E> implements Queue<E> {
	private E[] data; //creates an array to hold data
	private int front = 0; //int to hold the front value
	private int arraysize = 0; //int to hold the size of the array
	
	public CircularArrayQueue(int queueSize) { 
			data = (E[]) new Object[queueSize]; //set data equal to an object array of the size passed in
	}
	
	@Override
	public int size() {
		
		return arraysize; //returns the variable arraysize
	}

	@Override
	public boolean isEmpty() {
		if(arraysize==0) { //checks if the size of the array is 0
			return true; //returns true if it's size is 0
		}
		return false; //returns false if the size is greater than 0
	}


	@Override
	public E first() {
	if(arraysize==0) { //if the size of the array is 0
		return null; //returns null
	}
	return data[front]; //if the array is greater than 0 it returns the front value
	}

	@Override
	public E dequeue() {
		if(arraysize==0) { //if the size of the array is 0
			return null; //returns null
		}
		E answer = data[front]; //creates a holder to hold the front value
		data[front]=null; //sets the front value to null
		front=(front+1 % data.length); //sets the new front value
		arraysize--; //decreases the size of the array
		return answer; //returns the holder for the previous front value
	}

	/* Throw the exception IllegalStateException when the queue is full */
	@Override
	public void enqueue(E e) throws IllegalStateException {
		if(arraysize==data.length) throw new IllegalStateException("Queue is full"); //if the the size of the array is equal to its max size then throw an exception
		{
		int available = (front + arraysize) % data.length; //determines the index of smallest available space
		data[available]=e; //sets data at available equal to the argument passed in
		arraysize++; //increases the size of the array
		}
	}

	    
}
