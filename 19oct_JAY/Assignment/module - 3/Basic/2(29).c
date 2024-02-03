//Convert minutes into seconds and hours
#include<stdio.h>

void main()
{
    int m,s;
    float h;
    printf("Enter the minutes:");
    scanf("%d",&m);
    s=m*60;
    h=(float)m/60;
    printf("\nAfter converting seconds = %d and hours = %.2f",s,h);
}
