//wap for simple calculator
#include<stdio.h>

void main()
{
    int a,b,c;
    float d;
    printf("Enter the number 1:");
    scanf("%d",&a);
    printf("Enter the number 2:");
    scanf("%d",&b);
    c=a+b;
    printf("The addition is : %d",c);
    c=a-b;
    printf("\nThe Subtraction is : %d",c);
    c=a*b;
    printf("\nThe Multiplication is : %d",c);
    d=(float)(a/b);
    printf("\nThe division is : %f",d);
    d=(float)(a%b);
    printf("\nThe modulo is : %f",d);
}
