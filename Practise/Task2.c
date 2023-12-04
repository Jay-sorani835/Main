//convert days into week,months,year
#include<stdio.h>

int main()
{
    int d;
    printf("Enter the days :");
    scanf("%d",&d);
    printf("\nThe week of those day is : %d",d/7);
    printf("\nThe months of those day is : %d",d/30);
    printf("\nThe year of those day is : %d",d/365);
}
