#include<stdio.h>

int main()
{
    int i,n,rem,s = 0;
    printf("Enter the number:");
    scanf("%d",&n);
    while(n != 0){
        rem = n % 10;
        if(rem > s)
            s = rem;
        n = n / 10;
    }
    printf("%d is max",s);
    return 0;
}
