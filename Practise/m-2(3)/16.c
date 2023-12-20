#include<stdio.h>

int main()
{
    int n,i = 1,s = 0;
    printf("Enter the number for finding to that number:");
    scanf("%d",&n);
    while(n+1 != i){
        s = s + i;
        i++;
    }
    printf("The sum is %d",s);
    return 0;
}
