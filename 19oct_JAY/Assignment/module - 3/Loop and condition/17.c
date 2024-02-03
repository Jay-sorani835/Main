#include<stdio.h>

int main()
{

    int n,i = 0,c = 0,count = 0;
    while(i != 5){
        printf("Enter the number:");
        scanf("%d",&n);
        if((n % 2) == 0)
            count++;
        else
            c++;
        i++;
    }
    printf("here %d even numbers and %d odd numbers",count,c);
    return 0;
}
