#include <stdio.h>

void main()
{
    int n,i,f1=1,f2=1,f3=1,f4=1,f5=1,n1,n2,n3,n4;
        printf("Enter the number:");
        scanf("%d",&n);
        printf("Enter the number:");
        scanf("%d",&n1);
        printf("Enter the number:");
        scanf("%d",&n2);
        printf("Enter the number:");
        scanf("%d",&n3);
        printf("Enter the number:");
        scanf("%d",&n4);
    for(i=1;i<=n;i++){
        f1 = f1*i;
    }
    for(i=1;i<=n1;i++){
        f2 = f2*i;
    }
    for(i=1;i<=n2;i++){
        f3 = f3*i;
    }
    for(i=1;i<=n3;i++){
        f4 = f4*i;
    }
    for(i=1;i<=n4;i++){
        f5 = f5*i;
    }
    printf("\nFactorial of %d is %d",n,f1);
    printf("\nFactorial of %d is %d",n1,f2);
    printf("\nFactorial of %d is %d",n2,f3);
    printf("\nFactorial of %d is %d",n3,f4);
    printf("\nFactorial of %d is %d",n4,f5);
}
