//Accept 5 employees name and salary and count average and total salary
#include<stdio.h>

void main()
{
  char e1[10],e2[10],e3[10],e4[10],e5[10];
  int s1,s2,s3,s4,s5,total;
  float avg;
  printf("Enter the name of First Employee :");
  gets(e1);
  printf("Enter the name of Second Employee :");
  gets(e2);
  printf("Enter the name of Third Employee :");
  gets(e3);
  printf("Enter the name of fourth Employee :");
  gets(e4);
  printf("Enter the name of Fifth Employee :");
  gets(e5);
  printf("Enter the salary of first employee:");
  scanf("%d",&s1);
  printf("Enter the salary of second employee:");
  scanf("%d",&s2);
  printf("Enter the salary of third employee:");
  scanf("%d",&s3);
  printf("Enter the salary of fourth employee:");
  scanf("%d",&s4);
  printf("Enter the salary of fifth employee:");
  scanf("%d",&s5);
  avg=(float)(s1+s2+s3+s4+s5)/5;
  total=s1+s2+s3+s4+s5;
  printf("The Total is %d and average is %f",total,avg);
}
