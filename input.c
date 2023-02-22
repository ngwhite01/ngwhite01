//  Queries user for specified number of words each having at most the input
//  maximum length.  Compares the words to each other and tells user which
//  do/not match according to strcmp.
// -----------------------------------------------------------------------
//  Invocation: input nWords maxLength
//       also:  input nWords maxLength < wordFile
//              where wordFile contains the words a user would enter.
// -----------------------------------------------------------------------
//  Compilation: gcc -o input input.c
// -----------------------------------------------------------------------
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
int main(int argc, char *argv[]) {
    int maxLength;
    int numWords;
    int goodWords;
    char **words;
    int i;
    int j;
    if (argc != 3) {
        printf("Error: Wrong number of arguments\n");
        exit(1);
    }
    if(argv[2][0]=='-'){
        printf("Error: Invalid input make sure to input a positive integer\n");
        exit(1);
    }
    if(argv[1][0]=='-'){
        printf("Error: Invalid input make sure to input a positive integer\n");
        exit(1);
    }

    for (int k = 0; k < strlen(argv[1]);k++) {
        if (!isdigit(argv[1][k])) {
            printf("Error: Invalid input make sure to input an integer\n");
            exit(1);
        }
    }

    for (int k = 0; k < strlen(argv[2]);k++) {
        if (!isdigit(argv[2][k])) {
            printf("Error: Invalid input make sure to input an integer\n");
            exit(1);
        }
    }

    numWords = strtol(argv[1], NULL, 10);
    maxLength = strtol(argv[2], NULL, 10);
    words = malloc(numWords * sizeof(char *));
    char word;

    //
    //-- Words must be terminated by a newline.
    //
    goodWords = 0;
    if (maxLength < 1) {
        printf("Error: Max length is too small\n");
        exit(1);
    }
    if (numWords < 2) {
        printf("Error: Not enough words to compare\n");
        exit(1);
    }

  while (goodWords < numWords)
    {
      printf("Enter word %d: ",goodWords+1);
      words[goodWords]=malloc((maxLength+2)*sizeof(char));
      fgets(words[goodWords],maxLength+2,stdin);

      if(words[goodWords][strlen(words[goodWords]) - 1] != '\n') {
          printf("Previous word was too long. Enter a new string for word %d: ", goodWords + 1);
          fgets(words[goodWords], 100, stdin);
          fgets(words[goodWords], maxLength + 2, stdin);

      }

        if(words[goodWords][strlen(words[goodWords]) - 1] != '\n') {
            printf("Previous word was too long.\n");
            exit(1);
        }

      *strchr(words[goodWords], '\n') = (char) 0;

      for(int k=0;k<strlen(words[goodWords]) - 1;k++) {
           word = words[goodWords][k];
           if (!isalpha(word)) {
               printf("Error: This word contains characters that aren't letters\n");
               exit(1);
           }
      }

#ifdef DEBUG
      printf("Word[%d] is <%s>\n",goodWords,words[goodWords]);
#endif
      goodWords++;
    }

  for (i=0;i<numWords;i++)
    {
      for (j=i+1;j<numWords;j++)
	{
	  if (strcmp(words[i],words[j])==0)
	    printf("Words %d and %d match.\n",i,j);
	  else
	    printf("Words %d and %d do not match.\n",i,j);
	}
    }
  for(int k=0;k<numWords;k++){
      free(words[k]);
  }
  free(words);
  return 0;
}

       
  
			     
