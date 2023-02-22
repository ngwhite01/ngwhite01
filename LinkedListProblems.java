/**
 * 
 * @author Nathan White
 * CS2321
 * R02
 * This program deletes duplicates from a linked list.
 */
public class LinkedListProblems {


	public static void deleteDuplicates(ListNode head) {

		ListNode current= head.next; //created a node to hold the next node 
		ListNode previous =head; //created a node to hold the current head value

		while(current!=null) { //makes sure current exists

			if(current.val==previous.val) { //if the next is equal to the current
				previous.next=current.next; //sets the previous nodes pointer to the next farthest node
			}

			else { //if the next isn't equal to the current
				previous=current; //sets the previous node equal to current
			}

			current=current.next; //iterates to the next value forward
		}

	}
}
