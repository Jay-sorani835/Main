#include<stdio.h>
#include<string.h>

int main(){
    char str1[20],str2[20];
    printf("Enter First string:");
    gets(str1);
    printf("Enter Second string:");
    gets(str2);
    printf("After combining the string is : %s",strcat(str1,str2));
    return 0;
}