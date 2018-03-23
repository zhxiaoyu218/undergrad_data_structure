/****************************
   CSC172 lab8 
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int mystrcmp(char *s, char *t);
void mystrcat(char *dest, char * source);
void mystrcpy(char *s, char *dest);

int main(int argc, char* argv[])
{
	int i;
	char hello[30];

	int a[10];
	mystrcpy("\"hello,\"",hello);
	for (i = 0; i < argc; i++)
	{
		
		if (mystrcmp(argv[i],"xiaoyu_zhang")==0)
		{
			mystrcat(hello,argv[i]);			
			printf("argument %d: %s\n",i,hello);
		}
		else{
		
			printf("argument %d: %s\n", i, argv[i]);
		}
	
	}

	printf("\nstart to print array \n");

	for (i = 0; i < 10; i++)
	{
		a[i] = i*i;
	}

	for (i = 0; i < 10; i++)
	{
		printf("array %d:  %d\n", i , *(a+i));
	}

}


int mystrcmp(char *s, char *t)
{
	while(*s == *t)
    {
        if(*s == '\0')
            return 0;
         
        s++;
        t++;
    }
    return *s- *t;
}

void mystrcat(char *dest, char * source){
	
	while(*dest)
		dest++;
	while(*dest++ = *source++);
}
void mystrcpy(char *s, char *dest)
{
	while ((*dest++ = *s++) != '\0' );
}