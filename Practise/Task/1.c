#include <stdio.h>

int main()
{
    int n,i;
    printf("Enter the size of an array:");
    scanf("%d",&n);
    int a[n];
    for( i = 0;i < n; i++){
        printf("Enter the value of a[%d]:",i+1);
        scanf("%d",&a[i]);
    }
    for( i = 0;i < n;i++){
        printf("\nThe value of a[%d] : %d",i,a[i]);
    }
    return 0;
}