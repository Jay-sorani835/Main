#include<iostream>
using namespace std;
class Area{
    public:
    int x,y;
    void get(int x, int y){
        this->x = x;
        this->y = y;
    }
};
class cal : public Area{
    public:
    int c; 
       int ar(){
        c = x * y;
        return c;
       } 
};
int main(){
    cal c1;
    int a,b;
    cout<<"Enter the first value:";
    cin>>a;
    cout<<"Enter the second value:";
    cin>>b;
    c1.get(a,b);
    cout<<"The multiplication is :"<<c1.ar()<<endl;
    return 0;
}