#include<stdio.h>

int main()
{
     int i,j,k;

  for ( i = 0; i <= 5; i++)
  {
      for(k = 0; k <= 2*(i-1);k++)
        printf(" *");
      printf("\n");
  }
    return 0;
}
