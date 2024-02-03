#include<stdio.h>
struct employee{
    int empno[5],age[5];
    char empname[5][20],address[5][30];
}emp;
int main(){
    for(int i = 0;i < 5;i++){
        printf("Enter the empno,empname,age,address for %d person:",i+1);
        scanf("%d %s %d %s",&emp.empno[i],&emp.empname[i],&emp.age[i],&emp.address[i]);
    }
    for(int i = 0;i < 5;i++){
        printf("The Employee %d number is : %d\n",i+1,emp.empno[i]);
        printf("The Employee %d name is : %s\n",i+1,emp.empname[i]);
        printf("The Employee %d age is : %d\n",i+1,emp.age[i]);
        printf("The Employee %d address is : %s\n",i+1,emp.address[i]); 
    }
    return 0;   
}