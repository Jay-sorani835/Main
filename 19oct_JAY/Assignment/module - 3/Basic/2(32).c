//check the size of sum
#include<stdio.h>

int main()
{
    int a,b,s;
    printf("Enter the first number:");
    scanf("%d",&a);
    printf("Enter the second number:");
    scanf("%d",&b);
    s=a+b;
    printf("The sum of this data is %d and size of it is %d",s,sizeof(s));
}
