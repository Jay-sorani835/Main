//make simple calculator using conditional statement
#include<stdio.h>

int main()
{
    int a,b,c,p;
    printf("Enter the first number:");
    scanf("%d",&a);
    printf("Enter the second number:");
    scanf("%d",&b);
    printf("Enter what you perform 1.Addition 2.Subtraction 3.Multiplication 4.Division 5.Modulo: ");
    scanf("%d",&p);
    if(p==1)
        printf("\nThe addition is %d",a+b);
    else if(p==2)
        printf("\nThe subtraction is %d",a-b);
    else if(p==3)
        printf("\nThe multiplication is %d",a*b);
    else if(p==4)
        printf("\nThe division is %.2f",((float)a/(float)b));
    else
        printf("\nThe modulo is %d",a%b);
    return 0;
}
