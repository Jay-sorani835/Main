#include <iostream>
using namespace std;
class A
{
    private:
    int no = 10; 
public://public access that used publicly no restrication are there.
    A()
    {
        cout << "This is number of constructor A."<<no;//private access because the no is only used in this class.
    }
};
class B: virtual public A//protected access
{
public:
    B(){
        cout<<"\nThis is Constructor B.";
    }
};
int main(){
    B b1;
    return 0;
}