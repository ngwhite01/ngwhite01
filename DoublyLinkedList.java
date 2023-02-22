package cs2321;
import java.util.Iterator;
import net.datastructures.Position;
import net.datastructures.PositionalList;

/**
 * This program overrides all the methods for the doubly linked list. 
 * CS2321
 * R02
 * @author Nathan White
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements PositionalList<E> {
	private Node<E> header; //creates a header node
	private Node<E> trailer; //creates a trailer node
	private int size=0; //creates a size variable

	/* 
	 * Node class which should contain element and pointers to previous and next nodes
	 */
	public static class Node<E> implements Position<E> { //node constructor
		private E element; //creates a generic called element
		private Node<E> prev; //creates a node to hold the previous node
		private Node<E> next; //creates a node to hold the next node

		public Node(E e, Node<E> p, Node<E> n) { //node constructor 
			this.element=e; //sets element equal to the argument e
			this.prev=p; //sets previous equal to the argument p
			this.next=n; //sets next equal to the argument n
		}

		@Override
		public E getElement() throws IllegalStateException{ //gets the element in the node
			if(next==null) { //if the next value is null it throws and exception
				throw new IllegalStateException("Does not exist"); 
			}
			return element; //returns the element value
		}
		public Node<E> getPrev() { //gets the previous value
			return prev; //returns the previous value
		}
		public Node<E> getNext() { //gets the next value
			return next; //returns the previous value
		}
		public void setNext(Node<E> n) { //sets the next value
			this.next=n; //sets this.next to n
		}
		public void setPrev(Node<E> p) { //sets the previous value
			this.prev=p; //sets this.prev to p
		}
		public void setElement(E e) { //sets the element
			this.element = e; //sets this.element to e
		}

	}

	/*
	 *Element iterator will return one element at a time  
	 */
	private class ElementIterator implements Iterator<E> {  //iterates through the elements

		Iterator<Position<E>> iterator = new PositionIterator();

		@Override
		public boolean hasNext() { //method to check if there is a next value
			return iterator.hasNext();
		}

		@Override
		public E next() { //method to grab the next value

			return iterator.next().getElement();
		}

	}

	/*
	 * Position iterator will return one Position at a time  
	 */
	private class PositionIterator implements Iterator<Position<E>> { //method that creates an iterator to go through positions
		private Position<E> holder = first(); //creates a holder position to grab the first value
		private Position<E> holder2=null; //creates a holder position
		@Override
		public boolean hasNext() { //method to check if there is a next value
			if(holder!=null) { 
				return true;
			}
			return false;
		}

		@Override
		public Position<E> next() {//method that gets the next value
			holder2=holder;
			holder=after(holder);
			return holder2;
		}

	}

	/*
	 * Position iterator will return one Position at a time  
	 */
	private class PositionIterable implements Iterable<Position<E>> { //method to iterate through positions

		@Override
		public Iterator<Position<E>> iterator() { //method to create an iterator

			return new PositionIterator();
		}

	}
	public DoublyLinkedList() { //create a constructor 
		header= new Node<>(null,null,null); //creates a header node
		trailer= new Node<>(null,header,null); //creates a end node
		header.setNext(trailer); //sets header's next to trailer
	}


	private Node<E> validate(Position<E> p) throws IllegalArgumentException { //method to validate the position of an input
		if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p"); //checks if the input is a node
		Node<E> node = (Node<E>) p; //creates a node to hold the value
		if(node.getNext() == null) { //if there isn't a next value 
			throw new IllegalArgumentException("p is no longer in the list"); //throws an exception
		}
		return node; //returns the new node
	}

	private Position<E> position(Node<E> node) { //creates a position method
		if (node == header || node == trailer) { //if the node is not the header or trailer
			return null; //returns null
		}
		return node; //returns the node
	}


	@Override
	public int size() { //method to return the size

		return size;
	}

	@Override
	public boolean isEmpty() { //checks if the linked list is empty
		if(size==0) { //if the size is 0 return true
			return true;
		}
		return false;
	}

	@Override
	public Position<E> first() { //returns the first value in the linked list
		if(size==0) { //if the size is 0 return null
			return null;
		}
		else { 
			return position(header.getNext()); //returns the position of the first value
		}
	}

	@Override
	public Position<E> last() { //returns the last value
		if(size==0) { //if the size is 0 return null
			return null;
		}
		else {
			return position(trailer.getPrev()); //returns the position of the last value
		}
	}
	//////////////////
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException { //method to grab the position before the position inputed
		if(size==0) { //if the size is 0 return null
			return null;
		}
		Node<E> holder=validate(p); //create a node of the validated position
		return position(holder.getPrev()); //return the position of the previous node
	}


	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException { //method to grab the position after the position inputed
		if(size==0) { //if the size is 0 return null
			return null;
		}
		Node<E> holder=validate(p); //create a node to the validated position
		return position(holder.getNext()); //return the position of the next node
	}

	@Override
	public Position<E> addFirst(E e) { //adds the inputed value to the beginning of the linked list

		return add(e,header,header.getNext()); //calls the add method for the inputed value
	}

	@Override
	public Position<E> addLast(E e) { //adds the inputed value to the end of the linked list


		return add(e,trailer.getPrev(), trailer); //calls the add method for the inputed value
	}


	private Position<E> add(E e, Node<E> p, Node<E> n) { //method to add a value and link it to a previous and next node
		Node<E> newNode = new Node<>(e, p, n);  //creates a new node
		p.setNext(newNode);  //sets the previous node's next to the new node
		n.setPrev(newNode); //sets the next node's previous to the new node
		size++; //adds to size
		return newNode; //returns the new node
	}




	@Override
	public Position<E> addBefore(Position<E> p, E e)
			throws IllegalArgumentException { //a method to add a value before a position inputed
		Node<E> holder=validate(p); //creates a node with the validated position
		return add(e, holder.getPrev(), holder); //calls the add method with the holder node and the inputed value
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) 
			throws IllegalArgumentException { //a method to add a value after a position inputed
		Node<E> holder=validate(p); //creates a node with the validated position
		return add(e, holder, holder.getNext()); //calls the add method with the holder node and the inputed value 
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException { //creates a method to set the value at a position to something

		Node<E> holder= validate(p); //validates the position
		E answer= holder.getElement(); //creates an answer generic to hold the element
		holder.setElement(e); //sets the element of holder to the inputed value
		return answer; //returns the answer generic
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException { //creates a method to remove a value at the position inputed
		Node<E> holder=validate(p); //creates a holder to hold the validated position
		Node<E> prev=holder.getPrev(); //sets the previous value to the node's previous
		Node<E> next=holder.getNext(); //sets the next value to the node's next
		prev.setNext(next); //sets the previous node next to next
		next.setPrev(prev); //sets the next node previous to prev
		size--; //lower size
		E answer= holder.getElement(); //creates an answer genericto hold the element
		holder.setElement(null); //sets the holder element to null
		holder.setNext(null); //sets the holder next to null
		holder.setPrev(null); //sets the holder previous to null
		return answer; //returns answer
	}


	public E removeFirst() throws IllegalArgumentException {
		return remove(header.getNext()); //removes the first value in the linked list
	}

	public E removeLast() throws IllegalArgumentException {
		return remove(trailer.getPrev()); //removes the last value in the linked list
	}

	@Override
	public Iterator<E> iterator() { //method to create an iterator

		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() { //creates an position iterator

		return new PositionIterable();
	}

	@Override
	public Object [] toArray() { //method to create an array out of the linked list
		Node<E> cursor=(Node<E>)first(); //creates a cursor node
		Object[] array=new Object[size()]; //new object array of the size of the linked list
		for(int i=0;i<size();i++) { //iterates through the linked list
			array[i]=cursor.getElement(); //the array at i is set to the element at the current node
			cursor=cursor.getNext(); //goes to the next node
		}
		return array; //returns the new array
	}


}
