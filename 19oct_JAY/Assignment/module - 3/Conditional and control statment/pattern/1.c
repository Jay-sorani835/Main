/*
1
10
101
1010
10101*/
#include<stdio.h>

int main()
{
    int i,j;
    for(i = 1;i <= 5; i++){
        printf("\n");
        for(j=1;j<=i;j++)
            printf(" %d",j%2);
    }
        return 0;
}
