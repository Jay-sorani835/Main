#include<stdio.h>

void main() {
  char cname[20];
  printf("Enter The country name:");
  scanf("%s",&cname);
  printf("Abbreviated Name: ");
  printf("%c%c",cname[0],cname[1]);
}
