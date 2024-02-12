/*equilateral triangle 3 side and 3 angle is same.
isosceles triangle 2 side and opposite side of those sides angles are same.
scalene triangle different sides and different angles.
*/
#include<iostream>
using namespace std;
class Tri{
int a,b,c;
public:
void set(int a1,int a2,int a3){
    a = a1;
    b = a2;
    c = a3;
}
void check(int a,int b,int c){
    if(a == b && b == c && a == c){
        cout<<"The triangle is Equilateral."<<endl;
    }
    else if(a == b || b == c || a == c){
        cout<<"The Triangle is Isosceles."<<endl;
    }
    else{
        cout<<"The Triangle is Scalene."<<endl;
    }
}
};
int main(){
    int a1,a2,a3;
    Tri t1;
    cout<<"Enter the first side:";
    cin>>a1;
    cout<<"Enter the second side:";
    cin>>a2;
    cout<<"Enter the third side:";
    cin>>a3;
    t1.set(a1,a2,a3);
    t1.check(a1,a2,a3);
    return 0;
}