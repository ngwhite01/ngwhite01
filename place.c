#include <stdlib.h>
#include "nqueens.h"
int place(int pos) {
    int i;
    for (i=1;i<pos;i++) {
        if((a[i]==a[pos])||((abs(a[i]-a[pos])==abs(i-pos))))
            return 0;
    }
    return 1;
}

