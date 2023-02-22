
#include "Array2D.c"
#include <stdio.h>
#include <string.h>
/*
 * Nathan White
 * ngwhite
 * This program takes in an input and output file as well as commands. It then reads in the file info from the input, completes any commands listed, and then outputs the new information to the output file.
 */
int main(int argc, char** argv) {
    if (argc < 3) { //checks for the correct number of arguments
        printf("Usage: Process <in file> <out file> [command 1] ..."); //prints out a usage message
        return 0; //returns 0
    }

    FILE* in = fopen(argv[1], "r"); //creates a file object with the input file name
    FILE* out = fopen(argv[2], "w"); //creates a file object with the output file name
    if (in == NULL) { //checks if the file in is null
        printf("ERROR: failed to open '%s'", argv[1]); //prints that it failed to open the input file
        fclose(out);
        return 0; //returns 0
    }
    if (out == NULL) { //checks if the file out is null
        printf("ERROR: failed to open '%s'", argv[2]); //prints that it failed to open the output file
        fclose(in);
        return 0; //returns 0
    }

    int rows = 0; //creates a row variable
    int columns = 0; //creates a column variable
    double greyValue = 0; //creates greyValue variable
    if (argc == 3) { //if there are no commands
        fscanf(in, "%d %d", &rows, &columns); //scans in the rows and columns
        fprintf(out, "%d %d\n", rows, columns); //prints the rows and columns to the output file
        for (int j = 0; j < rows; j++) { //loops through the rows
            for (int i = 0; i < columns; i++) { //loops through the columns
                fscanf(in, "%lf", &greyValue); //scans in the greyValue
                fprintf(out, "%lf ", greyValue); //prints the greyValue to the output file
            }
            fprintf(out, "\n"); //prints linefeed to output file
        }
        return 0; //returns 0
    }
    fscanf(in, "%d %d", &rows, &columns); //scans in the rows and columns
    double value[rows][columns]; //creates double array to hold greyValues
    for (int j = 0; j < rows; j++) { //loops through the rows
        for (int i = 0; i < columns; i++) { //loops through the columns
            fscanf(in, "%lf", &greyValue); //scans in the grey value from the input file
            value[j][i] = greyValue; //sets the current position of the value array equal to greyValue
        }
    }

    char* commandList[(argc - 3)]; //creates an array of commands
    for (int i = 3; i < argc; i++) { //loops through arguments
        commandList[i - 3] = argv[i]; //sets command list starts at zero equal to argv starting at 3
    }

    for (int i = 0; i < (argc - 3); i++) { //loops through the arguments of commands
        if (strcmp("print", commandList[i]) == 0) { //compares the current command in the list to the command print

            printf("Command: %s\n", commandList[i]); //prints the command
            array2DPrintASCII(rows, columns, value); //calls the method to print the ascii values

        } else if (strcmp("stats", commandList[i]) == 0) { //compares the current command in the list to the command stats

            printf("Command: %s\n", commandList[i]); //prints the command
            Array2DStats stats = array2DComputeStats(rows, columns, value); //calls the method to compute the stats
            array2DPrintStats(&stats); //calls the method to print the stats

        } else if (strcmp("mirror", commandList[i]) == 0) { //compares the current command in the list to the command mirror

            printf("Command: %s\n", commandList[i]); //prints the command
            array2DReverseCols(rows, columns, value); //calls the method to reverse the columns


        } else if (strcmp("flip", commandList[i]) == 0) { //compares the current command in the list to the command flip

            printf("Command: %s\n", commandList[i]); //prints the command
            array2DReverseRows(rows, columns, value); //calls the method to reverse the rows

        } else if (strcmp("bw", commandList[i]) == 0) { //compares the current command in the list to the command bw

            double bw[2]; //creates an array for black and white values
            bw[0] = 0.0; //sets the 1st value equal to 0
            bw[1] = 1.0; //sets the 2nd value equal to 1
            printf("Command: %s\n", commandList[i]); //prints the command
            array2DQuantize(rows, columns, value, 2, bw); //calls the method to convert the greyscale values to either 0 or 1
        } else if (strcmp("4color", commandList[i]) == 0) { //compares the current command in the list to the command 4color

            double fourColor[4]; //creates an array for 4 colors
            fourColor[0] = 0.0; //sets the 1st value equal to 0
            fourColor[1] = 0.33; //sets the 2nd value equal to 0.33
            fourColor[2] = 0.66; //sets the 3rd value equal to 0.66
            fourColor[3] = 1.0; //sets the 4th value equal to 1
            printf("Command: %s\n", commandList[i]); //prints the command
            array2DQuantize(rows, columns, value, 4, fourColor); //calls the method to convert the greyscale values to one of the 4 values

        } else { //if it is not a command above

            printf("ERROR: unknown command"); //prints an error message
        }
    }

    fprintf(out, "%d %d\n", rows, columns); //prints the rows and columns to the output file
    for (int j = 0; j < rows; j++) { //loops through the rows
        for (int k = 0; k < columns; k++) { //loops through the columns
            fprintf(out, "%lf ", value[j][k]); //prints the value at the current position to the output file
        }
        fprintf(out, "\n"); //prints a linefeed to the output file
    }
    fclose(in);
    fclose(out);
}
