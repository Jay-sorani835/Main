#include<stdio.h>

int main()
{
    int n = 2;
    int a[n][n],b[n][n],c[n][n],i,j,m;
    printf("Enter what you perform 1.Addition 2.Subtraction 3.Multiplication:");
    scanf("%d",&m);
    for(i = 0;i  < n;i++){
        for(j = 0;j < n;j++){
            printf("Enter value of a[%d][%d]:",i,j);
            scanf("%d",&a[i][j]);
        }
    }
    for(i = 0;i  < n;i++){
        for(j = 0;j < n;j++){
            printf("Enter value of b[%d][%d]:",i,j);
            scanf("%d",&b[i][j]);
        }
    }
    switch(m)
    {
        case 1 : for(i = 0;i  < n;i++){
        for(j = 0;j < n;j++){
                c[i][j] = a[i][j] + b[i][j];
        }

    for(i = 0;i  < n;i++){
        for(j = 0;j < n;j++){
                printf("\n%d",c[i][j]);
        }
    }
    }
    break;
    case 2 : for(i = 0;i  < n;i++){
        for(j = 0;j < n;j++){
                c[i][j] = a[i][j] - b[i][j];
        }
    }
    for(i = 0;i  < n;i++){
        for(j = 0;j < n;j++){
                printf("\n%d",c[i][j]);
        }
    }
    break;
    case 3 : for(i = 0;i  < n;i++){
        for(j = 0;j < n;j++){
                c[i][j] = a[i][j] * b[i][j];
        }
    }
    for(i = 0;i  < n;i++){
        for(j = 0;j < n;j++){
                printf("\n%d",c[i][j]);
        }
    }
    break;
    }
    return 0;
}
