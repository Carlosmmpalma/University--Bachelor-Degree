#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

void main(void)
{
   pid_t pid, pid2;
  
   pid = fork();
   if (pid > 0) {
     pid2 = fork();
     puts("Hello 1 \n");
   }
  puts("Hello 2 \n");  
}