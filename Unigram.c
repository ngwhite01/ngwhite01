    #include <stdio.h>
    #include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <math.h>
#include <stdbool.h>
#include <ctype.h>
/*
 * Nathan White
 * ngwhite
 * This program takes in a text file and prints the hash value of all the words along with the words themselves using a linked list.
 */
// Maximum length of words that we support
#define MAX_WORD_LEN 255

// Represents a single entry in a bucket's null-terminated linked list
// You should NOT modify this data type
typedef struct node
{
    char* word;  	 // Null-terminated string for this word
    unsigned int count;  // How often we saw this word in the training file
    struct node* next;   // Pointer to next Node in this bucket
} Node;

// Function prototypes, this allows the function implementations to appear in any order in the file
// You will implement these functions:
unsigned int hashString(const char* str){ //This method converts the string to a hash value
unsigned int hashvalue = 0;
for(int i=0;i<strlen(str);i++) {
    hashvalue = 31*hashvalue + str[i];
}
return hashvalue;
}
double calcUnigramLogProb(unsigned int count, unsigned long total){ //This method calculates the log probability of each word in the sentence.
double logProb = 0.0;
    logProb= log10((double)count/(double)total);
    return logProb;
}
void uppercaseAndStrip(char* dest, const char* src){ //This method strips all characters that aren't letters or apostrophe and then makes them upper case
    int count = 0;
    for(int i=0;i<strlen(src);i++){
        if((src[i])>=65 && (src[i])<=90) {
            dest[count]=src[i];
            count++;
        }else if((src[i])>=97 && (src[i])<=122){
            dest[count]=(char)(src[i]-32);
            count++;
        }else if((src[i])==39){
            dest[count]=src[i];
            count++;
        }
    }
    dest[count]= 0;
}

// Test function prototypes, you should NOT modify these
void testHashString();
void testCalcUnigramLogProb();
void testUppercaseAndStrip();
void testFillAllPrintable(char* str);

// Fill an array entry with all printable ASCII characters
void testFillAllPrintable(char* str)
{
    // Create a maximum size string with all printable ASCII characters
    int index = 0;
    char ch = ' ';
    while (index < MAX_WORD_LEN)
    {
        str[index] = ch;
        ch++;
        if (ch > 126)
        {
            ch = ' ';
        }
        index++;
    }
}

// Test function for the hashString function
void testHashString()
{
    char tests[][MAX_WORD_LEN + 1] = {"BAD", "DAB", "GOODFELLOWS", "WRITERSHIP", "a", "A", "abcdefghijklmnopqrstuvwxyz", "1234567890!@#$%^&*()", "", ""};
    testFillAllPrintable(tests[8]);
    
    int i = 0;
    while (strlen(tests[i]) > 0)
    {
        printf("\"%s\" -> %u\n", tests[i], hashString(tests[i]));
        i++;
    }
}

// Test function for the calcUnigramLogProb function
void testCalcUnigramLogProb()
{
    const int SIZE = 10;
    unsigned int  counts[] = { 5 ,  50 ,   1,    1,   1234,   0 , 100, 1, 1        , UINT_MAX - 10000 };
    unsigned long totals[] = {10 , 100 , 100, 2000, 567890, 123 , 100, 1, ULONG_MAX, UINT_MAX         };
    
    for (int i = 0; i < SIZE; i++)
    {
        printf("%5u %7lu -> %9.6f\n", counts[i], totals[i], calcUnigramLogProb(counts[i], totals[i]));
    }
}

// Test function for the stripLower function
void testUppercaseAndStrip()
{
    char tests[][MAX_WORD_LEN + 1] = {"b", "B", "bad", "BAD", "BaD", "CAN'T", "well-done", "!L00K-", "1234567890", "abcdefghijklmnopqrstuvwxyz", "", ""};
    char dest[MAX_WORD_LEN + 1];
    testFillAllPrintable(tests[10]);
    
    int i = 0;
    while (strlen(tests[i]) > 0)
    {
        printf("\"%s\" (len %zu) -> ", tests[i], strlen(tests[i]));
        uppercaseAndStrip(dest, tests[i]);
        printf("\"%s\" (len %zu)\n", dest, strlen(dest));
        i++;
    }
}

int main(int argc, char** argv) {
    // If no command line input we print out a help message and also run test functions
    if (argc <= 1) {
        printf("Usage: Unigram <hash table size> [debug]\n\n");
        testHashString();
        printf("\n");
        testCalcUnigramLogProb();
        printf("\n");
        testUppercaseAndStrip();
        return 0;
    }
    if (argv[1] <= 0) {
        printf("ERROR: Table size must be positive!");
        return 0;
    }
    int printinfo = 0;
    if (argv[2] > 0) {
        printinfo = 1;
    }
    int tablesize = atoi(argv[1]);
    char *word[256];
    Node *hash[tablesize];
    Node *data = malloc(sizeof(Node));
    Node *data2 = malloc(sizeof(Node));
    for (int i = 0; i < tablesize; i++) {
        hash[i]=malloc((sizeof(Node)));
        hash[i]->word=malloc(sizeof(char)*256);
        for (int j = 0; j < (sizeof(char)*256); j++) {
            hash[i]->word[j] = NULL;
        }
        hash[i]->count = 0;
        hash[i]->next = NULL;
    }
    int key = 0;
    int total = 0;
    Node *current = NULL;
    while (scanf("%s", word) == 1) {
        scanf("%s", word);
        total++;
        char *word2 = malloc(sizeof(strlen(word)));
        uppercaseAndStrip(word2, word);
        key = (hashString(word2) % tablesize);

        if (hash[key]->count == 0) {
            for (int i; i < strlen(word2); i++) {
                data->word[i] = word2[i];
            }
            data->next = NULL;
            data->count = 1;
            hash[key] = data;
            if (printinfo == 1) {
                printf("%s -> hash %u, bucket %d, count 1 \n", word2, hashString(word2), key);
            }
        } else {
            current = hash[key];
            while (current != NULL) {
                if (strcmp(word2, current->word) == 0) {
                    current->count = (current->count + 1);
                    if (printinfo == 1) {
                        printf("%s -> hash %u, bucket %d, count %d \n", word2, hashString(word2), key, current->count);
                    }
                    break;
                }
                if (current->next == NULL) {

                    for (int i; i < strlen(word2); i++) {
                        data2->word[i] = word2[i];
                    }

                    data2->next = hash[key];
                    data2->count = 1;
                    hash[key] = data2;
                    if (printinfo == 1) {
                        printf("%s -> hash %u, bucket %d, count 1 \n", word2, hashString(word2), key);
                    }
                    break;
                }

                current = current->next;
            }
        }
    }
    for(int i=0; i<tablesize; i++)
    {
        current=hash[i];
        while(current !=NULL && current->count !=0)
        {
            printf("%.6f %s \n",calcUnigramLogProb(current->count,total),current->word);
            current=current->next;
        }
    }
}