#include<stdio.h>
#include<string.h>

int main(){
    char str[20];
    int d = 0,a = 0,s = 0;
    printf("Enter the string:");
    gets(str);
    int len = strlen(str);
    for(int i = 0;i < len;i++){
        if(str[i] >= 65 && str[i] <= 90 || str[i] >= 97 && str[i] <= 122)
        a++;
        if(str[i] >= 48 && str[i] <=57)
        d++;
        if(str[i] >= 32 && str[i] <= 47 || str[i] >= 58 && str[i] <= 64 || str[i] >= 91 && str[i] <= 96 || str[i] >= 123 && str[i] <= 126)
        s++;
    }
    printf("In this string albhabets is %d , digits is %d and special character is %d",a,d,s);
    return 0;
}