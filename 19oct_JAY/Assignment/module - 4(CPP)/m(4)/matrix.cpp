//Add two 1D matrix
#include <iostream>
using namespace std;
class Matrix{

public: 
    int size,*data;
    Matrix(int s) : size(s){
        data = new int[size];
    }

    Matrix operator +(Matrix obj){
        if(this->size != obj.size){
            cout<<"Matrices have different sizes. Addition not possible."<<endl;
            return Matrix(0);
        }
        Matrix result(size);

        for (int i = 0; i < size; i++){
            result.data[i] = this->data[i] + obj.data[i];
        }
        return result;
    }
    void input(){
        cout<<"Enter "<<size<<" elements : ";
        for(int i = 0; i < size; ++i){
            cin>>data[i];
        }
    }
    void display(){
        cout<<"Matrix : ";
        for(int i = 0; i < size; ++i){
            cout<<data[i]<<" ";
        }
        cout<<endl;
    }
};

int main() {
    int size;
    cout<<"Enter size of matrices : ";
    cin>>size;
    Matrix m1(size), m2(size);
    cout<<"Enter elements for first matrix"<<endl;
    m1.input();
    cout<<"Enter elements for second matrix"<<endl;
    m2.input();
    Matrix result = m1 + m2;
    result.display();
    return 0;
}
