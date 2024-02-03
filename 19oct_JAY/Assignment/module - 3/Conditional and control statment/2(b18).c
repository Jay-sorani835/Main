//calculate profit and loss
#include<stdio.h>

int main()
{
    int s,b;
    printf("Enter your selling price in a year:");
    scanf("%d",&s);
    printf("Enter your buying price in a year:");
    scanf("%d",&b);
    if(s>b)
        printf("Your profit is %d",(s-b));
    else
        printf("Your loss is %d",(b-s));
    return 0;
}
