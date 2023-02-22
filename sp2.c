#include <stdlib.h>
#include <stdio.h>
int toUpper(char *bufToConvert)
{
  int i;
  
  if (bufToConvert==NULL) return 0; //works correctly since nothing input so returns 0
  i=0;
  while (bufToConvert[i]!='\0') //need to iterate character by character
    {
      if ((bufToConvert[i]>='a')&&(bufToConvert[i]<='z')) //changed from string as a whole to char
	bufToConvert[i]-=32;
      i++;
    }
  return i;
}
int main(int argc, char *argv[])
{
  if (argc!=2)exit(EXIT_FAILURE);
  printf("String is <%s>\n",argv[1]);
  toUpper(argv[1]);
  printf("After conversion string is <%s>\n",argv[1]);
  return(0);
  
}

  
