#include <stdio.h>
#include <stdlib.h>

/**
 * This method calculates the percent of change between the opening and closing costs of that day.
 * @param open
 * @param close
 * @return change
 */

double relativePercentage(double open, double close) {
    double change = (((close - open) / open) * 100);
    return change;
}
/**
 * This program initializes the array based on the size passed in and fills it with the value passed in.
 * @param value
 * @param size
 * @param values
 */
void initArray(double value, int size, double values[size]) {
    for(int i=0;i<size;i++){
        values[i]=value;
    }
}
/**
 * sorts the values passed into the method in the array. It sorts the largest to the start and the smallest to the end.
 * @param value
 * @param size
 * @param values
 */
void addToSortedArray(double value, int size, double values[size]) {
    int count = 0;
    for (int i = 0; i < size; i++) {
        if (values[i] > value) {
            count++;
        }
    }
    double holder = values[count];
    double holder2 = values[count];
    values[count] = value;
    for (int j = count; j < size-1; j++) {
        holder2 = values[j + 1];
        values[j + 1] = holder;
        holder = holder2;
    }
}
/**
 * updates the stats of the program based on the stock and bond change values passed in.
 * @param stockChange
 * @param bondChange
 * @param stockUpBondUp
 * @param stockUpBondDown
 * @param stockDownBondUp
 * @param stockDownBondDown
 */
void updateStats(double stockChange, double bondChange, int* stockUpBondUp, int* stockUpBondDown, int* stockDownBondUp, int* stockDownBondDown) {
    if (stockChange > 0.00 && bondChange > 0.00) {
        *stockUpBondUp += 1;
    }
    else if (stockChange > 0.00 && bondChange < 0.00) {
        *stockUpBondDown += 1;
    }
    else if (stockChange < 0.00 && bondChange > 0.00) {
        *stockDownBondUp += 1;
    }
    else if(stockChange < 0.00 && bondChange < 0.00){
        *stockDownBondDown += 1;
    }
}
/**
 * prints the sorted list based on the parameter size passed in. It also determines whether it is a stock or bond based on the prefix.
 * @param prefix
 * @param size
 * @param values
 */
void printArray(char* prefix, int size, double values[size]) {
    for (int i = 0; i < size; i++) {
        printf("best %s, %0.2f \n", prefix, values[i]);
    }

}
/**
 * Nathan White
 * ngwhite
 * This program takes in stock and bond data from a text file and then computes the change between both lists over each day.
 * It also determines how many days the stocks and bonds go up or down. The program also sorts the changes between the stocks and bonds in order to print the largest to the smallest based on the argument passed in.
 */
int main(int argc, char** argv) {
    if (argc == 0) { //checks if there are enough arguments.
        printf("Usage: StocksBonds <num best> ");
        return 0;
    }
    if (argv[1] <= 0) { //checks to see if the investment is positive.
        printf("ERROR: num best must be positive!");
        return 0;
    }
    int numbest = atoi(argv[1]);
    int time = 0;
    scanf("%d", &time);
    double openstock[time]; //array for open stock costs
    double closestock[time]; //array for close stock costs
    double openbond[time]; //array for open bond costs
    double closebond[time]; //array for close bond costs
    double stockchange[time]; //array for stock change
    double bondchange[time]; //array for bond change
    int upup = 0;
    int updown = 0;
    int downup = 0;
    int downdown = 0;
    double stockorder[time];
    double bondorder[time];
// this section initializes all the arrays with small values so it can get new values later and then sort them.
    initArray(-1000000,time,openstock);
    initArray(-1000000,time,closestock);
    initArray(-1000000,time,openbond);
    initArray(-1000000,time,closebond);
    initArray(-1000000,time,stockorder);
    initArray(-1000000,time,bondorder);
    //scans in all the data from the file about the stocks.
    for(int i=0;i<time;i++){
        scanf("%lf %lf", &openstock[i], &closestock[i]);
    }
    //scans in all the data from the file about the bonds.
    for(int i=0;i<time;i++){
        scanf("%lf %lf", &openbond[i], &closebond[i]);
    }
    //determines the stock and bond change rates over all the days and then sorts that information while also updating the stats
    // for whether the bonds and stocks went up or down over the days of the file.
    for (int i=0; i < time; i++) {
        stockchange[i] = relativePercentage(openstock[i],closestock[i]);
        bondchange[i] = relativePercentage(openbond[i], closebond[i]);

        addToSortedArray(stockchange[i], time, stockorder);
        addToSortedArray(bondchange[i], time, bondorder);

        updateStats(stockchange[i], bondchange[i], &upup, &updown, &downup, &downdown);

    }
    //this section computes doubles that hold the percentage of days where bonds and stocks went up or down.
    double stockupbondup =(((double)(upup) / time) * 100);
    double stockupbonddown =(((double)(updown) / time) * 100);
    double stockdownbondup =(((double)(downup) / time) * 100);
    double stockdownbonddown =(((double)(downdown) / time) * 100);
    //prints off the entire list of stock and bond changes over every day.
    for(int i=0; i<time;i++){
        printf("%d: stocks %0.2f%%, bonds %0.2f%% \n", i, stockchange[i], bondchange[i]);
    }
    //prints the percentage of days for each type.
    printf("stocks up, bonds up, %0.2f%% of days\n", stockupbondup);
    printf("stocks up, bonds down, %0.2f%% of days\n", stockupbonddown);
    printf("stocks down, bonds up, %0.2f%% of days\n", stockdownbondup);
    printf("stocks down, bonds down, %0.2f%% of days\n", stockdownbonddown);
    //calls print array to print off the best of each type based on the value of numbest
    if(numbest>time){
        numbest = time;
    }
    printArray("stocks", numbest, stockorder);
    printArray("bonds", numbest, bondorder);

}


