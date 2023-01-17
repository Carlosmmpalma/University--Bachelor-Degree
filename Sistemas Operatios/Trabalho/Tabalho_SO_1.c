//============================================================================
// Operating System Simulator
//============================================================================
#include <stdio.h>
#define SIZE 1000
#define MAX_PROCESSOS 10

//Struct para guardar info relativo a cada processo
typedef struct {
    int indice; //Indice da linha da matriz em que o processo se encontra                                                                                                   
    int estado; //Estado atual
    int novo;   //Instante em que o processo inicia                                                                                                      
    int ciclo[MAX_PROCESSOS]; //Guarda o tempo em blocked ou run(linha da matriz)
} processos;

int inst = 0, num_proc = 0;
processos process[MAX_PROCESSOS];

//Queue para Ready
int ready_queue[SIZE], ready_front = -1, ready_tail = -1;   
//Queue para Blocked
int block_queue[SIZE], block_front = -1, block_tail = -1;                                                               

//Frente da queue
int front(int queue_number) {                                                                                           
    if (queue_number == 0) {                                                                                           
        if (ready_front != -1)
            return ready_queue[ready_front];
    } else if (queue_number == 1) {                                                                                    
        if (block_front != -1)
            return block_queue[block_front];
    }
    return -1;
}

//Colocar da Queue
void enqueue(int value, int queue_number) {                                                                             
    if (queue_number == 0) {                                                                                           
        if (ready_tail == SIZE - 1)
            return;

        if (ready_front == -1)
            ready_front = 0;

        ready_tail++;
        ready_queue[ready_tail] = value;
    } else if (queue_number == 1) {                                                                                     
        if (block_tail == SIZE - 1)
            return;

        if (block_front == -1)
            block_front = 0;
        block_tail++;
        block_queue[block_tail] = value;
    }
}
//Retirar da queue
int dequeue(int queue_number) {                                                                                         
    int aux;
    if (queue_number == 0) {                                                                                            
        if (ready_front == -1)
            return -1;

        aux = ready_queue[ready_front];
        ready_front++;

        if (ready_front > ready_tail) {
            ready_front = -1;
            ready_tail = -1;
        }

        return aux;
    } else if (queue_number == 1) {                                                                                     
        if (block_front == -1)
            return -1;

        aux = block_queue[block_front];
        block_front++;

        if (block_front > block_tail) {
            block_front = -1;
            block_tail = -1;
        }

        return aux;
    }
    return -1;
}

void mudanca_estado(int count) {                                                                 
    int running;
    int aux;
    switch (process[count].estado) {
        //PROCESSO ENCONTRA-SE TERMINADO
        case 0:                                                                                                         
            process[count].estado = 6;                                                                           

            break;
        //PROCESSO PASSA DO READY PARA RUN
        case 1:                                                                                                         
            dequeue(0);
            process[count].estado = 2;                                                                             
            process[count].indice++;

            break;
        case 2:                            
                //PROCESSO PASSA DE RUN PARA EXIT                                                                           
                if (process[count].ciclo[process[count].indice + 1] == 0)
                    process[count].estado = 0;   
                //PROCESSO PASSA DE READY PARA RUN                                                                 
                else {
                    enqueue(count, 1);
                    process[count].estado = 3;                                                                     
                    process[count].indice++;
                }

                aux = front(0);
                //PROCESSO PASSA DE READY PARA RUN
                if (aux != -1) {
                    dequeue(0);
                    process[aux].estado = 2;                                                                            
                    process[aux].indice++;
                }

            break;

        case 3:                                                                                                                                                                                                // Round Robin Standard (RR)
                aux = dequeue(1);
                //PROCESSO PASSA DE BLOCKED PARA READY
                if (aux != -1) {
                    process[aux].estado = 1;                                                                            
                    enqueue(aux, 0);
                }
            break;
        case 4:     
            //CRIADO NOVO PROCESSO                                                                                                   
            process[count].estado = 5;                                                                             

            break;
        case 5:                                                                                                          
            running = 0;
            //Enquanto estado igual 2 está em run
            for (int i = 0; i < num_proc; i++) {
                if (process[i].estado == 2)
                    running=1;
            }
            if(running==0){
                process[count].estado = 2;
                process[count].indice++;
            //PROCESSO PASSA DE NEW PARA READY
            } else{
                process[count].estado = 1;                                                                         
                enqueue(count, 0);
            }

            break;
}
}

void algoritmo(int quantum) {                                                                               
    for (int i = 0; i < num_proc; i++)
        if (process[i].novo == 0)
            process[i].estado = 5;

    inst = 1;
    int quantum_unity = quantum;

    while (inst != 0) {
        int is_running = 0;
        int number_of_executing_process=0;
        int running_processos;

        printf("%d ", inst);

        for (int i = 0; i < num_proc; i++) {
            //SE O PROCESSO ESTÁ EM RUN OU BLOCKED DECREMEMENTA
            if ((process[i].estado == 2) || (front(1) == i && process[i].estado == 3))                                  
                process[i].ciclo[process[i].indice]--;

            switch (process[i].estado) {
                //EXIT
                case 0:
                    printf("\t  EXIT");
                    break;
                //READY
                case 1:
                    printf("\t  READY");
                    break;
                //RUN
                case 2:
                    printf("\t  RUN");
                    is_running = 1;
                    quantum_unity--;
                    running_processos = i;
                    break;
                //BLOCKED
                case 3:
                    printf("\t  BLOCK");
                    break;
                //ESPAÇOS PARA QUANDO O PROCESSO AINDA NÃO INICIOU
                case 4:
                    printf("\t  ");
                    break;
                //NEW
                case 5:
                    printf("\t  NEW");
                    break;
                //ESPAÇOS PARA QUANDO PROCESSO JÁ TERMINOU
                case 6:
                    printf("\t  ");
                    break;
            }
        }

        for (int i = 0; i < num_proc; i++) {
            //Termina o processo se está em exit
            if (process[i].estado == 0)                                                                                 
                mudanca_estado(i);

            //Se o ciclo termina para de estar em RUN
            if (process[i].ciclo[process[i].indice] == 0 && process[i].estado == 2) {                                    
                    if (front(0) == -1)                                                                                 
                        is_running = 0;

                mudanca_estado(i);
                quantum_unity = quantum;
            }
            //Se o ciclo termina para de estar em READY
            if (process[i].ciclo[process[i].indice] == 0 && process[i].estado == 3)                                     
                mudanca_estado(i);
            //Se o processo ainda nao terminou incrementamos o indice do processo
            if (process[i].estado != 6)                                                                                 
                number_of_executing_process++;
        }

        printf("\n");

                                                                                                    
        if (is_running == 0 && front(0) != -1)                                                                 
                mudanca_estado(front(0));

        //SE O O QUANTUM CHEGA A 0 TERMINA E PASSA DE RUN PARA READY
        if (quantum_unity == 0) {                                                                                       
             int ready = front(0);
             int aux = front(2);                                                                                   
        if (ready != -1) {
            dequeue(0);
            process[ready].estado = 2;                                                                                 
            process[ready].indice++;
            enqueue(running_processos, 0);
            process[running_processos].estado = 1;                                                                            
            process[running_processos].indice--;
        }
            quantum_unity = quantum;
        }
        //PROCESSO PASSA DE NEW PARA READU
        for (int i = 0; i < num_proc; i++) {
            if (process[i].estado == 5)                                                                                
                mudanca_estado(i);
        //Se estamos no instante inicial cria o processo
            if (process[i].novo == inst && process[i].estado == 4)                                                 
                mudanca_estado(i);
        }

        inst++;
        //Termina se nao existem mais processos
        if(number_of_executing_process == 0) {                                                                       
            inst = 0;
        }

    }
}


int main() {
   int programas[3][10] = {
{0, 3, 1, 2, 2, 4, 0, 0, 0, 0 } ,
{1, 4, 2, 4, 1, 1, 0, 0, 0, 0 } ,
{3, 2, 1, 6, 1, 3, 1, 1, 0, 0 } };

    int rows = sizeof(programas) / sizeof(programas[0]);
    int cols = sizeof(programas[0]) / sizeof(programas[0][0]);

    int quantum=3;

    num_proc = rows;

    printf("inst  ");

    for (int i = 0; i < rows; i++) {

        printf("\t  proc%d", i + 1);

        process[i].indice = 0;
        process[i].estado = 4;

        for (int j = 0; j < cols; j++)
            process[i].ciclo[j] = programas[i][j];

        process[i].novo = process[i].ciclo[0];
    }

    inst = 0;

    printf("\n");

    algoritmo(quantum);


    return 0;
}