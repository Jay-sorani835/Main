#include<stdio.h>

int main()
{
    int i=1,j,c=1;
    while(i != 10){
        for(j = 0 ;j<i;j++)
            printf("%d",c);
        c++;
    }
    return 0;
}
