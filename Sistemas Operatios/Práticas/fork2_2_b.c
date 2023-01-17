
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

void main(void)
{
    pid_t pid, pid2, pid3; 


 pid = fork(); 

 pid2 = fork(); 
 if(pid2) printf("Pai - Hello 2 \n"); else printf("Filho - Hello 2 \n");

 pid3 = fork(); 

 if(pid3) printf("Pai - Hello 3 \n"); else printf("Filho - Hello 3 \n");

}