// For various functions that work with 2D arrays of double values
//
// DO NOT MODIFY

#ifndef _ARRAY_2D_
#define _ARRAY_2D_

#include <stdbool.h>

// Holds statistics about the elements of the array
typedef struct
{
    double min;     // Minimum value in the array
    double max;     // Maximum value in the array
    double mean;    // Mean value of the array
} Array2DStats;

// Function prototypes for Display program
void array2DPrintASCII(int rows, int cols, double pixels[rows][cols]);
Array2DStats array2DComputeStats(int rows, int cols, double a[rows][cols]);
void array2DPrintStats(const Array2DStats* stats);

// Function prototypes for Process program
void array2DReverseRows(int rows, int cols, double a[rows][cols]);
void array2DReverseCols(int rows, int cols, double a[rows][cols]);
void array2DQuantize(int rows, int cols, double a[rows][cols], int numLevels, double levels[numLevels]);

#endif
