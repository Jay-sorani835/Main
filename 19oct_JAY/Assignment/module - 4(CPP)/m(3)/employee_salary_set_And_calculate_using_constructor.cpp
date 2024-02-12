/*Write a C++ program to implement a class called Employee that has 
private member variables for name, employee ID, and salary. Include 
member functions to calculate and set salary based on employee 
performance. Using of constructor*/
#include<iostream>
using namespace std;
class Employee{
    string name;
    int eID,sal;
    public:
    Employee(){
        cout<<"Enter the name of the employee:";
        cin>>name;
        cout<<"Enter the employee id :";
        cin>>eID;
        cout<<"Enter the employee salary:";
        cin>>sal;
        int pm;
        cout<<"Enter the employee's performance:";
        cin>>pm;
        if(pm > 75 && pm <= 100){
            sal = sal + sal % 20;
        }
        else if(pm > 50 && pm <= 75){
            sal = sal + sal % 15;
        }
        else if(pm > 25 && pm <= 50){
            sal = sal + sal % 10;
        }
        else{
            sal = sal + sal * 5;
        }
    }
};
int main(){
    Employee e;
    return 0;
}