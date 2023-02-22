#include "hmalloc.h"
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
/*You may include any other relevant headers here.*/

/**
 * Nathan White
 * CS 3411
 *
 */
/*Add additional data structures and globals here as needed.*/
void *free_list = NULL;
typedef struct node{
    int length;
    int next;
}Free_node;
struct node *head = NULL;



/* traverse
 * Start at the free list head, visit and print the length of each
 * area in the free pool. Each entry should be printed on a new line.
 */
void traverse(){
    struct node *ptr=head;
    int counter =0;
    while(ptr != NULL){

        printf("Index: %d, Address: %p, Length: %d\n", counter, ptr, ptr->length);
        ptr=(struct node*)((char*)(ptr))+ptr->next;
        counter++;
        if(ptr->next==0){
            break;
        }
    }

   /* Printing format:
	 * "Index: %d, Address: %08x, Length: %d\n"
	 *    -Index is the position in the free list for the current entry. 
	 *     0 for the head and so on
	 *    -Address is the pointer to the beginning of the area.
	 *    -Length is the length in bytes of the free area.
	 */
}

/* hmalloc
 * Allocation implementation.
 *    -will not allocate an initial pool from the system and will not 
 *     maintain a bin structure.
 *    -permitted to extend the program break by as many as user 
 *     requested bytes (plus length information).
 *    -keeps a single free list which will be a linked list of 
 *     previously allocated and freed memory areas.
 *    -traverses the free list to see if there is a previously freed
 *     memory area that is at least as big as bytes_to_allocate. If
 *     there is one, it is removed from the free list and returned 
 *     to the user.
 */
void *hmalloc(int bytes_to_allocate){
    if(free_list!=NULL) {
        Free_node* holder= (Free_node*) free_list;
        Free_node* last = NULL;
        while(holder->next!=NULL){

            if(holder->length>=bytes_to_allocate){
                last->next=last->next+holder->next;
                if(holder->next==0){
                    last->next=0;
                }
            return (holder+1);
            }else {
                last = holder;
                holder = (struct node *) ((char *) (holder)) + holder->next;
            }

        }

    }
    int length = bytes_to_allocate;
    int offset = 0;
    int *memory = (int*) sbrk(4);
    *memory = length;
    int *memory2 = (int*)sbrk(4);
    *memory2 = offset;
    void *memory3 = sbrk(bytes_to_allocate);
    return (memory3);
}
//length,offset,bytes to allocate

/* hcalloc
 * Performs the same function as hmalloc but also clears the allocated 
 * area by setting all bytes to 0.
 */
void *hcalloc(int bytes_to_allocate){
char* holder= (hmalloc(bytes_to_allocate));
for(int i=0;i<bytes_to_allocate;i++){
    holder[i]='\0';
}
return holder;
}



/* hfree
 * Responsible for returning the area (pointed to by ptr) to the free
 * pool.
 *    -simply appends the returned area to the beginning of the single
 *     free list.
 */
void hfree(void *ptr){
    if(free_list==NULL){
        free_list=(ptr-8);
    }else {
        void *holder = free_list;
        free_list = (ptr - 8);
        ((Free_node *) free_list)->next = (holder - free_list);
    }
}

/* For the bonus credit implement hrealloc. You will need to add a prototype
 * to hmalloc.h for your function.*/

/*You may add additional functions as needed.*/
