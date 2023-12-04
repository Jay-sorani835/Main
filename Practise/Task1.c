//assignment operator ( += , -= , *= , /= , % =)
#include<stdio.h>

int main()
{
    int n1,n2,d;
    printf("Enter the number 1 :");
    scanf("%d",&n1);
    printf("Enter the number 2:");
    scanf("%d",&n2);
    n1+=n2;
    printf("\nThe assignment operator += of this number is : %d",n1);
    n1-=n2;
    printf("\nThe assignment operator -= of this number is : %d",n1);
    n1*=n2;
    printf("\nThe assignment operator *= of this number is : %d",n1);
    n1/=n2;
    printf("\nThe assignment operator /= of this number is : %d",n1);
    n1%=n2;
    printf("\nThe assignment operator moduloequal of this number is : %d",n1);
    return 0;
}
