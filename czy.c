#include "bitsy.h"
#include <stdio.h>
//This header includes prototypes for the proposed bit abstractions
/*Include any additional headers you require*/

/*You may use any global variables/structures that you like*/

/* main - czy compression implementation
 * Develop a program called czy which compresses the data stream directed 
 * at its standard input and writes the compressed stream to its standard 
 * output.
 *
 * The compression algorithm reads the input one symbol (i.e., byte) at a 
 * time and compares it with each of the 8 bytes previously seen.  It also 
 * checks to see if the following n characters are the same as the current 
 * symbol.  If the byte has been previously seen, it outputs the position of 
 * the previous byte relative to the current position, Otherwise, the symbol 
 * is output as is by prefixing it with a binary one.
 *
 * To compile czy: make czy
 * To execute: ./czy < somefile.txt > somefile.encoded
 */
int main(int argc, char *argv[]){
    unsigned char bytearray[8];
    int num=0;
    while(num!=256){
        if(num==-1){
            perror("Error: failed to read");
            return -1;
        }
        for(int i=0;i<8;i++){
            bytearray[i]=read_byte();
            num=bytearray[i];
        }
        unsigned char symbol = read_byte();
        if(bytearray[7]==symbol){
            write_bit(0);
            write_bit(0);
            write_bit(0);
            write_bit(0);
        }
        else if(bytearray[6]==symbol){
            write_bit(0);
            write_bit(0);
            write_bit(0);
            write_bit(1);
        }
        else if(bytearray[5]==symbol){
            write_bit(0);
            write_bit(0);
            write_bit(1);
            write_bit(0);
        }
        else if(bytearray[4]==symbol){
            write_bit(0);
            write_bit(0);
            write_bit(1);
            write_bit(1);
        }
        else if(bytearray[3]==symbol){
            write_bit(0);
            write_bit(1);
            write_bit(0);
            write_bit(0);
        }
        else if(bytearray[2]==symbol){
            write_bit(0);
            write_bit(1);
            write_bit(0);
            write_bit(1);
        }
        else if(bytearray[1]==symbol){
            write_bit(0);
            write_bit(1);
            write_bit(1);
            write_bit(0);
        }
        else if(bytearray[0]==symbol){
            write_bit(0);
            write_bit(1);
            write_bit(1);
            write_bit(1);
        } else{
            write_bit(1);
            write_byte(symbol);
        }

    }
    flush_write_buffer(bytearray);


	//The implementation of your encoder should go here.
	
	//It is recommeded that you implement the bit abstractions in bitsy.c and
	//utilize them to implement your encoder. 
	//If so, do NOT call read/write here. Instead rely exclusively on 
	//readBit, readByte, writeBit, writeByte, and flushWriteBuffer.
	
	return 0; //exit status. success=0, error=-1
}
