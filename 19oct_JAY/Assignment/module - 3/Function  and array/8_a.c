#include<stdio.h>
struct employee{
    int empno,age;
    char empname[20],address[30];
}emp;
int main(){
    printf("Enter the empno,empname,age,address:");
    scanf("%d %s %d %s",&emp.empno,&emp.empname,&emp.age,&emp.address);
    printf("The Employee number is : %d\n",emp.empno);
    printf("The Employee name is : %s\n",emp.empname);
    printf("The Employee name is : %d\n",emp.age);
    printf("The Employee name is : %s\n",emp.address);    
}