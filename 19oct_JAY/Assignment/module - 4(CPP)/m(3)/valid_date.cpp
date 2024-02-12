/*Write a C++ program to implement a class called Date that has private 
member variables for day, month, and year. Include member functions to 
set and get these variables, as well as to validate if the date is valid.*/
#include<iostream>
#include<string.h>
using namespace std;
class Date{
    int d,m,y;
    public:
    Date(int d, int m, int y){
        if(y >= 1000 && y <= 3000){
            if((m == 1 || m == 3 || m == 5 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && d > 0 && d <= 31)
            cout<<"The Date is Valid.";
            else if((m == 4 || m == 6 || m == 9 || m == 11 ) && d > 0 && d <= 30)
            cout<<"The Date is Valid.";
            else if(m == 2){
            if((y % 400 == 2 ||  (y%100 != 0 && y%4 == 0)) && d>0 && d<=29)
            cout<<"The Date is Valid.";
                else if(d > 0 && d <= 28)
                cout<<"The Date is Valid.";
                else
                cout<<"The Date is Invlaid.";
            }
            else
            cout<<"The Date is Invalid.";
        }
        else
        cout<<"The Date is Invalid.";
        
    }
};
int main(){
    int date,month,year;
    cout<<"Enter the date in this format DD MM YYYY:";
    cin>>date>>month>>year;
    Date d1(date,month,year);
    return 0;
}