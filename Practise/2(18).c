//calculate annual salary
#include<stdio.h>

int main()
{
    int m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,t;
    printf("Enter the first month salary:");
    scanf("%d",&m1);
    printf("Enter the second month salary:");
    scanf("%d",&m2);
    printf("Enter the third month salary:");
    scanf("%d",&m3);
    printf("Enter the fourth month salary:");
    scanf("%d",&m4);
    printf("Enter the fifth month salary:");
    scanf("%d",&m5);
    printf("Enter the sixth month salary:");
    scanf("%d",&m6);
    printf("Enter the seventh month salary:");
    scanf("%d",&m7);
    printf("Enter the eigthth month salary:");
    scanf("%d",&m8);
    printf("Enter the nineth month salary:");
    scanf("%d",&m9);
    printf("Enter the tenth month salary:");
    scanf("%d",&m10);
    printf("Enter the eleventh month salary:");
    scanf("%d",&m11);
    printf("Enter the twelve month salary:");
    scanf("%d",&m12);
    t=m1+m2+m3+m4+m5+m6+m7+m8+m9+m10+m11+m12;
    printf("\nThe annual salary is %d",t);
    return 0;
}
