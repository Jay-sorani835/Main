#include<iostream>
using namespace std;
class Calc{
    private:
    int a,b;
    public : 
    int sum(int a,int b){
        cout<<"The Addition is "<<a+b;
        return 0;
    }
    int sub(int a, int b){
        cout<<"The Subtraction is "<<a-b;
        return 0;
    }
    int mul(int a, int b){
        cout<<"The Multiplication is "<<a*b;
        return 0;
    }
    void div(int a, int b){
        cout<<"The Division is "<<a/b;
    }
    int mod(int a, int b){
        cout<<"The Modulo is "<<a%b;
        return 0;
    }
};
int main(){
    int n,a,b;
    printf("Enter the First Number : ");
    scanf("%d",&a);
    printf("Enter the second Number : ");
    scanf("%d",&b);
    Calc c;
    while(n != 0){
    printf("\nEnter the your choice 1. Addition 2. Subtraction 3. Multiplication 4. Division 5. Modulo : ");
    scanf("%d",&n);
    switch(n){
        case 1 : c.sum(a,b);
        break;
        case 2 : c.sub(a,b);
        break;
        case 3 : c.mul(a,b);
        break;
        case 4 : c.div(a,b);
        break;
        case 5 : c.mod(a,b);
        break;
        default : printf("Invalid choice.");
        break;
    }
    }
    return 0;
}