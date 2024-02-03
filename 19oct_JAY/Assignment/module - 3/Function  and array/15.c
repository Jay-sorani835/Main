#include<stdio.h>
#include<stdlib.h>

void swap(int *a,int *b);
void bubbleSort(int *a,int n);
void main()
{
    int *a,n,i;
    printf("Enter size of array:");
    scanf("%d",&n);
    a=(int*)calloc(n,sizeof(int));
    for(i=0;i<n;i++)
    {
        printf("Enter value of a[%d]:",i);
        scanf("%d",&a[i]);
    }
    bubbleSort(a,n);
    printf("The Sorting is:\n");
    for(i=0;i<n;i++)
    {
        printf("%d ",a[i]);
    }
}
void swap(int *a,int *b)
{
    *a = *a ^*b;
    *b = *a ^*b;
    *a = *a ^*b;
}
void bubbleSort(int *a,int n)
{
    int i,j;
    for(i=0;i<n-1;i++)
    {
        int flag=0;
        for(j=0;j<n-1-i;j++)
        {
            if(a[j]>a[j+1])
            {
                swap(&a[j],&a[j+1]);
                flag=1;
            }
        }
        if(flag==0)
        {
            break;
        }
    }
}
