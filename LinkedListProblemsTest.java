import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;


public class LinkedListProblemsTest {

	@Before
	public void setUp() throws Exception {

	}

	/*
	Given a sorted linked list, delete all duplicates, keep the first appearance for each element only 
	Case 1:
	Input: 1->1->2->3->3->null
	Output: 1->2->3->null
	 */
	@Test
	public void testDeleteDuplicates1() {

		//Design the test data. For easy representation, we use array to represent the data
		int[] testdata= {1,1,2,3,3};

		//Describe the expected result. 
		//Here I use an array of integer, you could also use another list to represent the data. 
		int[] expected = {1,2,3};


		//Create a sorted list using the data
		ListNode head = new ListNode(testdata[0]);
		ListNode tail = head;
		for (int i=1; i<testdata.length;i++) {
			ListNode tmp = new ListNode(testdata[i]);
			tail.next=tmp;
			tail = tmp;
		}

		//call the method that is being tested
		LinkedListProblems.deleteDuplicates(head);

		//Check the list to see if it matched the expected result.
		ListNode p=head;
		int i = 0;
		while (p!=null & i<expected.length) {
			//check each element in the list is correct
			assertEquals(expected[i], p.val);
			p = p.next;
			i = i+1;
		}

		//check the total number of matches equal to the size of expected array
		assertEquals(i, expected.length);

		//check there is no extra data in the list
		assertEquals(null, p);
	}


	/*
	test case 2: all duplicates
	Input: 1->1->1->1->1->null
	Output: 1->null
	 */
	@Test
	public void testDeleteDuplicates2() {
		//Design the test data. For easy representation, we use array to represent the data
		int[] testdata= {1,1,1,1,1};

		//Describe the expected result. 
		//Here I use an array of integer, you could also use another list to represent the data. 
		int[] expected = {1};


		//Create a sorted list using the data
		ListNode head = new ListNode(testdata[0]);
		ListNode tail = head;
		for (int i=1; i<testdata.length;i++) {
			ListNode tmp = new ListNode(testdata[i]);
			tail.next=tmp;
			tail = tmp;
		}

		//call the method that is being tested
		LinkedListProblems.deleteDuplicates(head);

		//Check the list to see if it matched the expected result.
		ListNode p=head;
		int i = 0;
		while (p!=null & i<expected.length) {
			//check each element in the list is correct
			assertEquals(expected[i], p.val);
			p = p.next;
			i = i+1;
		}

		//check the total number of matches equal to the size of expected array
		assertEquals(i, expected.length);

		//check there is no extra data in the list
		assertEquals(null, p);
	}


	/*
	test case 3: no duplicates
	Input: 1->2->3->4->5->null
	Output: 1->2->3->4->5->null
	 */
	@Test
	public void testDeleteDuplicates3() {
		//Design the test data. For easy representation, we use array to represent the data
		int[] testdata= {1,2,3,4,5};

		//Describe the expected result. 
		//Here I use an array of integer, you could also use another list to represent the data. 
		int[] expected = {1,2,3,4,5};


		//Create a sorted list using the data
		ListNode head = new ListNode(testdata[0]);
		ListNode tail = head;
		for (int i=1; i<testdata.length;i++) {
			ListNode tmp = new ListNode(testdata[i]);
			tail.next=tmp;
			tail = tmp;
		}

		//call the method that is being tested
		LinkedListProblems.deleteDuplicates(head);

		//Check the list to see if it matched the expected result.
		ListNode p=head;
		int i = 0;
		while (p!=null & i<expected.length) {
			//check each element in the list is correct
			assertEquals(expected[i], p.val);
			p = p.next;
			i = i+1;
		}

		//check the total number of matches equal to the size of expected array
		assertEquals(i, expected.length);

		//check there is no extra data in the list
		assertEquals(null, p);

	}
}
