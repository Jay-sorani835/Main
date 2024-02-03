#include<stdio.h>

int main(){
    char str[10];
    printf("Enter the string:");
    scanf("%s",&str);
    for(int i = 0; i < str[i-1];i++){
        printf("%c ", str[i]);
    }
    return 0;
}