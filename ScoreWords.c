#include "Reviews.h"

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
/*
 * Nathan White
 * ngwhite
 * This program calls the method from reviews and it prints off how many reviews were loaded in from a inputted text file as well as how many reviews include the terms passed in as arguments.
 */
int main(int argc, char** argv){
if(argc<3){ //checks for the correct number of arguments
    printf("Usage: ScoreWords <reviews file> <word1> [word2] ..."); //prints a usage statement
    return 0; //returns 0
}
    Reviews reviews; //initializes reviews
    int loaded = reviewsInit(&reviews, argv[1]); //initializes an int loaded that has the value of the reviews size
if(loaded<=0){ //checks if loaded is less than or equal to 0
printf("Failed to load reviews from '%s'",argv[1]); //prints a message showing that it failed to load reviews from the filename
    return 0; //returns 0
}
printf("Loaded %d reviews\n", loaded); //prints the number of loaded reviews

for(int i=2;i<argc;i++){ //goes through the arguments starting at the 2nd
    int matched=0; //sets matched to 0
    double avgscore=0; //sets avgscore to 0
    reviewsSearch(&reviews,argv[i],&matched, &avgscore); //calls reviews search with the filename, reviews object, matched, and avgscore
    if(avgscore != -1) printf("'%s' in %d reviews, avg score %.3f\n", argv[i], matched, avgscore); //prints the statement if avgscore does not equal -1
    else printf("'%s' in %d reviews\n", argv[i], matched); //prints this statement if avgscore is -1
    }
reviewsUninit(&reviews); //calls reviewsUninit from reviews.c to deallocate the memory from all the structs
}