

#include "Array2D.h"
#include <stdio.h>
#include <float.h>
#include <math.h>
/*
 * Nathan White
 * ngwhite
 * This program holds multiple methods that are used within art.c and process.c.
 */

/**
 *This method prints out ascii characters based on the double values held in the array passed in. It also prints a border around it.
 * @param rows
 * @param cols
 * @param pixels
 */
void array2DPrintASCII(int rows, int cols, double a[rows][cols]){
    printf("+"); //prints corner border character
    for (int i = 0; i < cols; i++) { //loops through columns
        printf("-"); //prints upper border character
    }
    printf("+ \n"); //prints corner border character along with a linespace
    for (int j = 0; j < rows; j++) { //loops through rows
        printf("|"); //prints edge border character
        for (int i = 0; i < cols; i++) { //loops through columns

            if (0.00 <= a[j][i] && a[j][i] < 0.1) { //prints a space if the value is within this range
                printf(" ");

            }
            else if (0.1 <= a[j][i] && a[j][i] < 0.2) { //prints a period if the value is within this range
                printf(".");

            }
            else if (0.2 <= a[j][i] && a[j][i] < 0.3) { //prints a colon if the value is within this range
                printf(":");

            }
            else if (0.3 <= a[j][i] && a[j][i] < 0.4) { //prints a dash if the value is within this range
                printf("-");

            }
            else if (0.4 <= a[j][i] && a[j][i] < 0.5) { //prints an equals sign if the value is within this range
                printf("=");

            }
            else if (0.5 <= a[j][i] && a[j][i] < 0.6) { //prints a plus if the value is within this range
                printf("+");


            }
            else if (0.6 <= a[j][i] && a[j][i] < 0.7) { //prints an asterisk if the value is within this range
                printf("*");


            }
            else if (0.7 <= a[j][i] && a[j][i] < 0.8) { //prints a pound sign if the value is within this range
                printf("#");

            }
            else if (0.8 <= a[j][i] && a[j][i] < 0.9) { //prints a percent sign if the value is within this range
                printf("%%");

            }
            else if (0.9 <= a[j][i] && a[j][i] <= 1.0) { //prints an at sign if the value is within this range
                printf("@");

            }
        }
        printf("| \n"); //prints edge border character along with linefeed
    }
    printf("+"); //prints corner border character
    for (int i = 0; i < cols; i++) { //loops through columns
        printf("-"); //prints edge border character
    }
    printf("+ \n"); //prints corner border character along with linefeed
}

/**
 *This method computes the mean, max, and min values of the double array passed in.
 * @param rows
 * @param cols
 * @param a
 * @return
 */
Array2DStats array2DComputeStats(int rows, int cols, double a[rows][cols]){
    Array2DStats stats; //creates Array2DStats struct stats
    stats.min= DBL_MAX; //sets stats.min to be the max double value
    stats.max = DBL_MIN; //sets stats.max to be the min double value
    double total=0; //creates a total variable
    for (int j = 0; j < rows; j++) { //loops through rows
        for (int i = 0; i < cols; i++) { //loops through columns
            total = a[j][i] + total; //totals the current point value into the total variable
            if (a[j][i] > stats.max) { //checks if the current point is larger than stats.max
                stats.max = a[j][i]; //sets stats.max equal to the current point
            }
            if (a[j][i] < stats.min) { //checks if the current point is smaller than stats.min
                stats.min = a[j][i]; //sets stats.min equal to the current point
            }
        }
    }
    stats.mean = (total / (rows*cols)); //sets stats.mean equal to total divided by the rows times the columns
    return stats; //returns stats
}

/**
 *This method prints off the stats created from the previous method
 * @param stats
 */
void array2DPrintStats(const Array2DStats* stats){
    printf("mean %0.3lf, min %0.3lf, max %0.3f\n", stats->mean, stats->min, stats->max); //prints the mean, min, and max values
}

/**
 *This method flips the array passed in based on its rows.
 * @param rows
 * @param cols
 * @param a
 */
void array2DReverseRows(int rows, int cols, double a[rows][cols]){
    double holder = 0; //creates a holder variable
    for(int j = 0;j<(rows/2);j++){ //loops through half the rows
        for(int i=0;i<cols;i++){ //loops through the columns
            holder= a[j][i]; //sets holder equal to the current position
            a[j][i]=a[(rows-1)-j][i]; //sets the current position equal to the corresponding value at the end of the array
            a[(rows-1)-j][i]=holder; //sets the corresponding value equal to the holder
        }
    }
}

/**
 *This method flips the array passed in based on its columns.
 * @param rows
 * @param cols
 * @param a
 */
void array2DReverseCols(int rows, int cols, double a[rows][cols]){
    double holder = 0; //creates a holder variable
    for(int j = 0;j<rows;j++){ //loops through half the rows
        for(int i=0;i<(cols/2);i++){ //loops through the columns
            holder= a[j][i]; //sets holder equal to the current position
            a[j][i]=a[j][(cols-1)-i]; //sets the current position equal to the corresponding value at the end of the array
            a[j][(cols-1)-i]=holder; //sets the corresponding value equal to the holder
        }
    }
}

/**
 *This method takes in a double array and an array of values to check against. It then sets the double array values equal to the closest value in the other array.
 * @param rows
 * @param cols
 * @param a
 * @param numLevels
 * @param levels
 */
void array2DQuantize(int rows, int cols, double a[rows][cols], int numLevels, double levels[numLevels]){
    double holder=999; //creates holder variable
    int count =0; //creates count
    for (int j = 0; j < rows; j++) { //loops through rows
        for (int i = 0; i < cols; i++) { //loops through columns

            for(int k=0; k < numLevels;k++){ //loops through levels array
                if(fabs(levels[k]- a[j][i])<holder){ //checks if the difference is less than the holder variable
                    holder=fabs(levels[k]-a[j][i]); //sets holder equal to the current difference
                    count =k; //sets count equal to k
                }
            }
            a[j][i]=levels[count]; //sets the current position equal to the least different value from levels
            holder=999; //resets holder

            count=0; //resets counter
        }
    }
}

