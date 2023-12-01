//Convert days into months
#include<stdio.h>

void main()
{
    int d;
    float m;
    printf("Enter the days:");
    scanf("%d",&d);
    m=(float)d/30;
    printf("after converting days into month,The month is:%f",m);
}
