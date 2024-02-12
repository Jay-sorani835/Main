#include<iostream>
using namespace std;
class Parent1{
    public: 
    Parent1(){
    cout<<"\nThis is First Parent";
}
};
class Parent2{
    public:
    Parent2(){
    cout<<"\nThis is second parent";
    }
};
class Parent3{
    public:
    Parent3(){
    cout<<"\nthis is third parent";
}
};
class child : public Parent1 , public Parent2 , public Parent3{   
    public:
    child(){
    cout<<"This is Child";
}
};
int main(){
    child c1;
    return 0;
}