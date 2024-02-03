
#include <stdio.h>

void check(int n1)
{
  int a,rev = 0, rem;
  a = n1;
  while(n1 != 0) {
    rem = n1 % 10;
    rev = rev * 10 + rem;
    n1 = n1/10;
  }
  printf("Reversed number = %d", rev);
  if(rev == a )
    printf("\nThis is the palindrome number.");
  else
    printf("\nGiven number is not palindrome number.");
}
int main() {
  int n1;
  printf("Enter an integer: ");
  scanf("%d", &n1);
  check(n1);
  return 0;
}
