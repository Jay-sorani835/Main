#include<iostream>
using namespace std;
class Task{
    int id;
    string mo,name,address;
    public:
    /*Task(int id1,string name1,int m_no1,string address1){
        id = id1;
        name = name1;
        m_no = m_no1;
        address = address1;
    }*/
    void get(int id1,string mno,string nm,string address1){
        id = id1;
        mo = mno;
        name = nm;
        address = address1;
    }
    void print(){
       cout<<"Person Id : "<<id<<"\nName : "<<name;
       cout<<"\nPerson mobile no : "<<mo<<"\nAddress : "<<address<<endl;
    }

};

int main(){
    Task user;
    Task us[5];
    int id,i;
    string mno,nm,address;
    for(i = 0; i < 5; i++){
        cout<<"Enter the Person ID : ";
        cin>>id;
        cout<<"Enter the mobile number : ";
        cin>>mno;
        cout<<"Enter the name : ";
        cin>>nm;
        cout<<"Enter the Address :";
        cin>>address;
        us[i].get(id,mno,nm,address);
        cout<<endl;
    }
    for(i = 0; i < 5;i++)
        us[i].print();
    
    return 0;
}