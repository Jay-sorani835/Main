//swap without using third variable
#include<stdio.h>

void main()
{
    int a,b,c;
    printf("Enter the first value:");
    scanf("%d",&a);
    printf("Enter the second value:");
    scanf("%d",&b);
    printf("Before swapping using third variable the value is %d and %d. ",a,b);
    a=a+b;
    b=a-b;
    a=a-b;
    printf("\nAfter the swapping using the third variable the value is %d and %d. ",a,b);
}

