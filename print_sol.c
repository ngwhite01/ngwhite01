#include <stdio.h>
#include "nqueens.h"
void print_sol(int n) {
    int i,j;
    count++;
    printf("\n\nSolution #%d:\n",count);
    for (i=1;i<=n;i++) {
        for (j=1;j<=n;j++) {
            if(a[i]==j)
                printf("Q\t"); else
                printf("*\t");
        }
        printf("\n");
    }
}

