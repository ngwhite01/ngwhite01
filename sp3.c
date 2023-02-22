#include <string.h>
#include <stdlib.h>

int main()
{
  char *buf = malloc( sizeof(char) * 14); //malloc enough space for message to be copied
  char message[14]="Hello, world\n"; //Moved message here since there's no reason for it to be global
  strncpy(buf,message,14);
  free(buf); //free the space of buff

  return 0; //needs a return value for int main
}

