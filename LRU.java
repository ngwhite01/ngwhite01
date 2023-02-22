package cs2321;

import cs2321.DoublyLinkedList.Node;
import net.datastructures.*;

/*
 * The design of this program is related to Cache replacement policy in OS 
 * https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU)
 * 
 * LRU: Discards the least recently used items first.
 * 
 * Let's say we have a online store and would like to keep a list of certain number of customers
 * who visited our site from the most recent to the least recent.  
 * 
 * For example: 
 * 
 *  Let's say K=4, which means we only keep the most recent 4 customers. 
 *  And the visiting sequence of the customers: A B C D E D F. Each letter represent a customer's name.
 *
 *  The recent list will change as customers coming:
 *   
 *   A          -- A is the first customer
 *   B A        -- B is not on the list and B's visit to use is sooner than A's
 *   C B A      -- C is not on the list and D's visit to use is sooner than B's
 *   D C B A    -- D is not on the list and D's visit to use is sooner than C's
 *   E D C B    -- E is not on the list. But The list is full. 
 *                 Kick out A as it is the least recent one. 
 *                 Add E to the most recent one. 
 *              
 *   D E C B    -- D is on the list already.  D's place is moved from second to the first. 
 *   
 *   F D E C    -- F is not on the list. The list is full.
 *                 Kick out B and put F as the most recent one 
 *   
 *  For simplicity we only keep the customer name in the list. 
 *  But to make the list useful, we will store some other information associated with the customer.  
 * 
 */

/**
 * This program takes in arguments and then makes a linked list based on the least recent unit. It has a limit and the least used value gets kicked out once a new one comes.
 * CS2321
 * R02
 * @author Nathan White
 *
 */
public class LRU {
	DoublyLinkedList<String> Cache; //linked list
	int maxValue; //int to hold the max value
	Node<String> holder; //holder node
	
	/* Keep up to K customers who visits us recently from the most recent to the least recent. */
	public LRU(int K) { //constructor for LRU
		maxValue=K; //constructor for max value
		Cache=new DoublyLinkedList<>(); //creates new double linked list 
	}

	/* customer visits us, one at a time */
	public void visit(String customer) { //takes in new customer
		if(Cache.size()==0) { //checks if the size of the linked list is 0
			Cache.addFirst(customer); //adds the customer to the empty list
			holder=(Node<String>) Cache.first(); //sets holder to the first value in the linked list
		}else { //if the size of the linked list is greater than 0
			for(int i=0;i<Cache.size();i++) { //iterates through the linked list
				if(holder.getElement().equals(customer)) { //element equals an element in the linked list
					Cache.remove(holder); //removes the holder node from the linked list
					break; //breaks out if it removes
				}
				holder=holder.getNext(); //goes to the next value in the linked list
			}
			
			Cache.addFirst(customer); //adds to the front 
			holder=(Node<String>) Cache.first(); //resets the holder value to the 1st value in the linked list
			if(Cache.size()>maxValue) { //checks if the size of the linked list is greater than the max value
				Cache.removeLast(); //removes from the end if it is over the max
			}
		}
	}


	/* return the most recent K customers */
	public PositionalList<String> getList() {

		return Cache; //returns the linked list
	}


}
