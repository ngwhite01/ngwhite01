#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <math.h>
#include <unistd.h>
#include <sys/stat.h>
#include "ufsext.h"
#include <string.h>
#include <fcntl.h>
#include <stdint.h>


int testops(){
    int fd;
    int bytes;
    fd=open("test.txt", O_RDWR);
    char buf[50];
    bytes = extract(fd, buf,4, 4);
    printf("\n %d", bytes);

    int fd2;
    int bytes2;
    fd2=open("test2.txt", O_RDWR);
    char buff[50]="abcd";
    bytes2 = insert(fd2, buff,4, 4);
    printf("\n %d", bytes2);

    int fd3;
    int bytes3;
    fd3=open("test3.txt", O_RDWR);
    bytes3 = delete(fd3, 10, 4);
    printf("\n %d", bytes3);

}