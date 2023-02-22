#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
/*
 * Nathan White
 * ngwhite
 * This program takes in a test file simulates a cpu based on the behaviors given in the file. It will add jobs, print jobs, and run cycles according to whatever the file dictates.
 */
// Stores information about a particular job on the system
// Do NOT modify!
typedef struct
{
    int id;                 // Unique numeric ID for this job
    int startTime;          // Time the job entered the system
    int totalCycles;        // Total CPU cycles required by this job
    int remainingCycles;    // How many cycles remaining before it is done
} Job;

// Node in the doubly linked list
// Do NOT modify!
typedef struct node
{
    Job job;                // Data about this particular job
    struct node* next;      // Pointer to the next node in the doubly linked list
    struct node* prev;      // Pointer to the prev node in the doubly linked list
} Node;

// Tracks information about the state of the CPU scheduler
// Do NOT modify!
typedef struct
{
    Node* head;             // Current head of the linked list
    int time;               // Current time of the simulation
} State;

// Executes one cycle of the CPU scheduling simulation.
// The current job at the head of the linked list gets one cycle of CPU time.
//
// If the current job is done, it should be removed from the list and the function prints:
//   t=A, B done, elapsed C, idle D%\n
//   where A is the current time of the simulation
//         B is the job ID of the completed job
//         C is how many cycles have elapsed since the job was first added (including this cycle)
//         D is the percentage of cycles in which this job was idle (some other job was executing)
// 
// The head of the list should be advanced to the next node.
// The current time of the simulation should be advanced by one.
//
// Returns true if a job used the cycle, false if there were no available jobs.

/**
 * This method passes in the state and then goes through one cycle of the cpu. It checks the remaining cycles for the current job, it incriments the time, and it prints out a message once each job has no more remaining cycles.
 * @param state
 * @return
 */
bool executeCycle(State* state){
    if(state->head == NULL) { //checks if the current head is null
        state->time++; //increments time
        return false; //returns false
    }
    state->head->job.remainingCycles--; //removes 1 from the current remaining cycles
        if (state->head->job.remainingCycles == 0) { //checks if the remaining cycles is 0
            int elapsed = (state->time - state->head->job.startTime + 1); //calculates the elapsed time
            printf("t=%d, %d done, elapsed %d, idle %0.2f%%\n", state->time, state->head->job.id, elapsed,
                   (double) (elapsed - state->head->job.totalCycles) / elapsed * 100.00); //prints the time, job id, elapsed time, and idle time
            Node *next = NULL; //creates a node called next

            if (state->head->next != state->head) { //checks to see if the next head does not equal the current
                state->head->prev->next = state->head->next; //sets the previous next equal to the next
                state->head->next->prev = state->head->prev; //sets the next previous equal to the previous
                next = state->head->next; //sets the next node equal to the next head
                free(state->head); //frees the current head
                state->head = next; //sets the current head equal to next
            } else { //if the next head equals the current head
                free(state->head); //frees the current head
                state->head = NULL; //sets it equal to null
            }

        }
        else{ //if there are remaining cycles
            state->head = state->head->next; //sets the current head equal to the next head

        }
            state->time++; //increments time
            return true; //returns true
}

// Adds a new job to the linked list.
// The job is added as the node before the current head of the list.
// Thus the new job has to wait until all existing jobs get scheduled.
//
// Returns true on succss, false otherwise (e.g. failure to allocate memory).

/**
 *This method passes in the state, job id, and job time in order to add it to the linked list. It implements all the parts of the job struct through the inputs of this method.
 * @param state
 * @param jobID
 * @param jobTime
 * @return
 */
bool addJob(State* state, int jobID, int jobTime)
{
        Job job; //creates a job struct
        job.id=jobID; //sets the job id integer equal to jobID
        job.totalCycles=jobTime; //sets the totalCycles integer equal to jobTime
        job.startTime = state->time; //sets the startTime integer equal to state time
        job.remainingCycles = jobTime; //sets the remaining cycles equal to jobTime
        Node* node = malloc(sizeof(Node)); //allocates memory for a Node struct
    if(node == NULL) { //checks to see if node is null
        return false; //returns false
    }
    node->job = job; //sets the node job equal to job
    node->next = node; //sets the next node equal to node
    node->prev = node; //sets the previous node equal to node
        printf("t=%d, added %d\n", state->time, jobID); //prints off the id and the time in which its added
    if(state->head == NULL) { //checks if the current head is null
        state->head = node; //sets it equal to node
    }
    node->next = state->head; //sets the next node equal to the current head
    node->prev = state->head->prev; //sets the previous node equal to the previous head
    state->head->prev->next = node; //sets the current head equal to node
    state->head->prev = node; //sets the previous head equal to node
    return true; //returns true
}

// Prints out the jobs currently in the linked list in order of next execution. 
// Output format is:
//   t=A, print B:C D:E ...
//   where A is the current time of the simulation
//         B is the ID of the job at the head of the list
//         C is the remaining cycles for the job at the head of the list
//         D is the ID of the next job to be scheduled
//         E is the remaining cycles of the next job to be schedule
//         ... and so on for all jobs in the list

/**
 *This method takes in the state and then prints off a message depending on how many heads are in the linked list.
 * @param state
 */
void printJobs(const State* state) {
    printf("t=%d, print ", state->time); //prints off the time
    if (state->head != NULL) { //checks if the current head does not equal null
        Node *node = state->head; //sets the node node equal to the current head
        do {
            printf("%d:%d ", node->job.id, node->job.remainingCycles); //prints off the job id and remaining cycles
            node = node->next; //goes to the next head
        } while (node != state->head); //does this while the current node does not equal the starting head
    }
    printf("\n"); //prints a linefeed
}

// Remove all remaining jobs from the linked list, deallocating the associated memory.
// Also results in the head of the list being changed to NULL.
//
// Returns the number of removed jobs (0 if list is empty).

/**
 *This method frees all the jobs left in the linked list
 * @param state
 * @return
 */
int freeJobs(State* state)
{
    int counter = 0; //creates a counter
    Node* current = state->head; //sets node current equal to the current head
    if(current == NULL){ //checks if the node current is equal to null
        return 0; //returns 0
    }
    do {
        Node* next = current->next; //creates node next that is equal to the current next
        free(current); //frees current
        current = next; //sets current equal to next
        counter++; //increments counter
    }while(current != state->head); //does this while current does not equal the original head
    state->head=NULL; //sets the original head equal to null
    return counter; //returns counter

}

// Main program that simulates scheduling jobs on a CPU.
// Input is via standard input.
// Input consists of a string command followed by 0 or more integer arguments depending on the command.
// Do NOT modify the main function!
int main(void)
{
    // Disable standard output buffering, this makes for better student feedback if program crashes
    setbuf(stdout, NULL);

    // State struct keeps track of the head of the linked list and the current time of the simulation
    State state;
    state.head = NULL;
    state.time = 0;

    // String literals that are used in the input
    const char* COMMAND_RUN = "run";
    const char* COMMAND_ADD = "add";
    const char* COMMAND_PRINT = "print";

    // Variables used to read in data depending ont he command
    char command[100];
    int cycles = 0;
    int id = 0;
    int jobTime = 0;

    // Used to keep track of how often the CPU was busy or idle
    int busyCycles = 0;
    int idleCycles = 0;

    while (scanf("%99s", command) == 1)
    {
        if (strcmp(command, COMMAND_RUN) == 0)
        {
            // After the run command should come a positive number of cycles to execute
            if ((scanf("%d", &cycles) == 1) && (cycles > 0))
            {
                for (int i = 0; i < cycles; i++)
                {
                    if (executeCycle(&state))
                    {
                        busyCycles++;
                    }
                    else
                    {
                        idleCycles++;
                    }
                }
            }
            else
            {
                printf("Invalid run command\n");
                return 1;
            }
        }
        else if (strcmp(command, COMMAND_ADD) == 0)
        {
            // After the add command should come the process ID and a positive job time
            if ((scanf("%d %d", &id, &jobTime) == 2) && (jobTime > 0))
            {
                if (!addJob(&state, id, jobTime))
                {
                    printf("Failed to add job\n");
                    return 1;
                }
            }
            else
            {
                printf("Invalid add command\n");
                return 1;
            }
        }
        else if (strcmp(command, COMMAND_PRINT) == 0)
        {
            printJobs(&state);
        }
        else
        {
            printf("Unknown command = %s\n", command);
            return 1;
        }
    }

    // Compute how many cycles the CPU was busy
    double utilization = (double) busyCycles / (idleCycles + busyCycles) * 100.0;
    printf("t=%d, end, CPU busy %.2f%%\n", state.time, utilization);

    // Deallocate any remaining jobs in the system
    printf("t=%d, freed %d remaining jobs\n", state.time, freeJobs(&state));

    return 0;
}
