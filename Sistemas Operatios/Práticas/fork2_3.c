// simple C program for passing a value 12*23*34*45 from
// child process to parent process
#include <pthread.h>
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

int main()
{
int x;
int fd[2], i = 0;
pipe(fd);            //new pipe
pid_t pid = fork();  //fork

if(pid > 0) {

                     // I am the parent
    wait(NULL);  // waits for the end of the child process   
    
    // n stores the total bytes read successfully
    int n = read(fd[0], &x, sizeof(x));
    
    printf("received x= %d ", x);
}
else if( pid == 0 ) {
                      // I am the child

    x=12*23*34*45; //some calculation....
    write(fd[1], &x, sizeof(x));
}

else {
    perror("error\n"); //fork()
}
}