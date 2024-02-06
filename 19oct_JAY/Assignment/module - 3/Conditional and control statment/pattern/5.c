#include <stdio.h>

void main()

{
     int i,j,a=0;
  for ( i = 1; i <= 5; i++){
      for ( j = 1; j <= i; j++){
        a++;
        printf(" %d",a);
      }
      printf("\n");
  }
}
