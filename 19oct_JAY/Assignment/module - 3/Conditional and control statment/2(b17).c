//check the triangle from given value of angle
#include<Stdio.h>

int main()
{
    float a1,a2,a3;
    printf("Enter the first angle:");
    scanf("%f",&a1);
    printf("Enter the second angle:");
    scanf("%f",&a2);
    printf("Enter the third angle:");
    scanf("%f",&a3);
    if((a1+a2+a3) == 180)
        printf("The triangle is formed.");
    else
        printf("Check the value of angle because it not match to sum of angles of triangle.");
    return 0;
}
