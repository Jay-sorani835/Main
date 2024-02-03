#include<stdio.h>
char str[20];
int len(){
    int len = 0;
    printf("Enter the string:");
    scanf("%s",&str);
    for(int i = 0;i < str[i];i++){
        len++;
    }

    printf("The Size of the string is %d",len);
    return 0;
}
int main(){
    len();
    return 0;
}