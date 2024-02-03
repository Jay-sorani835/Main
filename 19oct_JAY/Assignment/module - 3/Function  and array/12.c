#include<stdio.h>

int main(){
    char a[5][30];
    for(int i = 0;i < 5;i++){
        printf("Enter the name:");
        scanf("%s",&a[i]);
    }

    for(int i = 0;i < 5 ;i++){
        printf("\n%s",a[i]);
    }
    return 0;
}