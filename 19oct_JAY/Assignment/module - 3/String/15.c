#include<stdio.h>
#include<string.h>

int main(){
    int minindex = 0,maxindex = 0, max = 0, min = 0,len,i,j,k;
    char str[50], substr[50][50];
    printf("Enter the string:");
    gets(str);
    while(str[k] != 0){
        j = 0;
        while(str[k] != ' ' && str[k] != 0){
            substr[i][j] = str[k];
            i++;
            j++;
        }
        substr[i][j] = 0;
        i++;
        if(str[k] != 0)
        k++;
    }
    len = i;
    max = strlen(substr[0]);
    min = strlen(substr[0]);
    for(i = 0;i < len;i++){
        int a = strlen(substr[i]);
        if(a > max){
            max = a;
            maxindex = i;
        }
        if(a < min){
            min = a;
            minindex = i;
        }
    }
    printf("Largest word is : %s\n",substr[maxindex]);
    printf("Smallest word is : %s",substr[minindex]);
    return 0;
}