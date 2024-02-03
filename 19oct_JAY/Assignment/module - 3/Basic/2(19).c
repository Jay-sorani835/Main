//find compound interest
#include<stdio.h>
#include<math.h>

int main()
{

    int p,t;
    float r,CI,a;
    printf("Enter the principal amount:");
    scanf("%d",&p);
    printf("Enter the time:");
    scanf("%d",&t);
    printf("Enter the rate:");
    scanf("%f",&r);
    CI = pow(p*(1 + (r/100)),t);
    a = CI - p;
    printf("compound interest of monthly %.2f",CI);
    return 0;
}
