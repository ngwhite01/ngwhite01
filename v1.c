#include <string.h>
#include <stdlib.h>
char message[14];

int main(int argc, char *argv[])
{
  char *buf = malloc(sizeof(char) * 14); // malloc for buffer
  strncpy(buf,message,14);
  free(buf); // free buffer
}

