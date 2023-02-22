package cs2321;

/**
 * @author:
 *
 */
public class FractionalKnapsack {


	/**
	 * Goal: Choose items with maximum total benefit but with weight at most W.
	 *       You are allowed to take fractional amounts from items.
	 *       
	 * @param items items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
	 * @param knapsackWeight
	 * @return The maximum total benefit. Please use double type operation. For example 5/2 = 2.5
	 * 		 
	 */
	
	
	/*
	 * Nathan White
	 * ngwhite
	 * R02
	 * This program finds the maximum value of items that can fit in the knapsack
	 */
	/**
	 * this method returns the maximum value of items based off a fixed weight and items inputed
	 * @param items
	 * @param knapsackWeight
	 * @return
	 */
	public static double MaximumValue(int[][] items, int knapsackWeight) {
		int weight=0; //create an int to hold the weight
		double benefit=0; //create an int to hold the benefit value
		double total=0; //create a double for the total value
		double value; //create a double to hold the value


		HeapAPQ<Double, Integer> queue; //creates a priority queue 
		queue = new HeapAPQ<Double, Integer>();
		for(int i=0;i<items.length;i++) { //iterates through the items array and sets the benefit/weight equal to value
			value=(items[i][1]/items[i][0]);
			queue.insert(value*-1, items[i][0]); //sets the key equal to value and the value to weight
		}
		while(knapsackWeight>0) { //while there is still available space in the sack
			benefit= (queue.min().getKey()*queue.min().getValue()*-1); //set benefit equal to the largest key value * weight
			weight=queue.min().getValue(); //grabs the weight from the highest value item
			if(weight>knapsackWeight) { //if a fraction of an item is needed
				double avg =((double)knapsackWeight/(double)weight); //create the fraction of what is left over what the item weights
				benefit=benefit*avg; //set the benefit equal to the benefit times that fraction
				weight = knapsackWeight; //set the weight equal to the remaining weight
			}
			total=total+benefit; //set total equal to itself plus benefit
			knapsackWeight=knapsackWeight-weight; //sets knapsackweight equal to knapsackweight minus weight
			queue.removeMin(); //removes the highest efficiency item each iteration through the while
		}
		return total; //returns the total after there is no room left
	}
}


