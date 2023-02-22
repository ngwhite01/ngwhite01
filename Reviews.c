#include "Reviews.h"       // Gets Reviews typedef and function prototypes
#include "Review.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
/*
 * Nathan White
 * ngwhite
 * This program utilizes the review.c program methods in order to handle multiple different reviews. It initializes and uninitializes an array of reviews as well as searches reviews for certain terms.
 */

/**
 *This method initializes an array of review structs using the method from review.c. It takes in a filename and reviews struct and goes through the file to set certain values such as the size and items in the array.
 * @param reviews
 * @param filename
 * @return
 */
int reviewsInit(Reviews* reviews, const char* filename){
    int counter =0; //initializes a counter
FILE* file = fopen(filename, "r"); //creates a file object
if(file == NULL){ //checks if the file is empty
    return 0; //returns 0
}
    if (fscanf(file, "%d", &reviews->size) < 1) { //checks if the size is less than 1
        return 0; //returns 0
    }
    reviews->items = malloc(sizeof(Review) * reviews->size); //allocates memory for reviews items

    if(reviews->items==NULL){ //checks if the items are empty
        return 0; //returns 0
    }
    for(int i=0;i<reviews->size;i++){ //iterates to the review size
        bool pass = reviewInit(&reviews->items[i],file); //creates a boolean calling the reviewInit method from review.c
        if (pass) counter++; //if it is true increments the counter

    }
fclose(file); //closes the file
file=NULL; //sets the file equal to null
    return counter; //returns counter
}
/**
 *This method deallocates the memory from reviewsInit method
 * @param reviews
 */
void reviewsUninit(Reviews* reviews){
        for(int i=0;i<reviews->size;i++){ //iterates through the review size
            reviewUninit(&reviews->items[i]); //calls the reviewUninit method from reviews.c
        }
free(reviews->items); //deallocates the items
reviews->items=NULL; //sets items equal to null
}

/**
 *This method takes in a word, pointer to a matched value, pointer to an average score value, and an array of reviews. It then checks if the word is in any of the reviews and changes the value of matched to reflect that. It then calculates the average score of those review that include the word.
 * @param reviews
 * @param word
 * @param matched
 * @param avgScore
 */
void reviewsSearch(const Reviews* reviews, const char* word, int* matched, double* avgScore){
        *avgScore = 0.0; //sets avgScore equal to 0
        *matched = 0; //sets matched equal to 0
        for (int i = 0; i < reviews->size; i++) //iterates through the review size
        {
            if (reviewMatches(&reviews->items[i], word)) //checks if the word matches the review at position i
            {
                (*matched)++; //increases matched
                *avgScore += reviews->items[i].score; //sets avgScore equal to avgScore plus the score at position i
            }
        }


        if (*matched > 0) //checks is matched is greater than 0
        {
            *avgScore /= *matched; //sets avgScore equal to avgScore divided by matched
        }
        else
        {
            *avgScore = -1.0; //sets avgScore equal to -1
        }
    }
