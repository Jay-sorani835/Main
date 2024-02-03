#include <stdio.h>

int main()
{
    char a[10];
    int c = 0;
    printf("Enter the string:");
    gets(a);
    for(int i = 0;i < a[i];i++){
        c++;
    }
    for(int i = c; i >= 0;i--){
        printf("%c ",a[i]);
    }
    return 0;
}