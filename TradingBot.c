#include <stdio.h>
#include <stdlib.h>

/**
 * Nathan White
 * ngwhite
 *This program takes in an investment and goes through bond and stock data and chooses when to buy and sell the shares you own for you.
 */
int main(int argc, char** argv) {
    if (argc == 0) { //checks if there are enough arguments.
        printf("Usage: TradingBot <investment amount>");
        return 0;
    }
    if (argv[1] <= 0) { //checks to see if the investment is positive.
        printf("ERROR: investment must be positive!");
        return 0;
    }
    double investment = atof(argv[1]); //passes in the investment the user is making
    int time = 0;
    scanf("%d", &time); //scans in the days from the text file
    double stocknumber=0.0;
    double openstock[time]; //array of stock open costs
    double closestock[time]; //array of stock close costs
    double openbond[time]; //array of bond open costs
    double closebond[time]; //array of close costs
    double earnings=0.0;
    double stockchange[time]; //array of change in stock prices from opening to closing each day
    double bondchange[time];  //array of change in bond prices from opening to closing each day
    double finalprice = 0.0;

    for(int i=0;i<time;i++){ //reads in the open and closing stock prices for each day
        scanf("%lf %lf", &openstock[i], &closestock[i]);
    }
    for(int i=0;i<time;i++){ //reads in the open and closing bond prices for each day
        scanf("%lf %lf", &openbond[i], &closebond[i]);
    }

    for (int i=0; i < time; i++) { //computes the change in price between opening and closing for both bonds and stocks and puts each day's values into an array.
        stockchange[i] = (((closestock[i] - openstock[i]) / openstock[i]) * 100);
        bondchange[i] = (((closebond[i] - openbond[i]) / openbond[i]) * 100);

    }
    int identifier = 0;
    int buy = 0;
    stocknumber = (investment / openstock[0]); //sets the starting stock number
    printf("0: open, buy %0.2f stock @ %0.2f = $%0.2f \n", stocknumber, openstock[0], investment); //prints off the initial information for the bot
    for (int j = 1; j < time; j++) { //iterates through and determines when it is a good time to buy or sell bonds/stocks
        if (bondchange[j-1]<stockchange[j-1]) {
            if(identifier==1) {
                buy = 1;
                identifier = 0;
            }
            identifier = 0;
        }else if(bondchange[j - 1] == stockchange[j - 1]) {
            identifier = 0;
        }
        else {
            if(identifier==0){
                buy=1;
                identifier = 1;
            }

        }
        if(identifier == 1){
            if(buy==1){
                investment = (stocknumber * openstock[j]); //changes the investment to equal the new total
                printf("%d: open, sell %0.2f stock @ %0.2f = $%0.2f\n", j, stocknumber, openstock[j],investment); //prints the selling of the stock and the information related to it
                stocknumber = (investment / openbond[j]); //computes the new number of shares for the bond you are purchasing
                printf("%d: open, buy %0.2f bond @ %0.2f = $%0.2f\n", j, stocknumber, openbond[j],investment); //prints the buying info for the bond
                buy = 0;
            }
        }
        if(identifier == 0){
            if(buy==1){
                investment = (stocknumber * openbond[j]);
                printf("%d: open, sell %0.2f bond @ %0.2f = $%0.2f\n", j, stocknumber, openbond[j],investment); //prints the selling of the bond and the information related to it
                stocknumber = (investment / openstock[j]); //computes the new number of shares for the stock you are purchasing
                printf("%d: open, buy %0.2f stock @ %0.2f = $%0.2f\n", j, stocknumber, openstock[j],investment); //prints the buying info for the stock
                buy = 0;

            }
        }

    }

    if(identifier==1){ //if you end the days holding a bond it determines the total from your shares and its current price. Then it prints off the information regarding that last sale.
        investment = (stocknumber * closebond[time-1]);
        printf("%d: close, sell %0.2f bond @ %0.2f = $%0.2f\n", (time-1), stocknumber, closebond[time-1],investment);
        finalprice = (stocknumber * closebond[time-1]); //figures out your final investment price
    }
    else{ //if you end the days holding a stock it determines the total from your shares and its current price. Then it prints off the information regarding that last sale.
        investment = (stocknumber * closestock[time-1]);
        printf("%d: close, sell %0.2f stock @ %0.2f = $%0.2f\n", (time-1), stocknumber, closestock[time-1],investment);
        finalprice = (stocknumber * closestock[time-1]); //figures out your final investment price
    }
    earnings = (finalprice - atof(argv[1]));
    printf("profit/loss $%0.2f\n", earnings); //computes your profit/loss
}
