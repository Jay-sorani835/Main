//check the whether condition
#include<stdio.h>

int main()
{
    int t;
    printf("Enter the temperature in centigrade:");
    scanf("%d",&t);
    if(t<0)
        printf("\nFreezing Whether.");
    else if(t==0 || t<=10)
        printf("\nVery cold whether.");
    else if(t>10 || t<=20)
        printf("\nCold whether.");
    else if(t>20 || t<=30)
        printf("\nNormal temperature.");
    else if(t>30 || t<40)
        printf("\nIt is Hot.");
    else
        printf("\nIt is very hot.");
    return 0;
}
