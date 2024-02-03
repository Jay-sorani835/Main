#include<stdio.h>
#include<string.h>

void rev(char a[]){
	int len = strlen(a);
	for(int i=0; i<len/2; i++){
		int temp = a[i];
		a[i] = a[len-1-i];
		a[len-1-i] = temp;
	}
}

int main(){
    int b = 1;
	char c[20];
	printf("Enter a string : ");
	gets(c);
	rev(c);
    int len = strlen(c);
	int i = 0,j = len-1;
	while(i<j){
		if(c[i] != c[j]){
			b = 0;
		}
		i++;
		j--;
	}
	if(b == 1){
		printf("\nstring is palindrome");
	}
	else{
		printf("\nstring is not palindrome");
	}
	return 0;
}