//(1/2)-(2/3)+(3/4)......(n-1/n)
#include<stdio.h>

int main()
{
    float n,sum = 0;
    printf("Enter the term when you find sum from 1 to that term:");
    scanf("%f",&n);
    for(int i = 1;i <= n;i++){
            if((i%2) == 1)
                sum = sum + ((i/i+1));
            else
                sum = sum - (i/(i+1));
    }
    printf("sum of (1/2)-(2/3)+(3/4)....(%d-1)/%d is %f",n,n,sum);
    return 0;
}
