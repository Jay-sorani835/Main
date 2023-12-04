//calculate insurance premium
#include<stdio.h>

int main()
{
    int s;
    float ip;
    printf("Enter your salary:");
    scanf("%d",&s);
    ip=.1*s;
    printf("\nThe insurance premium based is %.2f ",ip);
    return 0;
}
