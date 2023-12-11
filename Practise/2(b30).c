//calculate charge
#include<stdio.h>

int main()
{
    int r;
    printf("Enter the bill amount:");
    scanf("%d",&r);
    if(r>800)
        printf("Your charge is %.2f",(.18*r));
    else if(r<256)
        printf("Your bill amount is invalid,minimum bill amount should be rupees 256.");
    else
        printf("No charges.");
    return 0;
}
