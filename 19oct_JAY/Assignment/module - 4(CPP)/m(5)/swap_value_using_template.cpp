#include<iostream>
using namespace std;
template<typename t>
class temp{
int a,b;
public:

t swap(t a, t b){
    a = a * b;
    b = a / b;
    a = a / b;
    cout<<"After swaping the first value is :"<<a<<endl;
    cout<<"After swaping the second value is :"<<b<<endl;
}
};
int main(){
    temp <int>t1;
    int a,b;
    cout<<"Enter the first value:";
    cin>>a;
    cout<<"Enter the second value :";
    cin>>b;
    t1.swap(a,b);
    return 0;
}