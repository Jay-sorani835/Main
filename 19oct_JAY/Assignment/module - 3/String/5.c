#include<stdio.h>
#include<string.h>

int cmp(){
    int c = 0,i;
    char str1[20],str2[20];
    printf("Enter the first String:");
    gets(str1);
    printf("Enter the second string:");
    gets(str2);
    if(strlen(str1) == strlen(str2)){
        for( i = 0;i <=  strlen(str2);i++){
            if(str1[i] == str2[i])
            c++;
        }
        if(c == i){
            printf("String is Matched.");
        }
        else
        printf("String is not Matched.");
    }
    else
    printf("String is not Matched.");
    return 0;
}
int main(){
    cmp();
    return 0;
}