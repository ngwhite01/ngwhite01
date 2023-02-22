package cs2321;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListStackTest {

	@Test
	void test() {
		LinkedListStack<Integer> stack = new LinkedListStack<>();
		stack.push(10);
		int holder = stack.pop();
		assertEquals(holder, 10);
	}
	
	@Test
	void test2() {
		LinkedListStack<Integer> stack = new LinkedListStack<>();
		stack.push(10);
		stack.push(10);
		stack.push(10);
		int holder = stack.size();
		assertEquals(holder, 3);
	}
}
