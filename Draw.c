// Student stub code for ASCII Drawing assignment

#include <stdio.h>
#include <stdlib.h>
#include <stdlib.h>
#include <float.h>
#include <math.h>
/*
 * Nathan White
 * ngwhite
 * This program takes in a file and then scans it for information such as the height, width, x values, y values, and colors of certain pixels. It then goes through and depending on the case will print lines, rectangles, and points in order to display a particular image. It then prints off this image as well as some stats such as the max color value, min color value, and average color value.
 */
/**
 * This method takes in width and height as well as the image array and creates an array full of zeros of the designated size.
 * @param width
 * @param height
 * @param image
 */
void initImage(int width, int height, double image[height][width])
{
    for (int j = 0; j < height; j++) { //loops through height
        for (int i = 0; i < width; i++) { //loops through width
            image[j][i] = 0.0; //sets current point equal to 0
        }
    }
}
/**
 * This method takes in width, height, and the image array and them prints it off along with a border depend on the values in each array location.
 * @param width
 * @param height
 * @param image
 */
void printImage(int width, int height, double image[height][width])
{
    printf("+"); //prints corner border character
    for (int i = 0; i < width; i++) { //loops through width
        printf("-"); //prints upper border character
    }
    printf("+ \n"); //prints corner border character along with a linespace
    for (int j = 0; j < height; j++) { //loops through height
        printf("|"); //prints edge border character
        for (int i = 0; i < width; i++) { //loops through width

            if (0.00 <= image[j][i] && image[j][i] < 0.1) { //prints a space if the value is within this range
                printf(" ");

            }
            else if (0.1 <= image[j][i] && image[j][i] < 0.2) { //prints a period if the value is within this range
                printf(".");

            }
            else if (0.2 <= image[j][i] && image[j][i] < 0.3) { //prints a colon if the value is within this range
                printf(":");

            }
            else if (0.3 <= image[j][i] && image[j][i] < 0.4) { //prints a dash if the value is within this range
                printf("-");

            }
            else if (0.4 <= image[j][i] && image[j][i] < 0.5) { //prints an equals sign if the value is within this range
                printf("=");

            }
            else if (0.5 <= image[j][i] && image[j][i] < 0.6) { //prints a plus if the value is within this range
                printf("+");


            }
            else if (0.6 <= image[j][i] && image[j][i] < 0.7) { //prints an asterisk if the value is within this range
                printf("*");


            }
            else if (0.7 <= image[j][i] && image[j][i] < 0.8) { //prints a pound sign if the value is within this range
                printf("#");

            }
            else if (0.8 <= image[j][i] && image[j][i] < 0.9) { //prints a percent sign if the value is within this range
                printf("%%");

            }
            else if (0.9 <= image[j][i] && image[j][i] <= 1.0) { //prints an at sign if the value is within this range
                printf("@");

            }
        }
        printf("| \n"); //prints edge border character along with linefeed
    }
    printf("+"); //prints corner border character
    for (int i = 0; i < width; i++) { //loops through width
        printf("-"); //prints edge border character
    }
    printf("+ \n"); //prints corner border character along with linefeed
}
/**
 * This method takes in width, height, the image array, a x point, a y point, and the color value and then makes the specified point in the array equal to color if it is in range.
 * @param width
 * @param height
 * @param image
 * @param x
 * @param y
 * @param color
 */
void drawPoint(int width, int height, double image[height][width], int x, int y, double color)
{
    if (x >= width || x < 0 || y >= height || y < 0) { //checks to see if the inputted x and y values are out of range of the array
        return; //returns if that's the case
    }
    else{ //if it is in range
        image[y][x] = color; //sets point equal to color
    }
} 
/**
 * This method takes in width, height, the image array, the left x point, a the top y point, the rectangle width, the rectangle height, and the color value and then makes the specified point in the array starting from the top left corner of the rectangle equal to color if it is in range and then keeps looping through to all the points the rectangle would cover.
 * @param width
 * @param height
 * @param image
 * @param left
 * @param top
 * @param rectangleWidth
 * @param rectangleHeight
 * @param color
 */
void drawRectangle(int width, int height, double image[height][width], int left, int top, int rectangleWidth, int rectangleHeight, double color)
{
    for (int i = 0; i < rectangleHeight; i++) { //loops through rectangle height
        for (int j = 0; j < rectangleWidth; j++) { //loops through rectangle width
                drawPoint(width,height,image,(left+j),(top+i),color); //calls the draw point function for all the points the loop would access
        }
    }

}
/**
 * This method takes in width, height, the image array, a x1 point, a x2 point, a y1 point, a y2 point, and the color value and it then draws a line between the 2 points in the specified color value.
 * @param width
 * @param height
 * @param image
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param color
 */
void drawLine(int width, int height, double image[height][width], int x1, int y1, int x2, int y2, double color)
{
    double dx = x2 - x1; //the difference in the 2 x points
    double dy = y2 - y1; //the difference in the 2 y points
    double x=0;
    double y=0;
    double step = 0.0;
    double i=0;

    if (abs((int)round(dx)) >= abs((int)round(dy))) { //checks if the dx value is larger than dy
        step = abs((int)round(dx)); //sets step equal to dx
    }
    else {
        step = abs((int)round(dy)); //sets step equal to dy
    }
    dx = dx / step; //calculates a new dx value based on step
    dy = dy / step; //calculates a new dy value based on step
    x = x1; //sets x equal to x1
    y = y1; //sets y equal to y1
    while (i <= step) { //while the int i counter is less than step
        if (x >= width || x < 0 || y >= height || y < 0) { //checks if the point is out of range.
            i++; //adds to the counter
        } else { //if the point is in range
            image[(int) round(y)][(int) round(x)] = color; //sets the current point equal to color
            i++; //adds to the counter
        }
        x = x + dx; //sets x equal to x plus dx
        y = y + dy; //sets y equal to y plus dy

    }
}
/**
 * This method takes in the image array, width, height, as well as pointers to the mincolor, maxcolor, and average color variables. It then calculates the min color and max color values as well the average color value of the entire array.
 * @param width
 * @param height
 * @param image
 * @param minColor
 * @param maxColor
 * @param avgColor
 */
void getImageStats(int width, int height, double image[height][width], double* minColor, double* maxColor, double* avgColor)
{
    *minColor = DBL_MAX; //sets min color to the largest double value
    *maxColor = DBL_MIN; //sets max color to the smallest double value
    double total=0; //creates total variable
    for (int j = 0; j < height; j++) { //loops through height
        for (int i = 0; i < width; i++) { //loops through width
            total = image[j][i] + total; //totals the current point value into the total variable
            if (image[j][i] > *maxColor) { //checks if the current point is larger than the current max color value
                *maxColor = image[j][i]; //sets max color equal to the current point
            }
            if (image[j][i] < *minColor) { //checks if the current point is smaller than the current min color value
                *minColor = image[j][i]; //sets min color equal to the current point
            }
        }
    }
    *avgColor = total / (width*height); //sets average color equal to the total variable divided by the total number of points.
}
/**
 *This method takes in the width, height, image array, x value, y value, and color. It then checks to see if the value is in range and then sets the inputted point equal to the color value if the color value. It then recursively calls this method for the points in every direction around the inputted one. //It doesn't set a point to color if the color value at that point is larger than the color value input.
 * @param width
 * @param height
 * @param image
 * @param x
 * @param y
 * @param color
 */
void floodFill(int width, int height, double image[height][width], int x, int y, double color)
{
    if (0 > x || x >= width || 0 > y || y >= height || image[y][x] >= color) { //checks to see if the point for flood fill is out of the range as well as if the point has a greater color value
        return;
    }
    else {
        image[y][x] = color; //sets the current point to the color value
        floodFill(width, height, image, x, (y + 1), color); //recursively calls to the point above
        floodFill(width, height, image, x, (y - 1), color); //recursively calls to the point below
        floodFill(width, height, image, (x + 1), y, color); //recursively calls to the point to the left
        floodFill(width, height, image, (x - 1), y, color); //recursively calls to the point to the right

    }

}

// Print the resulting greyscale image as ASCII art.
// You need to fix the lines marked with TODO comments to read input from standard input.
// Do not change other things in the main function.
int main(void)
{
    // Read in the size of the drawing canvas
    int width = 0;
    int height = 0;
    int result = scanf("%d %d", &width, &height); //scans in the width and height from the file
        
    // Program only supports images that are 1x1 or bigger
    if ((width <= 0) || (height <= 0) || (result != 2)) {
        printf("Failed to read a valid width and height from standard input!\n");
        return 0;
    }
    double image[width][height];
    initImage(width, height, image);
    
    char command = '\0';
    double color = 0.0;


    while (scanf(" %c", &command) == 1) //Keep reading in drawing commands until we reach the end of the input
    {
        switch (command)
        {		
            case 'p': 	
            {
                // Draw a point, read in: x, y, color
                int x = 0;
                int y = 0;
                result = scanf("%d %d %lf", &x, &y, &color); //scans in the x, y, and color values for drawing points
                if (result != 3)
                {
                    printf("Invalid point command!\n");
                    return 0;
                }
		
                drawPoint(width, height, image, x, y, color);
                break;
            }
            case 'r': 	
            {
                // Draw a rectangle, read in: x, y, w, h, color
                int left = 0;
                int top = 0;
                int rectangleWidth = 0;
                int rectangleHeight = 0;
                result = scanf("%d %d %d %d %lf", &left, &top, &rectangleWidth, &rectangleHeight, &color); //scans in the left, top, rectangle width, rectangle height, and color for drawing rectangles
                if (result != 5)
                {
                    printf("Invalid rectangle command!\n");
                    return 0;
                }
                
                drawRectangle(width, height, image, left, top, rectangleWidth, rectangleHeight, color);
                break;
            }
            case 'l':
            {
                // Draw a line, read in x1, y1, x2, y2, color
                int x1 = 0;
                int y1 = 0;
                int x2 = 0;
                int y2 = 0;                
                result = scanf("%d %d %d %d %lf", &x1, &y1, &x2, &y2, &color); //scans in the x1, y1, x2, y2, and color values for drawing a line
                if (result != 5)
                {
                    printf("Invalid line command!\n");
                    return 0;
                }
                drawLine(width, height, image, x1, y1, x2, y2, color);
                break;
            }            
            case 'f':
            {
                // Flood fill a color in, read in: x, y, color
                int x = 0;
                int y = 0;
                result = scanf("%d %d %lf", &x, &y, &color); //scans in the x, y, and color values for the flood fill method
                if (result != 3)
                {
                    printf("Invalid flood fill command!\n");
                    return 0;
                }
                
                floodFill(width, height, image, x, y, color);
                break;
            }
            default:
            {
                printf("Unknown command!\n");
                return 0;
            }
        }
    }
	
    // Print the final image
    printImage(width, height, image);
    
    // Finally display some statistics about the image
    double minColor = 0.0;
    double maxColor = 0.0;
    double avgColor = 0.0;
    getImageStats(width, height, image, &minColor, &maxColor, &avgColor);
    printf("Color range [%.2f, %.2f], average %.4f\n", minColor, maxColor, avgColor);
}



