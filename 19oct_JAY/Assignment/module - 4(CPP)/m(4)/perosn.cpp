/*Create a class person having members name and age. Derive a class student
having member percentage. Derive another class teacher having member
salary. Write necessary member function to initialize, read and write data.
Write also Main function*/
#include<iostream>
using namespace std;
class Person{
    public:
    string name = "none";
    int age = 0;
};
class student : public Person{
    public:
    float pt = 0;
    void ini(){
        cout<<"Enter the name of the student:";
        cin>>name;
        cout<<"Enter the percentage of the student :";
        cin>>pt;
        cout<<"Enter the age of the student :";
        cin>>age;
    }
    void read(){
        cout<<"The name of student is :"<<name<<endl;
        cout<<"The age is "<<age<<" and percentage is "<<pt<<endl;
    }
    void write(){
    cout<<"Enter the name of the student:";
    cin>>name;
    cout<<"Enter the percentage of the student :";
    cin>>pt;
    cout<<"Enter the age of the student :";
    cin>>age;
    }
};
class teacher : public Person{
    public:
    int salary = 0;
    void ini(){
        cout<<"Enter the name of the teacher:";
        cin>>name;
        cout<<"Enter the percentage of the teacher :";
        cin>>salary;
        cout<<"Enter the age of the teacher :";
        cin>>age;
    }
    void read(){
        cout<<"The name of teacher is :"<<name<<endl;
        cout<<"The age is "<<age<<" and salary is "<<salary<<"Rs."<<endl;
    }
    void write(){
    cout<<"Enter the name of the teacher:";
    cin>>name;
    cout<<"Enter the percentage of the teacher :";
    cin>>salary;
    cout<<"Enter the age of the teacher :";
    cin>>age;
}
};
int main(){
    student s1;
    teacher t1;
    int c,d;
    cout<<"Enter what is your role:1. Student 2. Teacher:-";
    cin>>c;
    if(c == 1){
        cout<<"What you perform 1. Initialize 2. Read 3. Write:";
        cin>>d;
        switch(d){
            case 1 : s1.ini();
            break;
            case 2 : s1.read();
            break;
            case 3 : s1.write();
            break;
            default : cout<<"Invalid choice.";
        }
    }
    else if(c == 2){
        cout<<"What you perform 1. Initialize 2. Read 3. Write:";
        cin>>d;
        switch(d){
            case 1 : t1.ini();
            break;
            case 2 : t1.read();
            break;
            case 3 : t1.write();
            break;
            default : cout<<"Invalid choice.";
        }
    }
    else{
        cout<<"Invalid Choice.";
    }
    return 0;
}
