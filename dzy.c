#include "bitsy.h" //This header includes prototypes for the proposed bit abstractions
/*Include any additional headers you require*/

/*You may use any global variables/structures that you like*/

/* main - dzy de-compression implementation
 * This program decompresses a compressed stream directed at its standard input 
 * and writes decompressed data to its standard output.
 *
 * To compile dzy: make dzy
 * To execute: ./dzy < somefile.encoded > somefile_decoded.txt
 */
int main(int argc, char *argv[]){
	//The implementation of your decoder should go here.
	
	//It is recommeded that you implement the bit abstractions in bitsy.c and
	//utilize them to implement your decoder.
	//If so, do NOT call read/write here. Instead rely exclusively on 
	//readBit, readByte, writeBit, writeByte, and flushWriteBuffer.
	unsigned char recentBytearray[8];
	unsigned char num =0;
	unsigned short frequent =2;
	unsigned char position=0;
	unsigned char byte;
	while(num!=256) {
        frequent = read_bit();
        if (frequent == 1) {
            byte = read_byte();
            num = byte;
            write_byte(byte);
            for (int i = 7; i >0; i--) {
                recentBytearray[i] = recentBytearray[i-1];
            }
            recentBytearray[0] = byte;
        }else if (frequent == 0) {
            unsigned short bit1=read_bit();
            unsigned short bit2=read_bit();
            unsigned short bit3=read_bit();
            int j=0;
            position =bit1|j;
            position<<1;
            position=bit2|j;
            position<<1;
            position=bit3|j;
            byte = recentBytearray[0 + position];
            num = byte;
            write_byte(byte);
            for (int i = 7; i >0; i--) {
                recentBytearray[i] = recentBytearray[i-1];
            }
            recentBytearray[0] = byte;
        }
	}
	flush_write_buffer(recentBytearray);
	return 0; //exit status. success=0, error=-1
}
