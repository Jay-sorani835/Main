/*Write a C++ program to implement a class called Student that has private 
member variables for name, class, roll number, and marks. Include 
member functions to calculate the grade based on the marks and display 
the student's information. Accept address from each student implement 
using of aggregation*/
#include<iostream>
using namespace std;
class Student{
string name,cs,grade;
int rollno,mark;
public :
void set(int rollno,string name, string cs, int mark){
    this->rollno = rollno;
    this->name = name;
    this->cs = cs;
    this->mark = mark;
}
void calGrade(){
    if(mark >= 95){
        grade = "A+";
    }
    else if(mark >= 90 && mark < 95){
        grade = "A";
    }
    else if(mark >= 85 && mark < 90){
        grade = "B+";
    }
    else if(mark >=80 && mark < 85){
        grade = "B";
    }
    else if(mark >= 70 && mark < 80){
        grade = "C";
    }
    else if(mark >= 55 && mark < 70){
        grade = "D";
    }
    else if(mark >= 35 && mark < 55){
        grade = "F";
    }
    else{
        cout<<"You are Fail.";
    }
}
void display(){
    cout<<" "<<name<<" has roll no is "<<rollno<<" and he scored "<<mark<<" with grade "<<grade<<" in class "<<cs<<endl;
}
};
int main(){
    
    int rollno,mark;
    string name,cs;
    cout<<"Enter the student details : "<<endl;
    cout<<"Enter the name of the student : ";
    cin>>name;
    cout<<"Enter the roll no of the student : ";
    cin>>rollno;
    cout<<"Enter the class of the student : ";
    cin>>cs;
    cout<<"Enter the marks of the student :";
    cin>>mark;
    Student s1;
    s1.set(rollno,name,cs,mark);
    s1.calGrade();
    s1.display();
    return 0;
}