#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(int argc, char* argv[]) {
    if(argc<3){
        perror("Error: lack of arguments");
        return 1;
    }
    int blocksize;
    if(argc<4){
        blocksize=1024;
    }else{
        blocksize=atoi(argv[3]);
    }

    int file1=open(argv[1],O_RDONLY);
    int file2=open(argv[2],O_WRONLY);

    if(blocksize % 4 != 0){
        char* warning="Warning block size not a multiple of 4";
        write(1,warning, strlen(warning));
    }
    while(blocksize % 4!=0){
        blocksize++;
    }
    if(file1==-1){
        perror("Error: file failed to open");
        return 1;
    }
    if(file2==-1){
        perror("Error: file failed to open");
        return 1;
    }
    int num=1;
    unsigned int buff[blocksize/4];
    unsigned char buff2[100];
    int num2=1;
    unsigned int checksum=0;
    while(num!=0) {
        num = read(file1, buff, blocksize);
        if (num == -1) {
            perror("Error: failed to read");
            return 1;
        } else if (num<blocksize) {
            char* temp = (char*) buff;
            for(int i=num;i<blocksize;i++){
                temp[i]='0';
            }
            for (int i = 0; i < (blocksize/4); i++) {
                checksum=buff[i]^checksum;
            }
            sprintf(buff2,"%08X",checksum);
            write(1,buff2, strlen(buff2));
            num2=write(file2,buff,blocksize);
        } else {
            for(int i=0;i<(blocksize/4);i++){
                checksum=buff[i]^checksum;
            }
            sprintf(buff2,"%08X",checksum);
            write(1,buff2, strlen(buff2));
            num2=write(file2,buff,blocksize);
        }
    }
return 0;
}