#include<iostream>
using namespace std;
class A{
public:
A(){
    cout<<"This is grandparent A"<<endl;
}
};
class B : public A{
public:
B(){
    cout<<"This is parent B"<<endl;
}
};
class C : public B{
    public:
    C(){
        cout<<"This is child"<<endl;
    }
};

int main(){
    C c1;
    return 0;
}