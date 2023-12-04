//check number is equal or not
#include<stdio.h>

int main()
{
    int n1,n2;
    printf("Enter the first number:");
    scanf("%d",&n1);
    printf("Enter the second number:");
    scanf("%d",&n2);
    if(n1==n2)
        printf("\nThey are equal.");
    else
        printf("\nThey are not equal.");
    return 0;
}
