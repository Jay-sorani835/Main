//swapping without using third variable
int main()
{
    int a,b;
    printf("Enter the first number:");
    scanf("%d",&a);
    printf("Enter the second number:");
    scanf("%d",&b);
    printf("\nbefore swapping the a = %d and b = %d",a,b);
    a=a*b;
    b=a/b;
    a=a/b;
    printf("\nAfter swapping the a = %d and b = %d",a,b);
}
