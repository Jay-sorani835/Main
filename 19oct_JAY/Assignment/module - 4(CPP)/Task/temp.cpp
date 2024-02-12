#include<iostream>
using namespace std;
class User{
    string nm;
    float sal;
public:
    void setValue(string unm, double usal) {
        nm = unm;
        sal = usal;
    }
    
    void dispInfo() {
        cout<<"Name : "<<nm<<"\nSalary : "<<sal<<endl;
    }
};

int main() {
    User user;
    User users[5];
    int i;
    string unm1;
    float usal1;
    
    for(i=0; i<5; i++){
     	cout<<"Enter name "<<i+1<<" : ";
        cin>>unm1;
        cout<<"Enter salary "<<i+1<<" : ";
        cin>>usal1;
        users[i].setValue(unm1, usal1);
        cout<<endl;
    }
    for(i=0; i<5; i++){
        users[i].dispInfo();
    }
    return 0;
}