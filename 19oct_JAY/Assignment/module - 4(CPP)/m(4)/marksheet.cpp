#include<iostream>
using namespace std;
class Math{
    public:
    int m;
    Math(){
        cout<<"Enter the maths mark:";
        cin>>m;
    }
};
class Phy{
    public:
    int p;
    Phy(){
        cout<<"Enter the physics mark:";
        cin>>p;
    }
};
class Chem{
public:
int c;
Chem(){
    cout<<"Enter the chemistry mark:";
    cin>>c;
}
};
class Mark : public Math, public Phy, public Chem{
    public:
    Mark(){
        cout<<"Maths : "<<m<<endl;
        cout<<"Science : "<<p<<endl;
        cout<<"Chemistry : "<<c<<endl;
    }
};
int main(){
    Mark m1;
    return 0;
}