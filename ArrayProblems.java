/**
 * 
 * @author Nathan White
 * CS2321
 * R02
 * This program deletes duplicates from an array and then outputs a new cleared array.
 */
public class ArrayProblems {


	public static int[] deleteDuplicates(int[] nums) {

		int holder = 0; //creates a holder

		for(int i=0;i<nums.length;i++) { //iterates through the array


			boolean duplicate =false; //creates a boolean to check if there is a duplicate

			for(int j=0;j<holder;j++) { //loops through up to holder
				if(nums[j]==nums[i]) { //checks if the current and next values are equal
					duplicate = true; //makes duplicate true
					break; //breaks out of the for loop
				}
			}
			if(!duplicate) { //checks if it is not a duplicate
				nums[holder]=nums[i]; //sets the holder value equal to the current value
				holder++;
			}

		}
		int [] output= new int[holder]; //creates a new array to fit a copy of the previous array

		for(int i=0;i<holder;i++) { //loops through up to holder
			output[i]=nums[i]; //sets output to the corresponding value in nums

		}

		return output; //returns output array

	}

}
