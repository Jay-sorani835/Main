//g.s.= s + hra + da
#include<stdio.h>

int main()
{
    int s;
    printf("Enter the salary:");
    scanf("%d",&s);
    if(s <= 10000)
        printf("Gross salary is %.2f",((float)s+(.2*(float)s)+(.8*(float)s)));
    else if(s > 11000 && s <=20000)
        printf("Gross salary is %.2f",((float)s+(.25*(float)s)+(.9*(float)s)));
    else
        printf("Gross salary is %.2f",((float)s+(.3*(float)s)+(.95*(float)s)));
    return 0;
}
