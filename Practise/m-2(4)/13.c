#include<stdio.h>

int main()
{
    int a[5],i,e = 0,o = 0;
    for(i = 0;i < 5;i++){
        printf("Enter the element:");
        scanf("%d",&a[i]);
        if((a[i]%2) == 0)
            e++;
        else
            o++;
    }
    printf("The even element is %d and odd element is %d",e,o);
    return 0;
}
