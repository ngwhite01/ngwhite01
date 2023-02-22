import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayProblemsTest {

	@Before
	public void setUp() throws Exception {
	}

	/*
	sortArray CASE 1:
	Input: [5,2,5,3,1]
	Expected Output: [5,2,3,1]
	 */
	@Test
	public void testDeleteDuplicatesArray1() {

		int[] nums= {5,2,5,3,1};
		int[] expected = {5,2,3,1};
		int[] actual = ArrayProblems.deleteDuplicates(nums);
		Assert.assertArrayEquals(expected, actual);

	}

	/*
	sortArray CASE 2:
	Input: [5,1,5,2,0,1,5,1]
	Expected Output: [5,1,2,0]
	 */
	@Test
	public void testDeleteDuplicatesArray2() {

		int[] nums= {5,1,5,2,0,1,5,1};
		int[] expected = {5,1,2,0};
		int[] actual = ArrayProblems.deleteDuplicates(nums);
		Assert.assertArrayEquals(expected, actual);

	}


	/*
	sortArray CASE 3: no duplicates
	Input: [1,2,3,4,5]
	Expected Output: [1,2,3,4,5]
	 */
	@Test
	public void testDeleteDuplicatesArray3() {

		int[] nums= {1,2,3,4,5};
		int[] expected = {1,2,3,4,5};
		int[] actual = ArrayProblems.deleteDuplicates(nums);
		Assert.assertArrayEquals(expected, actual);

	}



	/*
	sortArray CASE 4: all duplicates
	Input: [1,1,1,1,1,]
	Expected Output: [1]
	 */
	@Test
	public void testDeleteDuplicatesArray4() {
		int[] nums= {1,1,1,1,1};
		int[] expected = {1};
		int[] actual = ArrayProblems.deleteDuplicates(nums);
		Assert.assertArrayEquals(expected, actual);

	}

}
