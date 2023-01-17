// Client side C/C++ program to demonstrate Socket programming
#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#define PORT 1300
   
int main(int argc, char const *argv[])
{
    int sock = 0, valread;
    struct sockaddr_in serv_addr;
    char *hello = "Hello from client";
    char buffer[1024] = {0}; //Guardar String
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) //IF para verificar se socket foi criado corretamente
    {
        printf("\n Socket creation error \n");
        return -1;
    }
   
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
       
    // Convert IPv4 and IPv6 addresses from text to binary form
    if(inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr)<=0) //Converte string com enedereço IP em formato inteiro adequado(AF_INET)
    {
        printf("\nInvalid address/ Address not supported \n");
        return -1;
    }
   
    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) //Tendo em conta o socket e a estrutura com os dados pretendidos(sitio onde quero que o sokcet se ligue) 
    {                                                                        
        printf("\nConnection Failed \n");
        return -1;
    }//Se nao der erro o socket passa a ser um filedescriptor onde posso escrever e ler 

    write(sock , hello , strlen(hello) , 0 ); //Enviar String para o socket
    printf("Hello message sent\n");
    valread = read( sock , buffer, 1024);//Ler do socket a informaçao enviada previamente(no maximo 24 bytes)
    printf("%s\n",buffer );
    return 0;
}