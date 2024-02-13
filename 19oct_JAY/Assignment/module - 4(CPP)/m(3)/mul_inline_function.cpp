#include<iostream>
using namespace std;
class demo{
public:
int a,b;
void get(){
    cout<<"Enter the first value :";
    cin>>a;
    cout<<"Enter the second value:";
    cin>>b;
}
inline int dm(){return a*b;}
inline int cu(){return (a*a*a);}
inline int cu1(){return (b*b*b);}
};
int main(){
    demo d1;
    d1.get();
    cout<<"The multiplication of two numbers is : "<<d1.dm()<<endl;
    cout<<"The cubic value of first number is : "<<d1.cu()<<endl;
    cout<<"The cubic value of second number is : "<<d1.cu1()<<endl;
    return 0;
}
