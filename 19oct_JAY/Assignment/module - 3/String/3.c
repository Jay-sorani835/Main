#include<stdio.h>

int main(){
    char str[10];
    int len = 0;
    printf("Enter the string:");
    scanf("%s",&str);
    for(int i = 0; i <  str[i];i++){
        len++;
    }
    for(int i = len;i >= 0;i--){
        printf("%c ",str[i]);
    }
    return 0;
}