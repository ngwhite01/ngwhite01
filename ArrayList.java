package cs2321;

import java.util.Iterator;

import net.datastructures.List;
/**
 * This program overrides the methods of the list data structure
 * CS2321
 * R02
 * @author Nathan White
 *
 * @param <E>
 */
public class ArrayList<E> implements List<E> {

	public static int CAPACITY = 16; //creates a int to hold the capacity
	private E[] data; //creates an array of generics 
	private int size =0; //creates an int to hold the size

	private class ArrayListIterator implements Iterator<E> { //method to iterate
		int holder =0;

		@Override
		public boolean hasNext() { //method to check if there's a next value
			if(holder<=size-1) {
				return true;
			}
			return false;
		}

		@Override
		public E next() { //method to return the next value

			return data[holder++];
		}

	}

	public ArrayList() { //constructor for the arraylist
		// Default capacity: 16
		this(CAPACITY);
	}

	public ArrayList(int capacity) { //constructor for the arraylist with an input for the capacity
		// create an array with the specified capacity
		data=(E[]) new Object[capacity];
		CAPACITY=capacity;
	}

	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException { //method to check if the index is within the range of the array
		if (i < 0 || i >= n) { //if its out of the range it throws an exception
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
	}





	@Override
	public int size() { //method to return the size
		return size;
	}

	// Return the current capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled. 
	public int capacity() { //method to return the capacity
		
		return CAPACITY; //returns the capacity
	}


	@Override
	public boolean isEmpty() { //method to check if the array is empty
		if(size==0) { //if the size is 0 
			return true; //returns true
		}
		return false;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException { //gets the value at the index inputed
		checkIndex(i, size); //calls the checkindex method to make sure its in the range
		return data[i]; //returns the value at the input
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException { //method to set the value at the index inputed to the value
		checkIndex(i, size); //calls the checkindex method to make sure its in the range
		E temp= data[i]; //creates a temp value to hold data
		data[i]=e; //sets data at i to e
		return temp; //returns the temp value
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException { //method to add in a value at the index to the value inputed
		checkIndex(i, size+1); //checks if its in range
		if(size==data.length) { //if the size is equal to the length of the array it is full so it calls capacity
			E[] holder = (E[]) new Object[2*CAPACITY]; //creates a holder generic array
			CAPACITY = 2*CAPACITY; //doubles the capacity
			for(int j=0;j<data.length;j++) { //iterates through the array
				holder[j]=data[j]; //sets the holder equal to the current value of the data array
			}
			
			data=holder; //sets it equal to holder
		}
		for(int k=size-1; k>=i; k--) { //iterates through the array
			data[k+1]=data[k];
		}
		data[i]=e; //sets data at i to the input
		size++; //increases size

	}

	@Override
	public E remove(int i) throws IndexOutOfBoundsException { //method to remove the value at the index
		checkIndex(i, size); //checks the index 
		E temp = data[i]; //creates a temp value for data i
		for(int k=i; k<size-1;k++) { //iterates through the array
			data[k]=data[k+1];
		}
		data[size-1]=null; //sets the end to null
		size--; //decreases the size
		return temp; //returns temp
	}


	public void addFirst(E e)  { //method to add to the beginning 
		add(0, e);
	}

	public void addLast(E e)  { //method to add to the end
		if(size==0) { //if the size is 0 add to the front
			add(0,e);
		}else {
			add(size, e);
		}
	}

	public E removeFirst() throws IndexOutOfBoundsException { //method to remove the first value 
		if(size==0) {
			return null;
		}
		return remove(0);

	}

	public E removeLast() throws IndexOutOfBoundsException { //method to remove the last value
		if(size==0) {
			return null;
		}
		return remove(size-1);

	}
	
	@Override
	public Iterator<E> iterator() { //method to create a new iterator
		return new ArrayListIterator();
	}

	public Object[] toArray() {
		
		return data;
	}
}
