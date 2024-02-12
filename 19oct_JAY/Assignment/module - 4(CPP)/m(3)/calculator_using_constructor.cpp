#include<iostream>
using namespace std;
class Calc{
    int a,b,c;
    public : 
    Calc(){
        cout<<"Enter the first and second element : ";
        cin>>a>>b;
        while(c != 0){
        cout<<"\nEnter the choice 1. Addition 2. Substraction 3. Multiplication 4. Division 5. Modulo :";
        cin>>c; 
        switch(c){
            case 1 : cout<<"The Addition is :"<<a+b;
            break;
            case 2 : cout<<"The Subtraction is :"<<a-b;
            break;
            case 3 : cout<<"The Multiplication is :"<<a*b;
            break;
            case 4 : cout<<"The Division is :"<<a/b;
            break;
            case 5 : cout<<"The Modulo is :"<<a%b;
            break;
            default : cout<<"Invalid choice.";
            break;
        }
        }
    }    
};
int main(){
    Calc c;
    return 0;
}