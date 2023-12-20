//1+2+....+n
#include<stdio.h>

int main()
{
    int n,i,sum = 0;
    printf("Enter the term when you find sum from 1 to that term:");
    scanf("%d",&n);
    for(i = 1;i <= n;i++){
        sum = sum + i;
    }
    printf("sum of 1+2+...+%d is %d",n,sum);
    return 0;
}
