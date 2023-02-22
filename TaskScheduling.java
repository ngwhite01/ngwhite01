package cs2321;

public class TaskScheduling {
	/**
	 * Goal: Perform all the tasks using a minimum number of machines. 
	 * 
	 *       
	 * @param tasks tasks[i][0] is start time for task i
	 *              tasks[i][1] is end time for task i
	 * @return The minimum number or machines
	 */
	/*
	 * Nathan White
	 * ngwhite
	 * R02
	 * This program finds the minimum number of machines needed in order to complete the tasks given to it
	 */
	/**
	 * this method takes in a 2d array of tasks and determines the minimum number of machines in order to complete all of them
	 * @param tasks
	 * @return
	 */
	public static int NumOfMachines(int[][] tasks) {

		HeapAPQ<Integer, Integer> starttime; //creates a priority queue to hold the start times
		starttime = new HeapAPQ<Integer, Integer>(); 
		HeapAPQ<Integer, Integer> availability; //create a priority queue to handle availability
		availability = new HeapAPQ<Integer, Integer>();
		for(int i=0;i<tasks.length;i++) { //sets the key of the starttime queue equal to the start time and the value equal to the endtime
			starttime.insert(tasks[i][0], tasks[i][1]); //inserts every task
		}

		while(!starttime.isEmpty()) { //while there are tasks
			if(availability.isEmpty()|| availability.min().getKey()> starttime.min().getKey()) { //checks if there are no available machines
				availability.insert(starttime.removeMin().getValue(), null); //creates a new machine to handle that task
			}else { //if there are available machines
				availability.replaceKey(availability.min(), starttime.removeMin().getValue()); //replaces the start time and end time of a previous machine
			}
		}

		return availability.size(); //returns the size of the available queue
	}
}
