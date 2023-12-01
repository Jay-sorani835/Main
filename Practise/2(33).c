//Read Integer and Print First Three Powers
#include<stdio.h>

void main()
{
    int n,n1,n2,n3;
    printf("Enter the integer number:");
    scanf("%d",&n);
    n1=n;
    n2=n1*n;
    n3=n2*n;
    printf("The First three power is %d,%d,%d ",n1,n2,n3);
}
