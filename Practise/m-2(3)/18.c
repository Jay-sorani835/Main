#include<stdio.h>

int main()
{
    int n,i,s=1;
    printf("Enter the value for find it's multiplicative table:");
    scanf("%d",&n);
    for(int i = 1;i<=10;i++){
        s = n*i;
        printf("\n%d * %d = %d",n,i,s);
    }
    return 0;
}
