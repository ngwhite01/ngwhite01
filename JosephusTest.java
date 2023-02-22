package cs2321;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JosephusTest {

	@Test
	void test() throws Exception {
		
		String[] input = {"A", "B", "C", "D", "E", "F"};
		int input2 =2;
		String[] expected = {"B", "D", "F", "C", "A", "E"};
		String[] actual= Josephus.order(input, input2);
		for(int i=0;i<input.length;i++) {
		assertEquals(actual[i], expected[i]);
		}
	}

	@Test
	void test2() throws Exception{
		
		String[] input = {"A", "B", "C", "D", "E", "F"};
		int input2 =1;
		String[] expected = {"A", "B", "C", "D", "E", "F"};
		String[] actual;
		actual=Josephus.order(input, input2);
		for(int i=0;i<input.length;i++) {
		assertEquals(actual[i], expected[i]);
		}
	}

	@Test
	void test3() throws Exception{
		
		String[] input = {"A", "B", "C", "D", "E", "F"};
		int input2 =3;
		String[] expected = {"C", "F", "D", "B", "E", "A"};
		String[] actual;
		actual=Josephus.order(input, input2);
		for(int i=0;i<input.length;i++) {
		assertEquals(actual[i], expected[i]);
		}
	}
	

}
