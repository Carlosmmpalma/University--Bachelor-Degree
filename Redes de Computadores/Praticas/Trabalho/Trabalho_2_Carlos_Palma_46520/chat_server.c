#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h> 
#include <time.h>        

#define PORT 1234
#define BUFSIZE 256
#define MAXCLIENT 15

int server_fd, new_socket; 
int fdmax;
char usernames[MAXCLIENT][15]; // Array para os usernames
int indice=0; //Indice do post
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
    char s[300]; //MENSAGENS
    char p[256][256]; //POSTS

    //Enviar para todos
    if(strncmp(&buf[0],"HELLO",5)==0){ //NICKNAMES
        int count=-1; //Para verificar se já existe algum nickname igual
        for(int i = 0; i <= fdmax; i++){
            buf[strlen(buf)] = '\0';
            if(FD_ISSET(i,&master)&& i!=server_fd && strncmp(&buf[6],usernames[i],strlen(usernames[i])+1)==0) count++; //Percorre todos os usernames e se houver um igual incrementa o contador
        }
        if(count==-1){  // Se n existir o nick é adicionado ao array e escrita a mensagem "OK NICK <nickname>\n" para o cliente
            sprintf(usernames[sock],"%s",&buf[6]);
            usernames[sock][strlen(usernames[sock])-1]='\0';
            s[0]='\0';
            strcat(strcpy(s, "OK NICK "), usernames[sock]);
            strcat(s,"\n");
            write(sock,s, strlen(s));
        }else{  //Se  existir o nick é escrita a mensagem "ERR NICK <nickname>\n" para o cliente
            s[0]='\0';
            strcat(strcpy(s, "ERR NICK "), usernames[sock]);
            strcat(s,"\n");
            write(sock,s, strlen(s));
        }


    }else if(strncmp(&buf[0],"MSG",3)==0){ //MSG

        if(strncmp(&buf[4],"GLOBAL",6)==0){ // Se a mensagem for global envia para todos os sockets conectados excepto para ele mesmo a mensagem a seguir à tag GLOBAL
            for(int i = 0; i <= fdmax; i++){
                if(FD_ISSET(i,&master)&& i!=server_fd && i!=sock){
                     s[0]='\0';
                    strcat(s,"TEXT ");
                    strcat(s,usernames[sock]);
                    strcat(s," GLOBAL ");
                    strcat(s,&buf[11]);
                    s[strlen(s)]=='\0';
                    write(i,s, strlen(s));
            }
        }   
            //É escrita a mensagem "OK MSG GLOBAL\n" para o cliente
            s[0]='\0';
            strcat(s,"OK MSG GLOBAL\n");
            s[strlen(s)]=='\0';
            write(sock,s, strlen(s));

        }else if(strncmp(&buf[4],"USER",4)==0){ //Verifica se existe o username no array, se existir envia  a mensagem a seguir ao username que está no buffer
            for(int i=0; i<= fdmax; i++){
                if(FD_ISSET(i,&master) && i!=server_fd && strncmp(&buf[9],usernames[i],strlen(usernames[i])-1)==0){
                    s[0]='\0';
                    strcat(s,"TEXT ");
                    strcat(s,usernames[sock]);
                    strcat(s,&buf[3]);
                    s[strlen(s)]=='\0';
                    write(i,s, strlen(s));

                    s[0]='\0';
                    strcat(strcpy(s, "OK MSG USER "), usernames[i]);
                    strcat(s,"\n");
                    write(sock,s,strlen(s));
                }

            }

        }else{ //É escrita a mensagem "ERR MSG\n" para o cliente pois occoreu um erro
            s[0]='\0';
            strcat(s,"ERR MSG\n");
            s[strlen(s)]=='\0';
            write(sock,s, strlen(s));

        }

    }else if(strncmp(&buf[0],"POST",4)==0){ //POST
        int count=0;
        if(strncmp(&buf[5],"GLOBAL",6)==0){ // Adiciona à matriz onde estão guardados os posts o post do cliente e incrementa o indice 
                s[0]='\0';
                strcat(s,&buf[12]);
                s[strlen(s)]=='\0';
                sprintf(p[indice],"%s",s);
                indice++;
                count++;
            if(count==1){
                s[0]='\0';
                strcat(s,"OK POST GLOBAL\n"); //É escrita a mensagem "OK POST GLOBAL\n" pois o post foi aceite
                s[strlen(s)]=='\0';
                write(sock,s, strlen(s));
            }else{
                s[0]='\0';
                strcat(s,"ERR POST\n"); //É escrita a mensagem "ERR POST\n" pois o post n foi aceite 
                s[strlen(s)]=='\0';
                write(sock,s, strlen(s));
            }
        }else if(strncmp(&buf[5],"FILE",4)==0){}

    }else if(strncmp(&buf[0],"READ",4)==0){
        int count=0;
         if(strncmp(&buf[5],"GLOBAL",6)==0){ // São escritos todos os posts em memória 1 a 1
                if(indice==0){
                    write(sock,"0\n",2);
                    count++;
                }else{
                s[0]='\0';
                char s1[10];
                sprintf(s1,"%d\n",indice);
                strcat(strcpy(s,"OK ALLPOSTS GLOBAL "),s1);
                write(sock,s, strlen(s));
                for(int i=0; i < indice; i++){
                    s[0]='\0';
                    sprintf(s,"POST %d: %s",i+1,p[i]);
                    write(sock,s, strlen(s));
                }
            count++;
                }
         }
        if(count==0){
            s[0]='\0';
            strcat(s,"ERR ALLPOSTS\n"); //É escrita a mensagem "OK POST GLOBAL\n" pois ocorreu um erro
            s[strlen(s)]=='\0';
            write(sock,s, strlen(s));
        }


    }else if(strncmp(&buf[0],"FILE",4)==0){ //Envio de ficheiros
        if(strncmp(&buf[5],"USER",4)==0){ 
            for(int i=0;i<= fdmax; i++){
            if(FD_ISSET(i,&master) && i!=server_fd && strncmp(&buf[10],usernames[i],strlen(usernames[i])-1)==0){ //Verifica se o utilizador existe no array
                int n;
                char filename[20];
                filename[0] = '\0';
                strncpy(filename,&buf[11+strlen(usernames[i])],strlen(buf)); //Nome ficheiro
                filename[strlen(filename)-1]='\0';
                int size=atoi(&buf[10+strlen(usernames[i])+strlen(filename)]); //Tamanho do ficheiro
                FILE *fp;
                char buffer[size];

                fp = fopen(filename, "r"); //Abre o ficheiro
                if (fp == NULL) {
                perror("[-]Erro a ler o ficheiro");
                 write(sock,"ERR FILE\n",9); //Se n existir o ficheiro retorna ERRO
                }
                    char *contents = NULL;
                    size_t len = 0;

                s[0]='\0';
                strcat(s,"FILEFROM ");
                strcat(s,usernames[sock]);
                strcat(s," USER ");
                strcat(s,usernames[i]);
                strcat(s," ");
                strcat(s,filename);
                strcat(s,"\n");

                write(i,s, strlen(s));

                while (getline(&contents, &len, fp) != -1){ //Se existir envia ao cliente linha por linha
                    write(i,contents, strlen(contents));
                     }
                 bzero(buffer, size);
                 write(i,"\n",1);

            
            write(sock,"OK FILE\n",9);
            }
            }
        }else write(sock,"ERR FILE\n",9);
    
    }else{

       write(sock,"INVALID ENTRY\n",14);
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
                    
                }else { // "Old" client sent data, read() it
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
