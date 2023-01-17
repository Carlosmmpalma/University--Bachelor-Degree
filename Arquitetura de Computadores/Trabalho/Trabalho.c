#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
    void Insert();
    void Append();
    void Change();
    void Delete();
    void Print();
    void Edit();
    void Write();
    int Quitupdate();
    int Quit();

    char var;
    int contador;
    printf("Indique o que pretende\n");
    scanf("%c",&var);


    if(var=='f'){
        
    }if(var=='e'){
        Edit();
    }if(var=='i'){
        Insert();
    }if(var=='a'){
        Append();
    }if(var=='c'){
        Change();
    }if(var=='d'){
        Delete();
    }if(var=='p'){
        Print();
    }if (var=='w'){
        Write();
    }if (var=='q'){
        Quitupdate();
    }if (var=='Q'){
        Quit();
    }

    return 0;
}

void Insert(){
     


}

void Append(){


}

void Change(){

}

void Delete(){

}

void Print(){

}

void Edit(){

    FILE *pointer;
    
    pointer=fopen(nome,"a+");
    

}

void Write(){

}

int Quitupdate(){

}

int Quit(){


}



