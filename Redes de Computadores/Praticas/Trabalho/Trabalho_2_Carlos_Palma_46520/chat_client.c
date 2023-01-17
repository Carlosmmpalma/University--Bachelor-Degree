#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <netdb.h>
#include <netinet/in.h>

#include <string.h>

int main(int argc, char *argv[]) {
   int sockfd, portno, n;
   struct sockaddr_in serv_addr;
   struct hostent *server;
   fd_set master,read_fds;
   
   char buffer[256];

   portno = 1234;
   
   /* Create a socket point */
   sockfd = socket(AF_INET, SOCK_STREAM, 0);
   
   if (sockfd < 0) {
      perror("ERROR opening socket");
      exit(1);
   }
	
   server = gethostbyname("localhost");
   
   if (server == NULL) {
      fprintf(stderr,"ERROR, no such host\n");
      exit(0);
   }
   
   bzero((char *) &serv_addr, sizeof(serv_addr));
   serv_addr.sin_family = AF_INET;
   bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length);
   serv_addr.sin_port = htons(portno);
   
   /* Now connect to the server */
   if (connect(sockfd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0) {
      perror("ERROR connecting");
      exit(1);
   }
   
   /* Now ask for a message from the user, this message
      * will be read by server
   */ 
   FD_ZERO(&master);
   FD_ZERO(&read_fds);
   FD_SET(sockfd,&master);
   FD_SET(0,&master);

   while (1) {
     read_fds=master;
     select(sockfd+1,&read_fds,NULL,NULL,NULL);

     if(FD_ISSET(0,&read_fds)){
     bzero(buffer,256);
     fgets(buffer,255,stdin);

     buffer[strlen(buffer)] = '\0';
     
     /* Send message to the server */
     n = write(sockfd, buffer, strlen(buffer));

     if (n < 0) {
       perror("ERROR writing to socket");
       exit(1);
     }

     if (strcmp(buffer, "QUIT") == 0) {
       printf("Bye!!!\n");
       exit(0);
     }
     }
   
      if(FD_ISSET(sockfd,&read_fds)){
     bzero(buffer,256);
     n = read(sockfd, buffer, 255);
     buffer[strlen(buffer)-1]='\0';
     
     if (n < 0) {
       perror("ERROR reading from socket");
       exit(1);
     }
     
     printf("%s\n",buffer);
      }
   }
     
   return 0;
}
