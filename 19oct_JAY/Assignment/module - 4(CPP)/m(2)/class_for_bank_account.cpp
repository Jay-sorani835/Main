#include<iostream>
using namespace std;
class Bank{
    int acno,amount,s,n;
    string Dname,Atype;
    public:
    
    void assign(int a,int b,string nm,string ac){
        acno = a;
        s = b;
        Dname = nm;
        Atype = ac;
    }
    void withdraw(){
        cout<<"Enter the withdrawal Amount : ";
        cin>>n;
        s = s - n;
        cout<<"After Deposit the balance in AC.NO : "<<acno<<" is "<<s;
    }
    void Deposit(){
        cout<<"Enter Deposit Amount : ";
        cin>>n;
        s = s + n;
        cout<<"After withdraw the balance in AC.NO : "<<acno<<" is : "<<s;
    }
    void display(){
        cout<<"The Name of Account Holder and his balance :"<<"Name  :"<<Dname<<"   And   Balance : "<<s;
    }
    
};
int main(){
    Bank boi;
    int a,b,c;
    char nm[20],ac[10];
    printf("Enter the following details with between each of them space is required : ");
    printf("1. Account Number 2. Balance 3. Account type 4. Account holder name");
    scanf("%ld %d %s %s",&a,&b,&ac,&nm);
    boi.assign(a,b,nm,ac);
    while(c != 0){
    printf("\nEnter your choice from below 1. Withdraw 2. Deposit 3. Show : ");
    scanf("%d",&c);
    switch(c){
        case 1 : boi.withdraw();
        break;
        case 2 : boi.Deposit();
        break;
        case 3 : boi.display();
        break;
        default : printf("\nInvalid Choice.");
        break;
 
       }
    }
    return 0;
}