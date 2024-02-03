#include<stdio.h>
#include<stdlib.h>

int main()
{
    int *a,n,i,*b;
    printf("Enter value of size:");
    scanf("%d",&n);
    a=(int*)calloc(n,sizeof(int));
    for(i=0;i<n;i++){
        printf("Enter a[%d]:",i);
        scanf("%d",&a[i]);
    }
    b = (int *)calloc(n,sizeof(int));
    for(int j = 0;j < n;j++){
        printf("Enter b[%d]",j);
        scanf("%d",&b[j]);
    }
    int choice;
    printf("Enter your choice: 1.Ascending or 2.Descending: ");
    scanf("%d",&choice);
    if(choice == 1){
        Asc(a,n);
        Asc(b,n);
    }
    else{
        dsc(a,n);
        dsc(b,n);
    }
    printf("The first array :");
    for(i=0;i<n;i++){
        printf("%d ",a[i]);
    }
    printf("\n");
    printf("The second array : ");
    for(int j = 0;j < n;j++){
        printf("%d ",b[j]);
    }
    return 0;
}
void swap(int *a,int *b){
    *a = *a ^ *b;
    *b = *a ^ *b;
    *a = *a ^ *b;
}
void Asc(int *a,int n){
    int i,j;
    for(i=0;i<n-1;i++){
        int min=i;
        for(j=i+1;j<n;j++){
            if(a[min]>a[j])
                min=j;
        }
        if(min != i)
            swap(&a[min],&a[i]);
    }
}
void dsc(int *a,int n){
    int i,j;
    for(i=0;i<n-1;i++){
        int min=i;
        for(j=i+1;j<n;j++){
            if(a[min]<a[j])
                min=j;
        }
        if(min != i)
            swap(&a[min],&a[i]);
    }
}