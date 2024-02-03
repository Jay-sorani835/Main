//get monthly salary and deduct 10% for premium and 10% for loan installment
#include<stdio.h>

int main()
{
    int s;
    float rs,rm,r;
    printf("Enter the your monthly salary:");
    scanf("%d",&s);
    rs=.1*s;
    rm=.1*s;
    r=s-rm-rs;
    printf("\nThe remaining salary is %.2f",r);
    return 0;
}
