/* Assume a class cricketer is declared. Declare a derived class batsman from
cricketer. Data member of batsman. Total runs, Average runs and best
performance. Member functions input data, calculate average runs, Display
data. (Single Inheritance)*/
#include<iostream>
using namespace std;
class cricketer{
    public : 
    string name;
    void gt(){
        cout<<"Enter the name of the batsman :";
        cin>>name;
    }
};
class batsman : public cricketer{
    public :
    int TR = 0,r[5];
    float avg;
    void get(){
        for(int i = 0; i < 5;i++){
            cout<<"Enter "<<i+1<<" match run :";
            cin>>r[i];  
        }
        for(int i = 0; i < 5;i++){
            TR = TR + r[i];
        }
    }
    void avarage(){
        avg = (float)(TR) / 5;
    }
    void display(){
        cout<<"The Total runs is :"<<TR<<endl;
        cout<<"The avarage runs is :"<<avg<<endl;
    }
    void bestPerform(){
        int max = 0,j = 0;
        for(int i = 0;i < 5;i++){
            if(r[i] > max){
                max = r[i];
                j = i + 1;
            }
        }
        cout<<"The best performance is "<<max<<" in "<<j<<"th Match.";
    }
};
int main(){
    batsman b1;
    b1.get();
    b1.avarage();
    b1.display();
    b1.bestPerform();
    return 0;
}