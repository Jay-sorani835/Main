#include <stdio.h>

void main()
{
     int i,j,k;

  for ( i = 0; i <= 5; i++)
  {
      for ( j = 10; j >=2 * i + 1; j--)
      {
        printf(" ");
      }
      for ( k = 0; k <= 2 * (i-1); k++)
        printf(" *");
      printf("\n");
  }
}
