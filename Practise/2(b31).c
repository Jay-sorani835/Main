//if 1,3,5,7,8,10,12 then print 31 days , otherwise print 30 days if 2 than print 28 or in leap year 29 days
#include<stdio.h>

int main()
{
    int m,y;
    printf("Enter the month number to find days:");
    scanf("%d",&m);
    if(m == 1 || m == 3 || m == 5 || m == 7 || m ==8 || m == 10 || m == 12)
        printf("The days of this month is 31 days.");
    else if(m == 2){
        printf("Enter the year:");
        scanf("%d",&y);
        if((y%4) == 0)
            printf("The days of this month is 29 days.");
        else
            printf("The days of this month is 28 days.");
    }
    else
        printf("The days of this month is 30 days.");
    return 0;
}
