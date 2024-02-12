#include<iostream>
using namespace std;
class Person{
    int age;
    string name,country;
    public:
    void get(){
        cout<<"Enter the person name, age and country : ";
        cin>>name>>age>>country;
    }
    void display(){
        cout<<"The person name, age and country is :"<<name<<" "<<age<<" "<<country;
    }
};
int main(){
    Person p;
    p.get();
    p.display();
    return 0;
}