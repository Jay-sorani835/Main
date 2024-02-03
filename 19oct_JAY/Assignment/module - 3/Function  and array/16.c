#include<stdio.h>

int main()
{
    int a[5],s=0,n;
    for(int i = 0;i < 5;i++){
        printf("Enter the Number:");
        scanf("%d",&a[i]);
        s = s + a[i];
    }
    printf("The sum is %d",s);
    return 0;
}
