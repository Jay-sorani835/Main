#include<stdio.h>

int main()
{
    char a1,a2,a3,a4,a5,a6,a7;
    int n;
    for(int i = 0;i  < 5;i++){
    printf("\nHow many character of the name, it's allowed maximum 7 and minimum 2 character:");
    scanf("%d",&n);
    switch(n)
    {
    case 2 :
        printf("\nEnter the name:");
        scanf("\n%c%c",&a1,&a2);
        printf("Your entered name is %c%c",a1,a2);
    break;
    case 3 :
        printf("\nEnter the name:");
        scanf("\n%c%c%c",&a1,&a2,&a3);
        printf("Your entered name is %c%c%c",a1,a2,a3);
    break;
    case 4 :
        printf("\nEnter the name:");
        scanf("\n%c%c%c%c",&a1,&a2,&a3,&a4);
        printf("Your entered name is %c%c%c%c",a1,a2,a3,a4);
    break;
    case 5  :
        printf("\nEnter the name:");
        scanf("\n%c%c%c%c%c",&a1,&a2,&a3,&a4,&a5);
        printf("Your entered name is %c%c%c%c%c",a1,a2,a3,a4,a5);
    break;
    case 6 :
        printf("\nEnter the name:");
        scanf("\n%c%c%c%c%c%c",&a1,&a2,&a3,&a4,&a5,&a6);
        printf("Your entered name is %c%c%c%c%c%c",a1,a2,a3,a4,a5,a6);
    break;
    case 7 :
        printf("\nEnter the name:");
        scanf("\n%c%c%c%c%c%c%c",&a1,&a2,&a3,&a4,&a5,&a6,&a7);
        printf("Your entered name is %c%c%c%c%c%c%c",a1,a2,a3,a4,a5,a6,a7);
    break;
    }

   }
    return 0;
}
