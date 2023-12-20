#include<stdio.h>

int main()
{
    int n,a;
    printf("Enter the number:");
    scanf("%d",&n);
    a = n % 10;
    while(n >= 10){
        n = n / 10;
    }
    a = a + n;
    printf("\nThe sum of first and last digit is %d ",a);
    return 0;
}
