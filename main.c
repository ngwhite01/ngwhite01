#include <stdio.h>
#include "nqueens.h"
    int count=0;
    int a[30];
int main() {

        int i,n;
        printf("Enter the number of Queens\n");
        scanf("%d",&n);
        queen(n);
        printf("\nTotal solutions=%d",count);
        return(0);
    }

