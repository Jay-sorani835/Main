//find area and circumference of cicle
#include<stdio.h>
#define PI 3.14
void  main()
{
    float r,ar,cr;
    printf("Enter the radius of circle:");
    scanf("%f",&r);
    ar=PI * r * r;
    cr = 2 * PI * r;
    printf("The Area is %f and the Circumference is %f. ",ar,cr);
}
