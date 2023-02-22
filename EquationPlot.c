#include <stdio.h>
#include <stdlib.h>
#include <math.h>
/*
 * Nathan White
 * ngwhite
 * This program takes in arguments of xrange yrange a0 etc. and then plots the equation of that polynomial equation.
 */
int main(int argc, char** argv) {
    if (argc < 4) { //checks to see if there are enough arguments
        printf("Usage: EquationPlot <x_range> <y_range> <a_0> [a_1] ... [a_k]\n"); //prints a usuage statement as there aren't enough arguments
        return 0; //ends program
    }
    int xrange = atoi(argv[1]); //reads in xrange
    int yrange = atoi(argv[2]); //reads in yrange
    double a0 = atof(argv[3]); //reads in a0 value
    int size = argc - 1; //size of array argv
    double y = 0; //value of y
    double holder = 0; //holder for calculating y later on
    int x[((2 * xrange)+1)]; //array of x values
    int plot[((2 * xrange)+1)][((2 * yrange)+1)]; //array holding all the values for the plotted equation
    for (int k = 0; k < ((2 * xrange) + 1); k++) { //loop to iterate through all x values
        x[k] = (-xrange + k); //calculates and then stores x value for each position in the range
    }
    for (int j = 0; j < ((2 * yrange)+1); j++) { //loops through all y values for array
        for (int i = 0; i < ((2 * xrange)+1); i++) { //loops through all x values for array
            if (i==xrange && yrange==j) { //checks to see if your in the midpoint of the array
                plot[i][j] = "+"; //sets character at that point to +
            } else if (i == xrange) { //checks to see if you are in the center of your array x wise
                plot[i][j] = "|"; //sets character at that point to |
            } else if (j == yrange) { //checks to see if you are in the center of your array y wise
                plot[i][j] = "-"; //sets character at that point to -
            } else { //if none of the earlier ones are true it just makes the character a space
                plot[i][j] = " ";
            }
        }
    }
    printf("y = %0.2f", a0); //prints off a0
    if (argc > 4) { //checks if there is more to the equation
        for (int i = 1; i < (size - 2); i++) { //loops through starting at argv[4]
            printf(" + %0.2fx^%d", atof(argv[i + 3]), i); //prints argument plus its corresponding coefficient
        }
    }
    printf("\n"); //prints new line

    if (argc < 5) { //checks to see if there is only an a0 value passed in and no a1 etc.
        if ((-yrange+1) < a0 && a0< (yrange+1)) { //checks to see if a0 is within the graph range for y
            for (int i = 0; i < ((2 * xrange)+1); i++) { //loops through all x values for array
                plot[i][(yrange - (int) a0)] = "o"; //changes the character an o at the position 0 - a0 due to it being an array indexing at 0
            }
            for (int j = 0; j < ((2 * yrange)+1); j++) { //loops through all y values for array
                for (int i = 0; i < ((2 * xrange)+1); i++) { //loops through all x values for array
                    printf("%s", plot[i][j]); //prints off the character at the current position
                }
                printf("\n"); //prints new line
            }
            return 0; //ends program
        } else { //if a0 is outside the range of the graph
            for (int j = 0; j < ((2 * yrange)+1); j++) { //loops through all y values for array
                for (int i = 0; i < ((2 * xrange)+1); i++) { //loops through all x values for array
                    printf("%s", plot[i][j]); //prints off the character at the current position
                }
                printf("\n"); //prints new line
            }
            return 0; //ends program
        }
    }
    if(argc<6) { //checks to see if there are arguments up to a1
        y=a0; //sets y equal to a0
        for (int i = 0; i < ((2 * xrange) + 1); i++) { //loops through all x values for array
            holder = (atof(argv[4]) * x[i]); //calculates a1 * the current x value
            y = round(y+holder); //adds a0 to the holder value
            if((yrange - y)<0 || (yrange - y)>=((2*yrange)+1)){ //checks to see if the y value is outside the range of the graph
                y=a0; //resets y for the next loop
            }else{
                plot[i][(int) (yrange - y)] = "o"; //sets the current x value and calculated y value position to o
                y=a0; //resets y for the next loop
            }
        }
        for (int j = 0; j < ((2 * yrange)+1); j++) { //loops through all y values for array
            for (int i = 0; i < ((2 * xrange)+1); i++) { //loops through all x values for array
                printf("%s", plot[i][j]); //prints off the character at the current position
            }
            printf("\n"); //prints new line
        }
        return 0; //ends program
    }
    else {
        y=a0; //sets y = a0
        for (int i = 0; i < ((2 * xrange) + 1); i++) { //loops through all x values for array
            for (int j = 4; j <= (size); j++) { //goes from argv[4] * current x^1 etc for argv[5]*x^2
                holder = (atof(argv[j]) * pow(x[i], (j - 3))); //sets holder equal to argv[4] * current x^1 etc to argv[5]*x^2 etc.
                y = (y+holder); //sets y=current y value plus holder
            }
            y = round(y); //rounds y
            if((yrange - y)<0 || (yrange - y)>=((2*yrange)+1)){ //checks to see if its outside the graph range
                y=a0; //sets it equal to a0 for future loops
            }else{
                plot[i][(int) (yrange - y)] = "o"; //sets the current x value and calculated y value position to o
                y=a0; //sets it equal to a0 for future loops
            }
        }
        for (int j = 0; j < ((2 * yrange)+1); j++) { //loops through all y values for array
            for (int i = 0; i < ((2 * xrange)+1); i++) { //loops through all x values for array
                printf("%s", plot[i][j]); //prints off the character at the current position
            }
            printf("\n"); //prints new line
        }
        return 0; //ends program
        }
    }