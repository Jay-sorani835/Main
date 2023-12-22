#include<stdio.h>

int main()
{
    int i,j,a=1;
    for(i = 0 ; i<=4;i++){
        for(j = 0;j<=9;j++){
            printf(" %d",a);
            a++;
        }
        if((j%10)==0)
            printf("\n");
    }
    return 0;
}
