//check character is uppercase , lowercase , digit , special character
#include<stdio.h>

int main()
{
    char c;
    printf("Enter the character:");
    scanf("%c",&c);
    if(c>=65 && c<=90)
        printf("\nThe character is in Uppercase.");
    else if(c>=97 && c<=122)
        printf("\nThe character is in Lowercase.");
    else if(c>=48 && c<=57)
        printf("\nThe character is Digit.");
    else
        printf("\nThe character is Special character.");
    return 0;
}
