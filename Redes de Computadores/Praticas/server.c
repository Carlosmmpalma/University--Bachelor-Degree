// Server side C/C++ program to demonstrate Socket programming
#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <time.h>

#define PORT 1300

void strdate(char *buffer, int len) {
    
    time_t now=time(NULL);
    struct tm *ptm=localtime(&now);

    if(ptm==NULL) {

        puts("The localtime() function failed");
        return;
    }
    strftime(buffer, len, "%c",ptm);
}

int main(int argc, char const *argv[])
{
    int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};
       
    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) //Criar o socket
    {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }
       
    // Forcefully attaching socket to the port 1300
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT,
                                                  &opt, sizeof(opt)))
    {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons( PORT );
       
    // Forcefully attaching socket to the port 1300
    if (bind(server_fd, (struct sockaddr *)&address, 
                                 sizeof(address))<0)
    {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

    if (listen(server_fd, 3) < 0) //O socket está ligado à porta em questao/a porta está aberta
    {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    while (1){
    if ((new_socket = accept(server_fd, (struct sockaddr *)&address, //O primeiro connect é aceite e é establecido o socket
                       (socklen_t*)&addrlen))<0)
    {
        perror("accept");
        exit(EXIT_FAILURE);
    }

    printf("Connected to server");
    valread = read( new_socket , buffer, 1024);
    send(new_socket , buffer, strlen(buffer) , 0 );

    close(server_fd);
    }
    return 0;
}