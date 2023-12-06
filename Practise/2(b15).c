#include<stdio.h>

int main()
{
    int m,p,c;
    printf("Enter the mathematics marks:");
    scanf("%d",&m);
    printf("Enter the chemistry marks:");
    scanf("%d",&c);
    printf("Enter the physics marks:");
    scanf("%d",&p);
    if(m>=65 && p>=55 && c>=50 && (m+p+c)>=190 && (m+p)>=140)
        printf("\nYou are eligible for admission to a professional course.");
    else
        printf("\nYou are not eligible.");
    return 0;
}
