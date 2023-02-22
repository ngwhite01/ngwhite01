#include <stdio.h>
#include <stdlib.h>
#include <math.h>
/*
 * Nathan White
 * ngwhite
 *This program takes in an x value, and a0 value as well as further a values and then calculates the y value given based off those arguments.
 */
int main(int argc, char** argv)
{
    if (argc < 3){ //checks to see if there are enough arguments
        printf("Usage: EquationCalc <x_value> <a_0> [a_1] ... [a_k]\n"); //prints a usuage statement as there aren't enough arguments
        return 0; //ends program
    }
    double x = atof(argv[1]); //reads in x value
    double a0= atof(argv[2]); //reads in a0
    int size = argc -1; //calculates size of argments array
    double y= 0; //initializes y value
    double holder = 0; //initializes a holder double
    if(argc==3){ //checks if only x and a0 are passed in
        y=a0; //sets y equal to a0
        printf("%0.2f\n", y); //prints off y
        return 0; //ends program
    }
    if(argc<4){ //if there is only a0 and a1
        holder= (atof(argv[3])*x); //sets holder equal to a1*x
        y=holder+a0; //sets y equal to holder + a0
    }else //if there are more than just a0 and a1
        {
        for(int j=3;j<=(size);j++) { //iterates through the size of the array of arugments
            holder = (atof(argv[j]) * pow(x, (j-2))); //sets holder equal to the current a value * x to the power of 1,2,3,etc...
            y = y + holder; //sets y equal to current y value plus holder
        }
        y=y+a0; //sets y equal to current y value plus a0
    }
    printf("%0.2f\n", y); //prints the y value
}
