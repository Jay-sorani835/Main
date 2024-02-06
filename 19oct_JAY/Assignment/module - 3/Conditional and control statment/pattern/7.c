#include <stdio.h>
int main() {
    int n = 10,k,i,j;
    int a[n][n];
    for(i = 0; i < n; i++){
        for(j = 0; j < n; j++){
            a[i][j] = 0;
        }
    }
    int num = 1;
    for(k = 0; k <= n / 2; k++){
        for(i = k; i < n - k; i++){
            a[k][i] = num++;
        }

        for(i = k + 1; i < n - k; i++){
            a[i][n - k - 1] = num++;
        }

        for(i = n - k - 2; i >= k; i--){
            a[n - k - 1][i] = num++;
        }

        for(i = n - k - 2; i > k; i--){
            a[i][k] = num++;
        }
    }

    for(i = 0; i < n; i++){
        for(j = 0; j < n; j++){
            printf("%3d ", a[i][j]);
        }
        printf("\n");
    }
    return 0;
}