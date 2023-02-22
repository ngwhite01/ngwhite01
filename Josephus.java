package cs2321;
/**
 * This program takes in an array and an integer and every time a counter reaches that integer it takes that value and moves it to a different array
 * @author Nathan White
 * CS2321
 * R02
 */
public class Josephus{
	
	public String[] order(String[] persons, int k ) {
		String[] output = new String[persons.length]; //creates an output array as big as the input array
		CircularArrayQueue<String> Queue = new CircularArrayQueue<>(persons.length); //creates a new queue object
		for(int i=0;i<persons.length;i++) { //loops through the input array
			Queue.enqueue(persons[i]); //enqueues every value of the inputted array into the queue
		}
		int j=0; //creates a counter j
		while(Queue.size()>1){ //while the size of the queue is greater than 1
			
			for(int i=0;i<k;i++) { //loops up to the inputed int
				Queue.enqueue(Queue.dequeue()); //enqueues the dequeued value
				output[j]=Queue.dequeue(); //sets output at counter j to the dequeued value
				j++; //increases j
			}
		}
		output[j] =Queue.dequeue(); //sets output at counter j to the dequeued value
		return output; //returns the output array
	}	
}
