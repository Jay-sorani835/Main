//print reverse the number
#include <stdio.h>

int main() {

  int n, rev = 0, rem;

  printf("Enter an integer: ");
  scanf("%d", &n);

  for(int i = 0; i = (n!=0) ;i++){
    rem = n % 10;
    rev = rev * 10 + rem;
    n = n/10;
  }

  printf("Reversed number = %d", rev);

  return 0;
}

