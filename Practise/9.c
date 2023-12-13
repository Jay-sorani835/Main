//sum of given number
#include<stdio.h>

int main()
{
    int n[10],i,sum = 0;
    for(i=0;i<10;i++){
        printf("Enter the number:");
        scanf("%d",&n[i]);
        sum = sum + n[i];
    }
    printf("The sum is %d",sum);
    return 0;
}
