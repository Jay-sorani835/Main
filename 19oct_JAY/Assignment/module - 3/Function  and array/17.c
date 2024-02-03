//WAP to show the difference between structure and Union
#include<stdio.h>
//Structure and Union is the derived datatype
/*In Structure the size is the sum of all sizeof member.
In structre we access the member of sturcture simultanously*/
//syntax
struct demo
{
    int x;
    float y;
};
/*The Size of Union is the largest size of its member.
In Union we can access the member of Union at a time.*/
//syntax:
union udemo
{
    int x;
    float y;
};
int main(){
    struct demo d1;
    d1.x = 93;
    d1.y = 9.32;
    printf("The x and y in structure is %d and %.2f",d1.x,d1.y);
    union udemo u;
    u.x = 93;
    u.y = 8.56;
    printf("\n The integer is %d and float is %.2f",u.x,u.y);
    return 0;
}
