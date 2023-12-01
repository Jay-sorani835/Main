//convert years into days and days into years
#include<stdio.h>

void main()
{
    int y,d;
    printf("Enter the year: ");
    scanf("%d",&y);
    d=y*365;
    printf("After converting Days:%d and year:%d",d,y);
}
