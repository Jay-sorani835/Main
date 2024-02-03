//print name,age,birth date,address
#include<stdio.h>

void main()
{
    int a;
    char str[20],str1[15],str2[50];
    printf("Enter your name:");
    gets(str);
    printf("\nEnter your birth date:");
    gets(str1);
    printf("\nEnter your address:");
    gets(str2);
    printf("\nEnter Your age :");
    scanf("%d",&a);
    printf("\nName : %s",str);
    printf("\nBirthdate : %s",str1);
    printf("\nAge : %d",a);
    printf("\nAddress : %s",str2);
}
