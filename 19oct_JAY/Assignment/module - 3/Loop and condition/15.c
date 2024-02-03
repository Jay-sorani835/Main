#include<stdio.h>

int main()
{
    int n,i,s=0;

    while(i != 10){
        printf("Enter the number:");
        scanf("%d",&n);
        s = s + n;
        i++;
    }
    printf("The sum of total number is %d",s);
    return 0;
}
