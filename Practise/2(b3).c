//check year is leap or not
#include<stdio.h>

int main()
{
    int year;
    printf("Enter the year:");
    scanf("%d",&year);
    if((year % 4) == 0)
        printf("\n%d is Leap year.",year);
    else
        printf("\n%d is not Leap year.",year);
    return 0;
}
