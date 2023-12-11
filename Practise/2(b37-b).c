//check vowel or consonant using switch case
#include<stdio.h>

int main()
{
    int n;
    char c;
    printf("Enter the character:");
    scanf("%c",&c);
    if(c=='a' || c=='e' || c == 'i' || c == 'o' || c == 'u'|| c =='A' || c=='E' || c=='I'|| c == 'O' || c == 'U')
        n = 1;
    else
        n=2;
    switch(n)
    {
        case 1: printf("Character is vowel.");
        break;
        case 2: printf("Character is Consonant.");
        break;
    }
    return 0;
}
