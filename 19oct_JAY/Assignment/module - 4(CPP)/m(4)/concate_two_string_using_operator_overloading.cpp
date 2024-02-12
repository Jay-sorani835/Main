//Write a program to concatenate the two strings using Operator Overloading
#include <iostream>
#include<string>
using namespace std;

class Str{
    string str;
public:
    Str(){}
    Str(string s) : str(s){}

    Str operator+(Str &other){
        Str result;
        result.str = str + " " + other.str;
        return result;
    }

    void display(){
        cout<<str<<endl;
    }
};

int main(){
    string a,b;
    cout<<"Enter the first string:";
    cin>>a;
    cout<<"Enter the second string:";
    cin>>b;
    Str str1(a);
    Str str2(b);

    Str result = str1 + str2;

    cout<<"After concanating the string is : ";
    result.display();
    return 0;
}

