//convert country name into abbreviate name
#include<stdio.h>
#include<ctype.h>
int main()
{
    char c1,c2;
    printf("Enter the country name :");
    scanf("%c%c",&c1,&c2);
    printf("\nAbbreviated name of country is %c%c",toupper(c1),toupper(c2));
    return 0;
}
