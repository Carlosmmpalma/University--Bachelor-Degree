#include <stdio.h>
#include  <stdlib.h>
#include <string.h>

int linha=0;
char nome_ficheiro[30];
FILE *fp;

int Linhas(char ficheiro[],int contador[]); //Contador de linhas e caracteres de cada linha
void Insert(char ficheiro[],int contador[]); //Inserçcao de caracteres na linha atual
void Append(char ficheiro[],int contador[]); //Adiçao de caracteres à linha atual
void Print(char ficheiro[],int contador[]); //Impressao da linha atual
void Change(char ficheiro[], int contador[]); //
void Delete(char ficheiro[],int contador[]); //Eliminacao da linha atual
void File(char nome[]);//Definiçao de nome do ficheiro
void Edit(); //Abretura do ficheiro
void quit(char ficheiro[]);//Sair e guardar respetivamente o ficheiro
void Quit();//Sair sem guardar o ficheiro



int main(int argc, char const *argv[])
{
    char ficheiro[3000]; //String
    int contador[3000]; //Contador de caracteres de cada linha
    char escolha[30]; //Char para utilizador escolher comando a utilizar
    
    while (escolha[0]!='.')
    {
    
    scanf("%s ", escolha);

    if(escolha[0]=='i'){
        Insert(ficheiro,contador);
    }else if (escolha[0]=='a')
    {
        Append(ficheiro,contador);
    }else if (escolha[0]=='d')
    {
        Delete(ficheiro,contador);
    }else if (escolha[0]=='p')
    {
        Print(ficheiro,contador);
    }else if (escolha[0]=='e')
    {
        Edit();
    }else if (escolha[0]=='f')
    {
        File(escolha);
    }else if (escolha[0]=='q')
    {
        quit(ficheiro);
    }else if (escolha[0]=='Q')
    {
        Quit();
    }
    }
    
    return 0;
}

int Linhas(char ficheiro[], int contador[]){

    for (int i = 0; ficheiro[i] !='\0'; i++)
    {
        if(ficheiro[i]=='\n'){
        contador[linha]=i; //contador para caracteres
        linha++;//incremento para sempre que é adicionada uma nova linha
        }
    }
    return linha;
}

void Insert(char ficheiro[],int contador[]){

    scanf("%[^\n]", ficheiro); /* Ler os dados */
    scanf("%*[^\n]"); /* Limpar o buffer */
    scanf("%*c"); /* Voltar ao passo 1. */

    strcat(ficheiro,"\n"); //concatenaçao com '\n' para realizar parágrafo

    Linhas(ficheiro,contador);// chamada de funçao para atualizar com nova linha e contar caracteres

}

void Append(char ficheiro[],int contador[]){

    char concatenar[3000]; //String a concatenar
    char string[3000]; //string princiPAL

    for (int i = contador[linha]; i <'\0'; i++)//Substituiçao do '\n' por espaço
    {
        if (ficheiro[i]=='\n')
        {
            ficheiro[i]=' '; 
        }
        
    }

     scanf("%[^\n]",concatenar); /* Ler os dados */
     scanf("%*[^\n]"); /* Limpar o buffer */
     scanf("%*c"); /* Voltar ao passo 1. */

     strcat(ficheiro,concatenar);//Junçao da frase a adicionar
     strcat(ficheiro,"\n");//Parágrafo final
     
     linha--;//Estamos apenas a adicionar frase a linha já existente log->
     Linhas(ficheiro,contador);
}


 void Change(char ficheiro[], int contador[]){

    char string[3000];
    int j=0;
    int a=0,b=0,c=0;

    for (int i = contador[linha]; i != contador[linha+1]; i++)
    {
        ficheiro[i]='0';
        j++;
    }

     scanf("%[^\n]",string); /* Ler os dados */
     scanf("%*[^\n]"); /* Limpar o buffer */
     scanf("%*c"); /* Voltar ao passo 1. */

     if (strlen(string)==j)
     {
         for (int i = contador[linha]; i != contador[linha+1]; i++)
    {
        if (ficheiro[i]=='0')
        {
            ficheiro[i]=string[a];
            a++;
        } 
    }
     }
     
     

} 

void Delete(char ficheiro[],int contador[]){

for (int i = contador[linha]; i != contador[linha+1]; i++) //Substituiçao dos caracteres que pretendemos remover por 0's
    {
        ficheiro[i]='0';
    }

    for (int i = contador[linha]; i != '\0'; i++)//Percorrer a parte do array desde o inicio da parte que pretendemos remover
    {
        while (ficheiro[i]=='0')
        {
            ficheiro[i]=ficheiro[i+1]; //Andar com array para trás até à inexistencia de 0's
        }
        
    }
    
    
}



void Print(char ficheiro[],int contador[]){

    char string[3000]; //Vetor para guardar String que pretendemos imprimir
    int j=0; 

    for (int i = contador[linha]; i != contador[linha+1]; i++)//Percorrer linha que pretendemos imprimir
    {   
        string[j]=ficheiro[i];//Guardar linha no vetor criado para o efeito
        j++;
    }

    printf("%s",string);//Impressao da string
    
}

void File(char nome[]){
    int j=0;
    for (int i = 2; i != '\0'; i++)
    {
        nome_ficheiro[j]=nome[i]; //Definiçao do nome do ficheiro
        j++;
    }
    
}

void Edit(){

    fp = fopen(nome_ficheiro, "w"); //Abertura do ficheiro
}


void quit(char ficheiro[]){

      if (fp==NULL)
     {
         printf("\n ERRO\n"); //Verificar se o ficheiro é válido, caso contrário dá erro
         exit(1);
    }else{
        for (int i = 0; i !='\0'; i++)
        {
            fscanf(fp,"%c",&ficheiro[i]);
        }
        
        
    }
    fclose(fp);

}

void Quit(){
    fclose(fp);
    exit(1); //Sair sem guardar ficheiro

}




