#include <stdio.h>

void main()
{
    char a[10];
    int c = 0;
    printf("Enter the string:");
    scanf("%s",&a);
    for(int i = 0;a[i] != 0; i++){
        c++;
    }
    printf("The size of the string is %d",c);
}