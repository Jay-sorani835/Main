#include<stdio.h>

int max(){
    char str[20];
    int c = 0;
    printf("Enter the string:");
    gets(str);
    for(int i = 0;i < str[i];i++){
        if(str[i] >= 65 && str[i] <= 90 || str[i] >= 97 && str[i] <= 122){
            c++;
            printf("\nThe character is %c",str[i]);
        }
    }
    printf("\nThe Maximum character in string is %d",c);
}
int main(){
    max();
    return 0;
}