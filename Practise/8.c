//find max number
#include<stdio.h>

int main()
{
    int i,n[10],max;
    for(i=0;i<10;i++){
        printf("Enter the number:");
        scanf("%d",&n[i]);
    }
    for(i=0;i<10;i++){
        if(max<n[i])
            max = n[i];
    }
    printf("The maximum number is %d",max);
    return 0;
}
