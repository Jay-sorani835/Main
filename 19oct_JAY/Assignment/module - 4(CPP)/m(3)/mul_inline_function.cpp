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
};
int main(){
    demo d1;
    d1.get();
    cout<<"The multiplication of two numbers is : "<<d1.dm()<<endl;
    return 0;
}