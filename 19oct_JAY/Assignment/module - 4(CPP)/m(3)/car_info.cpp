/*Write a C++ program to create a class called Car that has private 
member variables for company, model, and year. Implement member 
functions to get and set these variables.*/
#include<iostream>
using namespace std;
class car{
    int year;
    string  model, company;
    public : 
    void get(){
        cout<<"Enter the car company name :";
        cin>>company;
        cout<<"Enter the car model name :";
        cin>>model;
        cout<<"Enter the car parsing year :";
        cin>>year;
    }
    void set(){
        cout<<company<<" car model number is "<<model<<" and car parsing year is "<<year<<endl;
    }
};
int main(){
    car c1;
    c1.get();
    c1.set();
    return 0;
}