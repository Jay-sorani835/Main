#include<stdio.h>
#include<string.h>

int  main(){
    char str[10];
    int v = 0,c = 0;
    printf("Enter the string:");
    gets(str);
    int len = strlen(str);
    for(int i = 0;i < len;i++){
        if(str[i] == 'a' || str[i] == 'A' || str[i] == 'e' || str[i] == 'E' || str[i] == 'i' || str[i] == 'I' || str[i] == 'o' || str[i] == 'O' || str[i] == 'u' || str[i] == 'U')
        v++;
        else
        c++;
    }
    printf("In this string %d vowels and %d consonants",v,c);
    return 0;
}