//find fibonaci series
#include<stdio.h>

int main()
{
    int n1=0,n2=1,n,i;
    int temp = n1 + n2;
    printf("Enter the term : ");
    scanf("%d",&n);
    printf("Fibonacci Series: %d , %d ", n1, n2);
    for(i=3;i<=n;i++){
        printf(" , %d",temp);
        n1 = n2;
        n2 = temp;
        temp = n1 + n2;
    }
    return 0;
}
