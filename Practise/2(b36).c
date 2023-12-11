//get the unit of electricity
#include<stdio.h>

int main()
{
    int u;
    printf("Enter the unit:");
    scanf("%d",&u);
    if(u<=50)
        printf("The amount is %.2f",((float)u*.5)+((float)u*.2));
    else if(u>=51 && u<=150)
        printf("The amount is %.2f",((float)u*.75)+((float)u*.2));
    else if(u>150 && u<=250)
        printf("The amount is %.2f",((float)u*1.20)+((float)u*.2));
    else
        printf("The amount is %.2f",((float)u*1.50)+((float)u*.2));
    return 0;
}
