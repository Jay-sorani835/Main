#include<iostream>
using namespace std;
class Max{
    int a,b;
    public:
    friend void max(Max &obj);
};
void max(Max &obj){
    cout<<"Enter the first value :";
    cin>>obj.a;
    cout<<"Enter the second value :";
    cin>>obj.b;
    if(obj.a > obj.b){
        cout<<"The first number is max:"<<obj.a;
    }
    else{
        cout<<"The second number is max:"<<obj.b;
    }
}
int main(){
    Max m1;
    max(m1);
    return 0;
}