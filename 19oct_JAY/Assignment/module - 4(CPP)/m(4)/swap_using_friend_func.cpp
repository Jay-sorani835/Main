#include<iostream>
using namespace std;
class Swap{
    int a,b;
    public:
    friend void swap(Swap &obj);
};
void swap(Swap &obj){
    cout<<"Enter the first value:";
    cin>>obj.a;
    cout<<"Enter the second value:";
    cin>>obj.b;
    int c = obj.a;
    obj.a = obj.b;
    obj.b = c;
    cout<<"After swaping first value is "<<obj.a<<" and second value is "<<obj.b<<endl;
}
int main(){
    Swap s1;
    swap(s1);
    return 0;
}