//get height of person from person in cm and categorize them
int main()
{
    int h;
    printf("Enter your height in centimeter:");
    scanf("%d",&h);
    if(h>170)
        printf("\nYou are very Tall.");
    else if(h>140 && h<=170)
        printf("\nYour height is medium.");
    else
        printf("\nYou are very short.");
    return 0;
}
