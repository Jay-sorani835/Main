//get 10 no. and find how many even number,how many odd number,sum of even number,sum of odd number
#include<stdio.h>

int main(){
    int i,n[10],sum=0,count = 0;
    for(i = 0;i<10;i++){
        printf("Enter the number:");
        scanf("%d",&n[i]);
    }
    for(i=0;i<10;i++){

        if((n[i]%2)==0){
                sum = sum + n[i];
        }
        else
            count = count + n[i];
   }
    printf("\nThe sum of even numbers is : %d",sum);
    printf("\nThe sum of odd numbers is : %d",count);
    return 0;
}
