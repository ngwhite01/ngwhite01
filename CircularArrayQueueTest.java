package cs2321;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CircularArrayQueueTest {

	@Test
	void test() {
		CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);
		queue.enqueue(15);
		int holder = queue.dequeue();
		assertEquals(holder, 15);
	}
	
	@Test
	void test2() {
		CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);
		queue.enqueue(15);
		queue.enqueue(15);
		queue.enqueue(15);
		queue.enqueue(15);
		queue.enqueue(15);
		queue.enqueue(15);
		queue.enqueue(15);
		queue.enqueue(15);
		queue.enqueue(15);
		queue.enqueue(15);
		int holder = queue.size();
		assertEquals(holder, 10);
	}
	
}
