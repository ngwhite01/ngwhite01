#include <string.h>
#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[])
{
  char** argCopy;
  int i;
  
  argCopy=malloc((argc+1)*sizeof(char *));
  for (i=0;i<argc;i++)
    { argCopy[i]=malloc(strlen(argv[i])+1);
      strcpy(argCopy[i],argv[i]);
    }
  argCopy[i]=(char *)0;
  
  i=0;
  while (argCopy[i]!=(char *)0){
    printf("argCopy[%d] <%s>\n",i,argCopy[i]);
    i++;
  }

  for (i = 0; i < argc; i++) { //need to free each element of the array
      free(argCopy[i]);
  }

  free(argCopy);
  return(0);
}

