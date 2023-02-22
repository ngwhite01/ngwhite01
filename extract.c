#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <math.h>
#include <unistd.h>
#include <sys/stat.h>
#include "ufsext.h"

int extract(int fd, void *buf, size_t bytes, size_t offset){
    struct stat buffer;
    fstat(fd, &buffer);
    int size = buffer.st_size;

    if(size<0){
        printf("Error: Invalid size");
        return -1;
    }

    if(offset<0){
        printf("Error: Invalid offset size");
        return -1;
    }

    if(offset>size){
        printf("Error: Invalid offset size");
        return -1;
    }

    if(bytes<=0){
        printf("Error: No bytes to delete");
        return -1;
    }

    if((offset+bytes)>size){
        printf("Error: Bytes to delete surpass file size");
        return -1;
    }

    int buffersize;
    buffersize = size - (offset+bytes);
    int *remainderbuff;
    remainderbuff = malloc(buffersize);

    if (lseek(fd, offset, SEEK_SET) == -1){
        printf("Error: Failed to seek at location");
        free(remainderbuff);
        return -1;
    }

    if (read(fd, buf, bytes) == -1){
        printf("Error: Failed to read");
        free(remainderbuff);
        return -1;
    }

    if (lseek(fd, (offset+bytes), SEEK_SET) == -1){
        printf("Error: Failed to seek at location");
        free(remainderbuff);
        return -1;
    }

    if (read(fd, remainderbuff, buffersize) == -1){
        printf("Error: Failed to read");
        free(remainderbuff);
        return -1;
    }
    if (lseek(fd, offset, SEEK_SET) == -1){
        printf("Error: Failed to seek at location");
        free(remainderbuff);
        return -1;
    }
    if(write(fd, remainderbuff, buffersize) == -1){
        printf("Error: Failed to write");
        free(remainderbuff);
        return -1;
    }
    ftruncate(fd,size-bytes);
    free(remainderbuff);

    if (lseek(fd, offset, SEEK_SET) == -1){
        printf("Error: Failed to seek at location");
        return -1;
    }

    return bytes;
}