#include<iostream>
using namespace std;
class Get{
    public:
    int a,b;
    Get(){
        cout<<"Enter the first value:";
        cin>>a;
        cout<<"Enter the second value:";
        cin>>b;
    }
};
class Add : public Get{
    public:
    int Add(){
        cout<<"\nThe Addition is : "<<a+b;
     }
};
class Sub: public Get{
    public:
    int Sub(){
        cout<<"\nThe subtraction is : "<<a-b<<endl;
    }
};
class Div : public Get{
    public:
    int Div(){
        cout<<"\nThe Division is : "<<a/b<<endl;
    }
};
class Mul : public Get{
    public :
    int Mul(){
        cout<<"\nThe Multiplication is : "<<a*b<<endl;
    }
};
int main(){
    int n;
    cout<<"Enter your choice what you perform 1. Addition 2. Subtraction 3. Multiplication 4. Division :"<<endl;
    cin>>n;
    Add a1;
    Sub s1;
    Mul m1;
    div d1;
    switch(n){
        case 1 : a1.Add();
        break;
        case 2 : s1.Sub();
        break;
        case 3 : m1.Mul();
        break;
        case 4 : d1.Div();
        break;
        default :  cout<<"Invlaid Choice.";
        break;
    }
    return 0;
}