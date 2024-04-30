#include <stdio.h>
#include <dirent.h>

int main(int argc, char *argv[]) {
    DIR *directory;
    struct dirent *entry;

    if (argc < 2) {
        // If no directory is provided, list the contents of the current directory
        directory = opendir(".");
    } else {
        // Open the specified directory
        directory = opendir(argv[1]);
    }

    if (directory == NULL) {
        perror("opendir");
        return 1;
    }

    // Iterate through each entry in the directory
    while ((entry = readdir(directory)) != NULL) {
        // Print the name of each entry
        printf("%s\n", entry->d_name);
    }

    // Close the directory
    closedir(directory);
    
    return 0;
}
