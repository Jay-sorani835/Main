//print factorial number
#include<stdio.h>

int main()
{
    int fact=1,i,n;
    printf("Enter the number for finding the factorial:");
    scanf("%d",&n);
    for(i=1;i<=n;i++){
        fact = fact * i;
    }
    printf("The factorial of %d is %d",n,fact);
    return 0;
}
