//swap 2 number using multiplication and division
#include<stdio.h>

void main()
{
    int n1,n2;
    printf("Enter the  number 1:");
    scanf("%d",&n1);
    printf("Enter the number 2:");
    scanf("%d",&n2);
    n1=n1*n2;
    n2=n1/n2;
    n1=n1/n2;
    printf("After swapping no.1:%d and no.2:%d",n1,n2);
}
