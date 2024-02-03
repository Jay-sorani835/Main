#include<stdio.h>

int main()
{
    int n;
    printf("Enter the number for finding factorial:");
    scanf("%d",&n);
    factorial(n);
    return 0;
}
void factorial(int a)
{
    int i,fact = 1;
    for(i = 1;i <= a;i++){
        fact = fact * i;
    }
    printf("The factorial of %d is %d",a,fact);
}
