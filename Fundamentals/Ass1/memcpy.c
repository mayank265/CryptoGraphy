#include <stdio.h>
#include <string.h>

int main()
{
    int choice;
    char source[] = "DS is a bad girl";   
    char destination[] = "isn't it Funny";   

    printf("Size of string source \"%s\" = %ld \n", source, sizeof(source));
    printf("Size of string destination \"%s\" = %ld \n", destination, sizeof(destination));

    printf ("\n#########################################################################");
    printf("\n Choose one to play with with interchanging Source and Destination");
    printf("\n1. Now, Lets Play with memcpy in good case ");
    printf("\n2. Now, Lets Play with memcpy with Keeping size -1");
    printf("\n3. Now, Lets Play with memcpy with Keeping size of destination less than source\n");
    scanf("%d", &choice);

    if (choice == 1) {
        memcpy(destination, source, sizeof(source));
        printf("Size of string destination \"%s\" = %ld \n", destination, sizeof(destination));
    }

    if (choice == 2) {
        memcpy(destination, source, -1);
        printf("Size of string destination \"%s\" = %ld \n", destination, sizeof(destination));
    }

    if (choice == 3) {
        memcpy(destination, source, sizeof(destination));
        printf("Size of string destination \"%s\" = %ld \n", destination, sizeof(destination));    
    }
    
    printf ("\n#########################################################################");
    printf ("\nIf you ran all the cases above, we can conclude that");
    printf ("\nmcmcpy doesn't checks for overflow or \\0 and,");
    printf ("\nmcmcpy leads to problem when source and destination addresses overlap \n");
    return 0;
}