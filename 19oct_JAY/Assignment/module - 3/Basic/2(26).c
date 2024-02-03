
#include<stdio.h>

int main()
{
    float c,f;
    printf("Enter the temperature in fahrenheit:");
    scanf("%f",&f);
    c=(f-32)*5/9;
    printf("\nThe temperature in celsius is %.2f",c);
    return 0;
}
