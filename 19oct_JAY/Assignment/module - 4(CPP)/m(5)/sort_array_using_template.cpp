//Sort the array using templates
#include<iostream>
using namespace std;
template<typename T>
void sort(T arr[],int size){
    for(int i = 0; i < size;i++){
        for(int j = i + 1;j < size; j++){
            if(arr[i] > arr[j]){
                arr[i] = arr[i] * arr[j];
                arr[j] = arr[i] / arr[j];
                arr[i] = arr[i] / arr[j];
           }
        }
    }
}
int main(){
    int n;
    cout<<"Enter the size of array:";
    cin>>n;
    int a[n];
    for(int i = 0; i < n; i++){
        cout<<"Enter the array element:";
        cin>>a[i];
    }
    cout<<"Before sorting the array is:"<<endl;
    for(int i = 0; i < n; i++){
        cout<<a[i]<<" ";
    }
    sort(a,n);
    cout<<endl<<"After sorting the array is :"<<endl;
    for(int i = 0; i < n;i++){
        cout<<a[i]<<" ";
    }

    return 0;
}