/*Write a program to calculate the area of circle, rectangle and triangle
using Function Overloading
Rectangle: Area * breadth
Triangle: ½ *Area* breadth
Circle: Pi * Area *Area
*/
#include<iostream>
using namespace std;
class Area{
    public:
    int c = 0;
    int ar(int a, int b){
        return a*b;
    }
    float ar(float a,float b,int c){
        float t = 0.5 * a * b;
        return t;
    }
    float ar(int a){
        float r = 3.14 * a * a;
        return r;
    }
};
int main(){
    Area a1;
    float a,b;
    cout<<"Enter the area :";
    cin>>a;
    cout<<"Enter the breadth :";
    cin>>b;
    cout<<"Rectangle :"<<a1.ar(a,b)<<endl;
    cout<<"Triangle :"<<a1.ar(a,b,0)<<endl;
    cout<<"Circle :"<<a1.ar(a)<<endl;
    return 0;
}