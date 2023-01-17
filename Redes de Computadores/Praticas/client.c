#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <netdb.h>
#include <netinet/in.h>
#include <string.h>

int main(int argc, char *argv[]) {
   int sockfd, portno,new_socket, n;
   struct sockaddr_in serv_addr;
   struct hostent *server;

   char buffer[256];
   portno = 1300;
   char *hello = "Hello from server";

   /* Create a socket point */
   sockfd = socket(AF_INET/*IPV4*/, SOCK_STREAM, 0); //produz file descriptor onde podemos ler e escrever
   if (sockfd < 0) {
      perror("ERROR opening socket");
      exit(1);
   }

   server = gethostbyname("localhost"); //Devolve struct com dados do servidor
   if (server == NULL) {
     fprintf(stderr,"ERROR, no such host\n");
      exit(1);
   }

   bzero((char *) &serv_addr, sizeof(serv_addr)); //Colocar tudo a zeros
   serv_addr.sin_family = AF_INET; //Tipo de estrutura à qual vou ligar
   bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length); //Copiar h_addr para dentro da estrutura serv_addr
   serv_addr.sin_port = htons(portno); //Colocar a porta no sitio certo_a partir deste momento a estrutura tem os dados do servidor

   /* Now connect to the server */
   if (connect(sockfd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0) {
      perror("ERROR connecting");
      exit(2);
   }

   printf("Connected\n");
   write(sockfd, "Cocaina",9);

   /* Now read server response */
   bzero(buffer,256); //Colocar /0 no buffer
   n = read(sockfd, buffer, 255); //Colocamos a string do socket no buffer

   if (n < 0) {
     perror("ERROR reading from socket");
     exit(3);
   }
   printf("> %s\n", buffer);
   
   close(sockfd);

   return 0;
}
