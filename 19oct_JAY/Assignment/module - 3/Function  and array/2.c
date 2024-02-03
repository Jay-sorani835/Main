//make simple calculator using switch case
#include<stdio.h>

int main()
{
    int a,b,p;
    float c;
    printf("Enter the first number:");
    scanf("%d",&a);
    printf("Enter the second number:");
    scanf("%d",&b);
    printf("Enter what you perform 1.Addition 2.Subtraction 3.Multiplication 4.Division 5.Modulo: ");
    scanf("%d",&p);
    switch(p)
    {
        case 1 : printf("Addition = %d",a+b);
        break;
        case 2 : printf("Subtraction = %d",a-b);
        break;
        case 3 : printf("Multiplication = %d",a*b);
        break;
        case 4 :
            c = (float)a/(float)b;
            printf("Division = %.2f",a/b);
        break;
        case 5 : printf("Modulo = %d",a%b);
        break;
    }
    return 0;
}

