//check first number is negative and second number is positive
#include<stdio.h>

int main()
{
    int n1,n2;
    printf("Enter the number 1:");
    scanf("%d",&n1);
    printf("Enter the second number:");
    scanf("%d",&n2);
    if(n1<0 && n2>0)
        printf("True.");
    else
        printf("False.");
    return 0;
}
