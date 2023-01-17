#include <stdlib.h>
#include <stdbool.h>
#include "queue.h"
#include "fatal.h"


#define MinQueueSize ( 5 )

struct QueueRecord{
    int Capacity;
    int Front;
    int Rear;
    ElementType *Array;
};


/* FUNCOES AUXILIARES */
/* numero de elementos na fila */
int size( Queue Q ){

    return Q->Capacity-(Q->Front+Q->Rear)%Q->Capacity;

}


/* indice do proximo elemento  */
int successor( int i, Queue Q ){

    if(Q->Capacity-1==i){
        return 0;
    }else{
        return i+1
    }
}



/* FUNCOES DE MANIPULACAO DE QUEUES */
Queue CreateQueue( int MaxElements ){
    Queue Q;

    if( MaxElements < MinQueueSize )
        Error( "Queue size is too small" );

    Q = malloc( sizeof( struct QueueRecord ) );
    if( Q == NULL )
        FatalError( "Out of space!!!" );

    Q->Array = malloc( sizeof( ElementType ) * MaxElements );
    if( Q->Array == NULL )
        FatalError( "Out of space!!!" );

    Q->Capacity = MaxElements+1;
    MakeEmptyQueue( Q );

    return Q;
}


void DisposeQueue( Queue Q ){
    if( Q != NULL ){
        free( Q->Array );
        free( Q );
    }
}


bool IsEmptyQueue( Queue Q ){
    return Q->Front==Q->Rear;
}


bool IsFullQueue( Queue Q ){
    return size(Q)==Q->Capacity-1;
}


void MakeEmptyQueue( Queue Q ){
    if(IsEmptyQueue(Q)){
        ERROR ("STACK EMPTY");
    }else{
        Q->Front=0;
        Q->Rear=0;
    }
}


void Enqueue( ElementType X, Queue Q ){
    if(IsFullQueue(Q)){
        ERROR("STACK IS FULL");
    }else{
        X=Q->Array[Q->Rear];
        Q->Rear=successor(Q->Rear,Q)
    }
}


ElementType Front( Queue Q ){
    if(IsEmptyQueue(Q)){
        ERROR ("STACK IS EMPTY");
    }else{
        return Q->Array[Q->Front];
    }
}


ElementType Dequeue( Queue Q ){
    ElementType X=0;
    if(IsEmptyQueue(Q)){
        ERROR("STACK IS EMPTY")
    }else{
        X=Q->Array[Q->Front];
        Q->Front=successor(Q->Front,Q);
    }
}
