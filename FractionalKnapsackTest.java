package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionalKnapsackTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testMaximumValue() {
		int[][] arr  = {{10, 60}, {20, 100}, {30, 120}};
		int W = 50;
		double expected = 240;
		assertEquals(expected, FractionalKnapsack.MaximumValue(arr, W),0.001);
	}


	@Test
	public void test2MaximumValue() {
		int[][] arr  = {{10, 200}, {50, 100}, {30, 120}};
		int W = 70;
		double expected = 380;
		assertEquals(expected, FractionalKnapsack.MaximumValue(arr, W),0.001);
	}



	@Test
	public void test3MaximumValue() {
		int[][] arr  = {{10, 50}, {20, 100}, {30, 180}};
		int W = 30;
		double expected = 180;
		assertEquals(expected, FractionalKnapsack.MaximumValue(arr, W),0.001);
	}


	@Test
	public void test4MaximumValue() {
		int[][] arr  = {{20, 50}, {20, 100}, {30, 120}};
		int W = 40;
		double expected = 180;
		assertEquals(expected, FractionalKnapsack.MaximumValue(arr, W),0.001);
	}



	@Test
	public void test5MaximumValue() {
		int[][] arr  = {{10, 50}, {10, 100}, {10, 150}};
		int W = 30;
		double expected = 300;
		assertEquals(expected, FractionalKnapsack.MaximumValue(arr, W),0.001);
	}
}
