#include<iostream>
using namespace std;
class Students{
    public:
    int no1,no2,no3,no4;
    Students(){
        cout<<"Enter the first student id:";
        cin>>no1;
        cout<<"Enter the second student id:";
        cin>>no2;
        cout<<"Enter the third student id:";
        cin>>no3;
        cout<<"Enter the forth student id:";
        cin>>no4;
    }
};
class Test{
    public:
    int s11,s12,s21,s22,s31,s32,s41,s42;
    Test(){
        cout<<"Enter the first student subject 1 marks:";
        cin>>s11;
        cout<<"Enter the first student subject 2 marks:";
        cin>>s12;
        cout<<"Enter the second student subject 1 marks:";
        cin>>s21;
        cout<<"Enter the second student subject 2 marks:";
        cin>>s22;
        cout<<"Enter the third student subject 1 marks:";
        cin>>s31;
        cout<<"Enter the third student subject 2 marks:";
        cin>>s32;
        cout<<"Enter the fourth student subject 1 marks:";
        cin>>s41;
        cout<<"Enter the fourth student subject 2 marks:";
        cin>>s42;
    }
};
class Result : public Students, public Test{
    public:
    int t1,t2,t3,t4;
    Result(){
        t1 = s11 + s12;
        t2 = s21 + s22;
        t3 = s31 + s32;
        t4 = s41 + s42;
        cout<<"The student roll no is:"<<no1<<" marks is :"<<t1<<endl;
        cout<<"The student roll no is:"<<no2<<" marks is :"<<t2<<endl;
        cout<<"The student roll no is:"<<no3<<" marks is :"<<t3<<endl;
        cout<<"The student roll no is:"<<no4<<" marks is :"<<t4<<endl;
    }
};
int main(){
    Result r1;
    return 0;
}