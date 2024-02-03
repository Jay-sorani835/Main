#include<stdio.h>
int n,max = 0;
int Max()
{
    printf("Enter the size of an array:");
    scanf("%d",&n);
    int a[n];
    for(int i = 0;i < n;i++){
        printf("Enter a[%d]:",i);
        scanf("%d",&a[i]);
        if(a[i] > max)
            max = a[i];
    }
    return max;;
}
int main()
{
    printf("The maximum number from array is %d",Max());
    return 0;
}
