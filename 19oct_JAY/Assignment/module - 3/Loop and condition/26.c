//1+(1+2)+....+(1+2+..+n)
#include<stdio.h>

int main()
{
    int n,i,sum = 0;
    printf("Enter the term when you find sum from 1 to that term:");
    scanf("%d",&n);
    for(i = 0;i <= n;i++){
        sum = sum + (i+1);
    }
    printf("sum of 1+(1+2)+(1+2+3)+...+(1+2+3+...+%d) is %d",n,sum);
    return 0;
}

