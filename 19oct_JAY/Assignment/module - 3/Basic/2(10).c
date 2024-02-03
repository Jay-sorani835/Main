//.find the area of a rectangular prism
#include<stdio.h>

void main()
{
    float w,l,h,ar;
    printf("Enter the width , height and length of rectangle prism :");
    scanf("%f %f %f",&w,&h,&l);
    ar = 2 *((w*l)+(w*h)+(l*h));
    printf("The area of rectangle prism is %f",ar);
}
