#include <stdio.h>
#include <string.h>
void substring(char ch[], char destination[], int start, int len) {
    int i, j;
    for (i = start, j = 0; i < start + len && ch[i] != 0; i++, j++) {
        destination[j] = ch[i];
    }
    destination[j] = 0;
}

int main() {
    char str1[100], str2[100];
    int s, l1;
    printf("Enter a string : ");
    scanf("%s", &str1);

    printf("\nEnter the starting index of the substring : ");
    scanf("%d", &s);

    printf("\nEnter the length of the substring : ");
    scanf("%d", &l1);
    substring(str1, str2, s, l1);

    printf("\nExtract substring : %s", substring);
    return 0;
}