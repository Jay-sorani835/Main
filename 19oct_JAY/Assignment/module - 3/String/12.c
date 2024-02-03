#include<stdio.h>
#include<string.h>
int main(){
	char s[50], w[50];
	int n, a[50], i, j, k=0, l, found=0, t=0;
	printf("Enter a string : ");
	gets(s);
	printf("Enter a word : ");
	gets(w);
	for(i=0; s[i]; i++){
		if(s[i]==' '){
			a[k++]=i;
		}
	}
	a[k++]=i;
	j=0;
	for(i=0; i<k; i++){
		n=a[i]-j;
		if(n==strlen(w)){
			t=0;
			for(l=0; w[l]; l++){
				if(s[l+j]==w[l]){
					t++;
				}
			}
			if(t==strlen(w)){
				found++;
			}
		}
		j=a[i]+1;
	}
	printf("total is word = %d",found);
	return 0;
}