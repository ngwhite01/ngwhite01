package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TaskSchedulingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNumOfMachines() {
		int[][] arr  = {{1, 5}, {1, 3}, {2, 5}, {3, 7}, {4, 7}, {6, 9}, {5,8}};
		int expected=4;
		assertEquals(expected, TaskScheduling.NumOfMachines(arr));
	}

	@Test
	public void test2NumOfMachines() {
		int[][] arr  = {{1, 4}, {1, 3}, {2, 5}, {3, 7}, {4, 7}, {6, 9}, {7,8}};
		int expected=3;
		assertEquals(expected, TaskScheduling.NumOfMachines(arr));
	}

	@Test
	public void test3NumOfMachines() {
		int[][] arr  = {{1, 2}, {2, 3}, {3, 5}, {5, 7}, {4, 7}, {6, 9}, {7,8}};
		int expected=3;
		assertEquals(expected, TaskScheduling.NumOfMachines(arr));
	}

	@Test
	public void test4NumOfMachines() {
		int[][] arr  = {{1, 2}, {1, 3}, {1, 5}, {5, 7}, {4, 7}, {7,8}};
		int expected=3;
		assertEquals(expected, TaskScheduling.NumOfMachines(arr));
	}

	@Test
	public void test5NumOfMachines() {
		int[][] arr  = {{3, 5}, {4, 6}, {5, 6}, {6, 8}, {4, 8}, {6, 9}, {9,10}};
		int expected=3;
		assertEquals(expected, TaskScheduling.NumOfMachines(arr));
	}
}
