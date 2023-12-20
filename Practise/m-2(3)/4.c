//print table up to given number
#include<stdio.h>

int main()
{
    int n,i,u,nl;
    printf("Enter the number for make table:");
    scanf("%d",&n);
    printf("Enter the number which you want upto find this number:");
    scanf("%d",&u);
    for(i=1;i<=u;i++){
            nl = n * i;
        printf("\n%d * %d = %d",n,i,nl);
    }
    return 0;
}
