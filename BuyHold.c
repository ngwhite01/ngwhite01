#include <stdio.h>
#include <stdlib.h>
/**
 * Nathan White
 * ngwhite
 *
 * This program takes in a data file and computes the number of stocks you can buy based on your investment and then at the end of the period it sells it and tells you your profit along with a few other statistics.
 */

int main(int argc, char** argv) {
    if (argc == 0) {
        printf("Usage: BuyHold <investment amount>");
        return 0;
    }
    if (argv[1] <= 0) {
        printf("ERROR: investment must be positive!");
        return 0;
    }
    double investment = atof(argv[1]); //the money you are investing
    int time = 0;
    scanf("%d", &time); //scans in 1st number on the text file which relates to how many days it lasts.
    double open[time]; //array to hold open costs
    double close[time]; //array to hold close costs
    double stocknumber = 0.00;
    double earnings = 0.00;
    for(int i=0; i<time;i++){
        scanf("%lf %lf", &open[i], &close[i]);
    }
    stocknumber = (investment / open[0]); //computes the number of stocks you can buy with the investment you made and the opening cost on day 1.
    double bestdaily= ((close[0]-open[0])/open[0]); //sets best daily to the 1st day's change
    double worstdaily=((close[0]-open[0])/open[0]); //sets worst daily to the 1st day's change
    int daycount1=0;
    int daycount2=0;
    for (int i = 0; i < time; i++) {
        if (open[i] < close[i]) {
            if (((close[i] - open[i]) / open[i]) > bestdaily) {
                bestdaily = ((close[i] - open[i]) / open[i]); //computes a new best daily when a larger one is found.
                daycount1=i;
            }
        }
        if (open[i] > close[i]) {
            if (((close[i] - open[i]) / open[i]) < worstdaily) {
                worstdaily = ((close[i] - open[i]) / open[i]); //computes a new worst daily when a smaller one is found.
                daycount2=i;
            }
        }
    }
    bestdaily = (bestdaily * 100); //formats the value to print correctly
    worstdaily = (worstdaily * 100); //formats the value to print correctly
    double finalprice = (stocknumber * close[time - 1]); //finds the final price based on the previously computed stocknumber and the final day's closing cost.
    earnings = (finalprice - investment);
    printf("0: buy %0.2f @ %0.2f = $%0.2f\n", stocknumber, open[0], investment); //prints the initial investment
    printf("%d: sell %0.2f @ %0.2f = $%0.2f\n", (time-1), stocknumber, close[(time-1)],finalprice); //prints the final return
    printf("profit/loss $%0.2f\n", earnings); //prints the earnings
    printf("best daily change %0.2f%%, day %d\n", bestdaily, daycount1); //prints best day as well as what day it was on.
    printf("worst daily change %0.2f%%, day %d\n", worstdaily, daycount2); //prints worst day as well as what day it was on.
}