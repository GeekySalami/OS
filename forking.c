#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid;
    int status;

    // Fork a child process
    pid = fork();

    if (pid < 0) {
        // Error occurred while forking
        perror("fork");
        return 1;
    } else if (pid == 0) {
        // Child process
        printf("Child process: PID = %d, Parent PID = %d\n", getpid(), getppid());
        // Sleep for some time to simulate work
        sleep(2);
        printf("Child process exiting...\n");
        return 0;
    } else {
        // Parent process
        printf("Parent process: PID = %d, Child PID = %d\n", getpid(), pid);
        // Wait for the child process to terminate
        // Uncomment one of the following lines to use wait or waitpid

        // Using wait
        //wait(&status);

        // Using waitpid
        waitpid(pid, &status, 0);

        printf("Parent process exiting...\n");
        return 0;
    }
}

