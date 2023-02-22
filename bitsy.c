#include "bitsy.h"
#include <stdio.h>
#include <fcntl.h>
/* Add any global variables or structures you need.*/

/* read_byte
 * Abstraction to read a byte.
 * Relys on readBit.
 */
unsigned char wbuff[1024];
unsigned char holder=0;
int wcounter=0;
int rcounter=8;
int counter=0;
int fcounter=0;
int dbit=1;
unsigned short read_byte(){ 
	/* This function should not call read() directly.
	 * If we are buffering data in read_bit, we dont want to skip over
	 * that data by calling read again. Instead, call read_bit and 
	 * construct a byte one bit at a type. This also allows us to read
	 * 8 bits that are not necessarily byte alligned. */
	unsigned short holder2=0;
	int j= 0;
	unsigned short holder3=0;
	for(int i=0;i<8;i++){
        holder2 = read_bit();
        if(holder2 ==256){
            return 256;
        }else{
            holder3=holder2|j;
            holder3<<1;
        }
	}

	//Do NOT call read here. Instead rely on read_bit.
	
	//I suggest returning a unique value greater than the max byte value
	//to communicate end of file. We need this since 0 is a valid byte value
	//so we need another way to communicate eof. These functions are typed
	//as short to allow us to return a value greater than the max byte value.
	return holder3; //placeholder
}

/* read_bit
 * Abstraction to read a bit.
 * */
unsigned short read_bit(){
	/* This function is responsible for reading the next bit on the
	 * input stream from stdin (fd = 0). To accomplish this, keep a 
	 * byte sized buffer. Each time read bit is called, use bitwise
	 * operations to extract the next bit and return it to the caller.
	 * Once the entire buffered byte has been read the next byte from 
	 * the file. Once eof is reached, return a unique value > 255
	 *
	 */
    int num=0;
    if(rcounter==8){
        unsigned char array1[1];
        num=read(0, array1, 1);
        holder=array1[0];
        rcounter=0;

    }
    if(num==-1){
        perror("Error: failed to read");
        return -1;
    }
    if(num==0){
        return 256;
    }

    int i = 0b10000000;
    unsigned short bit=  (holder & i);
    holder<<1;
    rcounter++;
    if(bit==0){
        return bit;
    }else{
        return 1;
	}
	//You will need to call read here

	//I suggest returning a unique value greater than the max byte value
	//to communicate end of file. We need this since 0 is a valid byte value
	//so we need another way to communicate eof. These functions are typed
	//as short to allow us to return a value greater than the max byte value.
	return 0; //placeholder
}

/* write_byte
 * Abstraction to write a byte.
 */
void write_byte(unsigned char byt){
	/* Use writeBit to write each bit of byt one at a time. Using writeBit
	 * abstracts away byte boundaries in the output.*/

	for(int i=0;i<8;i++){
        int j = 0b10000000;
        unsigned short bit=  (byt & j);
        byt<<1;
        write_bit(bit);
	}
	//Do NOT call write, instead utilize writeBit()
}

/* write_bit
 * Abstraction to write a single bit.
 */
void write_bit(unsigned char bit){
	/* Keep a byte sized buffer. Each time this function is called, insert the 
	 * new bit into the buffer. Once 8 bits are buffered, write the full byte
	 * to stdout (fd=1).
	 */
	int num=1;
    int j= 0;
    if(counter==8){
        unsigned char holder4[1];
        holder4[0] = wbuff[wcounter];
        num=write(1, holder4, 1);
        fcounter++;
        if(num==-1){
            perror("Error: failed to write");
        }
        wcounter++;
        counter=0;
    }else{
        wbuff[wcounter]=bit|j;
        wbuff[wcounter]<<1;
        counter++;
	}

	//You will need to call write here eventually.

}

/* flush_write_buffer
 * Helper to write out remaining contents of a buffer after padding empty bits
 * with 1s.
 */
void flush_write_buffer(){
	/* This will be utilized when finishing your encoding. It may be that some bits
	 * are still buffered and have not been written to stdout. Call this function 
	 * which should do the following: Determine if any buffered bits have yet to be 
	 * written. Pad remaining bits in the byte with 1s. Write byte to stdout
	 */
	int j=0;
	int num=0;
	int count=0;
	unsigned char bholder;
	if(counter==0){

	}else if(counter<8){
	    for(int i=0;i<8-counter;i++){
            wbuff[wcounter]=1&1;
            wbuff[wcounter]<<1;
	    }
	    unsigned char array1[1];
	    array1[0]=wbuff[wcounter];
	    write(1,array1,1);

	}else{
        unsigned char array1[1];
        array1[0]=wbuff[wcounter];
        write(1,array1,1);
	}

}
