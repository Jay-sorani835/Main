//calculate electricity bill
#include<stdio.h>

int  main()
{
    int cid,unit;
    char c1,c2,c3,c4,c5;
    printf("Enter the name , id and unit:");
    scanf("%c%c%c%c%c , %d , %d",&c1,&c2,&c3,&c4,&c5,&cid,&unit);
    if(unit<350)
        printf("Your bill amount is %.2f",((float)unit)*1.20);
    else if(unit >= 350 && unit < 600)
        printf("Your bill amount is %.2f",((float)unit)*1.50);
    else if(unit >=600 && unit < 800)
        printf("Your bill amount is %.2f",((float)unit)*1.80);
    else
        printf("Your bill amount is %.2f",((float)unit)*2.00);
    return 0;
}
