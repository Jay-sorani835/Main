//print factorial number
#include<stdio.h>

int main()
{
    int fact=1,i;
    int n;
    printf("Enter the number for finding the factorial it is find only upto 10 factorial:");
    scanf("%d",&n);
    for(i=n;i > 0;i--){
        fact = fact * i;
    }
    printf("The factorial of %d is %d",n,fact);
    return 0;
}
