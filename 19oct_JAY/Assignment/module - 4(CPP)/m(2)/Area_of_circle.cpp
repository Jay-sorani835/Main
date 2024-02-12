#include<iostream>
using namespace std;
class Circle{
    float r;
    public: 
    void get(){
        cout<<"Enter the radius of circle : ";
        cin>>r;
    }
    void area(){
        cout<<"\nThe area of circle is : "<<3.14*r*r;
    }
    void circumference(){
        cout<<"\nThe circumference of circle is : "<<2*3.14*r;
    }
};
int main(){
    Circle c;
    c.get();
    c.area();
    c.circumference();
    return 0;
}