#include <stdlib.h>
#include <stdio.h>
int main(int argc, char *argv[])
{
  long val;
  if (argc!=2)exit(EXIT_FAILURE);
  val=strtol(argv[1],NULL,10);
  val=val+10; //x is not defined, assuming it wants val to be ever increasing since it's the only defined variable
  printf("Argument is %ld\n",val); //%l means nothing while val is a long which is represented as %ld

  return 0; //return necessary since main returns int
}

  
