#include<iostream>
using namespace std;
class Rectangle{
    float ln,wd;
    public : 
    void get(){
        cout<<"Enter Length of rectangle :";
        cin>>ln;
        cout<<"Enter the widht of rectangle : ";
        cin>>wd;
    }
    void area(){
        cout<<"\nThe Area of rectangle is : "<<ln*wd;  
    }
    void perimeter(){
        cout<<"\nThe perimeter of rectangle is : "<<2*(ln+wd);
    }
};
int main(){
    Rectangle r;
    r.get();
    r.area();
    r.perimeter();
    return 0;
}