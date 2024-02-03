
//print reverse the number
#include <stdio.h>

int main() {

  int n1,n2,n3,a,b,c, rev = 0, rem,rem1,rem2,rev1 = 0,rev2 = 0;

  printf("Enter an integer: ");
  scanf("%d", &n1);
  printf("Enter an integer: ");
  scanf("%d", &n2);
  printf("Enter an integer: ");
  scanf("%d", &n3);
  a = n1;
  b = n2;
  c = n3;
  while (n1 != 0 && n2 != 0 && n3 != 0) {
    rem = n1 % 10;
    rev = rev * 10 + rem;
    n1 = n1/10;
    rem1 = n2 % 10;
    rev1 = rev1 * 10 + rem1;
    n2 = n2/10;
    rem2 = n3 % 10;
    rev2 = rev2 * 10 + rem2;
    n3 = n3/10;

  }

  printf("Reversed number = %d,%d,%d", rev,rev1,rev2);
  if(rev == a && rev1 == b && rev2 == c)
    printf("\nThis is the palindrome number.");
  else
    printf("\nGiven number is not palindrome number.");

  return 0;
}
