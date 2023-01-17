#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h> 
#include <time.h>        

#define PORT 1234
#define BUFSIZE 256
#define MAXCLIENT 10

int server_fd, new_socket; 
int fdmax;
char usernames[MAXCLIENT][15];
fd_set master, read_fds;

int process_client(int sock)
{
    int n;
    char buf[BUFSIZE];

    n = read(sock, buf, BUFSIZE);

    if (n <= 0) {
        return 0; /* client closed socket */
    }

    buf[n] = '\0';

    printf("* client %s wrote: '%s'\n", usernames[sock], buf);
    printf("* sending back...\n");
    //Enviar para todos
    if(buf[0] == '+'){
    for(int i = 0; i <= fdmax; i++){
        if(FD_ISSET(i,&master)&& i!=server_fd && i!=sock){
            write(i,buf, strlen(buf)+1);
        }
    }
    }else{
        //Enviar para a penas um
        for(int i = 0; i <= fdmax; i++){
        if(FD_ISSET(i,&master) && i!=server_fd && buf[0] == '-' && strncmp(&buf[1],usernames[i],strlen(usernames[i]-1))==0){ //Verifica se o nome a seguir ao '-' está associado a algum socket
        char buffer[120];
        char s[100];
        char s1[20];
        strncpy(s,&buf[strlen(usernames[i])+1],strlen(buf)); //substring da parte a seguir a "-NOME"
        sprintf(s1,"-%s",usernames[sock]);;
        strcat(strcpy(buffer, s1), s);
        write(i,buffer, strlen(buffer)+1);
        }
    } 
    
    printf("Ok.\n");

    return 1;
    }
}

int main(int argc, char const *argv[]) 
{ 
    struct sockaddr_in address;
     char buf[BUFSIZE];
    
    int opt = 1;      // for setsockopt() SO_REUSEADDR, below
    int addrlen = sizeof(address);
    int n, i;
    
    char buffer[BUFSIZE];
    
    // Creating socket file descriptor 
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) 
    { 
        perror("socket failed"); 
        exit(EXIT_FAILURE); 
    } 
       
    // Forcefully attaching socket to the port 1300 
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, 
                                                  &opt, sizeof(opt))) 
    { 
        perror("setsockopt failed"); 
        exit(EXIT_FAILURE); 
    } 
    address.sin_family = AF_INET; 
    address.sin_addr.s_addr = INADDR_ANY; 
    address.sin_port = htons( PORT ); 
       
    // Bind the socket to the network address and port
    if (  bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0  ) 
    { 
        perror("bind failed"); 
        exit(EXIT_FAILURE); 
    } 
    if (listen(server_fd, 3) < 0) 
    { 
        perror("listen failed"); 
        exit(EXIT_FAILURE); 
    }

    FD_ZERO(&master);
    FD_ZERO(&read_fds);
    FD_SET(server_fd, &master);

    fdmax = server_fd;
    
    // Main loop
    while (1) {
        read_fds = master;

        select(fdmax+1, &read_fds, NULL, NULL, NULL);

        for (i = 0; i <= fdmax; i++) {
            if (FD_ISSET(i, &read_fds)) {
                if (i == server_fd) { // New conection, accept() it
                    if ((new_socket = accept(server_fd,
                                             (struct sockaddr *)&address,  
                                             (socklen_t*)&addrlen))<0) { 
                        perror("accept failed"); 
                        exit(EXIT_FAILURE); 
                    }
                    printf("Client connected.\n");
                    
                    if (new_socket > fdmax) {
                        fdmax = new_socket;
                    }
                    FD_SET(new_socket, &master);
                    
                    //Guardar usernames
                    read(new_socket, buf, BUFSIZE);
                    buf[strlen(buf)-1] = '\0';

                    strcpy(usernames[new_socket],buf);
                }

                else { // "Old" client sent data, read() it
                    if (process_client(i) == 0) { // client close()d
                        FD_CLR(i, &master);
                        close(i);
                        printf("Client %s disconnected.\n", usernames[i]);
                        //Enviar username que se disconectou
                        for(int j = 0; j <= fdmax; j++){
                            if(FD_ISSET(j,&master)&& j!=server_fd){
                            char str1[100];
                            sprintf(str1,"Client %s disconnected\n",usernames[i]);
                            write(j,str1, strlen(str1)+1);
                            }
                        }
                    }
                    else { /* already processed */ }
                }
            }
        }
    }
    return 0; 
} 
