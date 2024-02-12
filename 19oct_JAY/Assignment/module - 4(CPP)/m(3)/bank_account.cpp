/*Write a C++ program to implement a class called Bank Account that has 
private member variables for account number and balance. Include 
member functions to deposit and withdraw money from the account.*/
#include<iostream>
using namespace std;
class BankAccount{
    int acno;
    int  balance;
    public:
    void get(){
        cout<<"Enter the Account number:";
        cin>>acno;
        cout<<"Enter the balance :";
        cin>>balance;
    }
    int deposit(int d){
        balance = balance + d;
        cout<<"Available balance is :"<<balance<<endl;
        return 0;
    }
    int withdraw(int w){
        if(balance != 0){
        balance = balance - w;
        cout<<"Available balance is :"<<balance<<endl;
        }
        return 0;
    }
};
int main(){
    BankAccount b1;
    b1.get();
    int c,a;
    cout<<"Enter what you want 1. Deposit 2. Withdraw:";
    cin>>c;
    switch(c){
        case 1 : cout<<"Enter the deposit amount :";
        cin>>a;
        b1.deposit(a);
        break;
        case 2 : cout<<"Enter the withdrawal amount : ";
        cin>>a;
        b1.withdraw(a);
        break;
        default : cout<<"Invalid Choice.";
        break;
    }
    return 0;
}