#include<stdio.h>

int main()
{
    int m,n1=1,n2=0,n3=-1;
    printf("Enter the number:");
    scanf("%d",&m);
    if(m>0)
        printf("n is %d",n1);
    else if(m=0)
        printf("n is %d",n2);
    else
        printf("n is %d",n3);
    return 0;
}
