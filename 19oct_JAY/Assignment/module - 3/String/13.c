#include<stdio.h>
#include<string.h>

int main(){
    char str[20];
    printf("Enter the string:");
    gets(str);
    int n = strlen(str);
    char alpha[n];
    for(int i = 0;i < str[i];i++){
        if(str[i] >= 'A' && str[i] <= 'Z' || str[i] >= 'a' && str[i] <= 'z'){
            alpha[i] = str[i];
        }
    }
    
    printf("After removing the non alphabets the string is : %s",alpha);
    return 0;
}