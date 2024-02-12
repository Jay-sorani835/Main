#include<iostream>
using namespace std;
class Calc{
    int c = 0;
    public:
    int add(int a, int b){
        return a+b;
    }
    int add(int a, int b, int c){return (a+b);}
    inline int sub(int a, int b){return (a-b);}
    inline int sub(int a, int b, int c){return (a-b);}
    inline int mul(int a, int b){return (a*b);}
    inline int mul(int a, int b, int c){return (a*b);}
    inline int div(int a, int b){return (a/b);}
    inline int div(int a, int b, int c){return (a/b);}
};
int main(){
    int a,b,c = 0;
    cout<<"Enter the first value :";
    cin>>a;
    cout<<"Enter the second value :";
    cin>>b;
    Calc c1;
    cout<<"The Addition is :"<<c1.add(a,b)<<endl;
    cout<<"The Addition is :"<<c1.add(a,b,c)<<endl;
    cout<<"The Addition is :"<<c1.sub(a,b)<<endl;
    cout<<"The Addition is :"<<c1.sub(a,b,c)<<endl;
    cout<<"The Addition is :"<<c1.mul(a,b)<<endl;
    cout<<"The Addition is :"<<c1.mul(a,b,c)<<endl;
    cout<<"The Addition is :"<<c1.div(a,b)<<endl;
    cout<<"The Addition is :"<<c1.div(a,b,c)<<endl;
    return 0;
}