#include <stdio.h>

void main()
{
    int i,n,fact = 1;
    printf("Enter the number for finding its factorial:");
    scanf("%d",&n);
    i = n;
    while(i != 0){
        fact = fact * i;
        i--;
    }
    printf("The factorial of %d is %d",n,fact);
}
