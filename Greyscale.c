#include <stdio.h>
#include <stdlib.h>
/*
 * Nathan White
 * ngwhite
 * This program takes in rgb values as well as ints for the rows and columns of an array. It then converts the rbg values to greyscale values and then prints the new array out.
 */
int main(int argc, char** argv)
{
    double brightness = 1; //creates a brightness variable
    if(argc>1) { //checks if there is an argument for brightness
        brightness = atof(argv[1]); //sets brightness equal to the 1st argument passed in
    }
    int rows=0; //creates rows
    int columns=0; //creates columns
    int red; //creates red int
    int green; //creates green int
    int blue; //creates blue int
    int max =-999; //creates a max value that is extremely small
    int min =999; //creates a min value that is extremely big
    scanf("%d %d", &rows, &columns); //scans in the row and column values
    double greyscale[rows][columns]; //creates the double array to hold the greyscale values
    double greyValue; //creates a double value to hold the grey value
    for(int i=0;i<rows;i++){ //loops through rows
        for(int j=0; j<columns;j++){ //loops through columns
            scanf("%d %d %d", &red, &green, &blue); //scans in the red, green, and blue values
            if(red>max){ //checks if red is greater than max
                max=red; //sets max to red
            }
            if(blue>max){ //checks if blue is greater than max
                max=blue; //sets max to blue
            }
            if(green>max){ //checks if green is greater than max
                max=green; //sets max to green
            }
            if(red<min){ //checks if red is less than min
                min=red; //sets min to red
            }
            if(blue<min){ //checks if blue is less than min
                min=blue; //sets min to blue
            }
            if(green<min){ //checks if green is less than min
                min=green; //sets min to green
            }
            greyValue=(((double)(max+min)/2)/255); //calculates the grey scale value
            greyValue=greyValue*brightness; //sets greyValue equal to greyValue times brightness
            if(greyValue>1){ //checks if greyValue is greater than 1
                greyValue=1; //sets greyValue equal to 1
            }
            if(greyValue<0){ //checks if greyValue is less than 0
                greyValue=0; //sets greyValue equal 0
            }
            greyscale[i][j]=greyValue; //sets the current greyscale position equal to the greyValue
            max=-999; //resets max
            min=999; //resets min
        }
    }
    printf("%d %d\n", rows, columns); //prints the rows and columns
    for(int i=0;i<rows;i++) { //loops through the rows
        for (int j = 0; j < columns; j++) { //loops through the columns
            printf("%0.3f ", greyscale[i][j]); //prints out the greyscale value at the current position
        }
        printf("\n"); //prints linefeed
    }
}