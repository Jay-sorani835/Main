//get marks from user and check it is pass or fail
#include<Stdio.h>

int main()
{
    int m;
    printf("Enter the marks:");
    scanf("%d",&m);
    if(m>=35)
        printf("\nYou are passed this exam.");
    else
        printf("\nSorry you are failed,better luck next time.");
    return 0;
}
