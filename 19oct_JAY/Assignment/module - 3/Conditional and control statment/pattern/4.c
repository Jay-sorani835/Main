#include<stdio.h>

int main()
{
    int i,j;
    for(i = 0;i < 6;i++){
        for(j = 0;j < 2*i+1;j++){
            printf(" ");
        } 
        for(j = 6 ; j >= 2*(i-2);j--){
            printf("* ");
        }
        printf("\n");
    }
    for(i = 0;i < 6;i++){
        for(j = 6;j >= 2*i+3;j--){
            printf(" ");
        }
        for(j = 0;j <= 2*i;j++){
            printf(" *");
        }
        printf("\n");
    }
    return 0;
}

