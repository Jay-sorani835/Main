#include<stdio.h>

int main()
{
    int i,j,a[5];
    for(i = 0; i <= 4;i++){
        printf("Enter the number of a[%d]:",i);
        scanf("%d",&a[i]);
    }
    printf("reverse of entering is:");
    for(i = 4;i >= 0;i--){
        printf("\n %d",a[i]);
    }
    return 0;
}
