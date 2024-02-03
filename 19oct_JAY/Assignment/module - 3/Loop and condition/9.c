
//sum of given number
#include<stdio.h>

int main()
{
    int n,s1 = 0,s;
    printf("Enter the number:");
    scanf("%d",&n);
    while(n != 0){
        s = n % 10;
        s1 =  s1 + s;
        n = n/10;
    }
    printf("The sum is %d",s1);
    return 0;
}
