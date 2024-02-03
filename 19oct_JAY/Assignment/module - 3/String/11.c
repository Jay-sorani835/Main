#include<stdio.h>
#include<string.h>

int convert(){
    char str[20];
    printf("Enter the string:");
    gets(str);
    for(int i = 0;i < str[i];i++){
        if(str[i] >= 'A' && str[i] <= 'Z'){
            str[i] = str[i] + 32;
        }
        else if(str[i] >= 'a' && str[i] <= 'z')
        str[i] = str[i] - 32;
        else
        return 0;
    }
    printf("The String After converting is %s",str);
    return 0;
}
int main(){
    convert();
    return 0;
}