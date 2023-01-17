#include "list.h"
#include <stdlib.h>
#include "fatal.h"


struct Node{
    ElementType Element;
    Position    Next;
};


List MakeEmpty( List L ){
    if( L != NULL ){
        DeleteList( L );
    }else{
        L= malloc( sizeof( struct Node));
        if( L == NULL ){
            FatalError( "Out of memory!");
        }
        L->Next = NULL;
    }

    return L;

}

bool IsEmpty( List L ){

    return L->Next==NULL;

}

bool IsLast( Position P, List L ){

    return P->Next == NULL;

}

Position Find( ElementType X, List L ){

    Position P;
    P = First(L);
    
    while(P!= NULL && P->Element != X){
        P = P->Next;
    }

    return P;

}


Position FindPrevious( ElementType X, List L ) {

    Position P;
    P = Header(L);
    
    while(P->Next != NULL && P->Next->Element != X){
        P = P->Next;
    }

    return P;
}


void Insert( ElementType X, List L, Position P ) {
    /* insere X como seguinte de P */
    Position tmp;

    tmp = malloc( sizeof(struct Node));
    if(tmp == NULL){
        FatalError("Out of Memory!");
    }
    tmp->Element = X;
    tmp->Next = P->Next;
    P->Next = tmp;

}

void Delete( ElementType X, List L ){

    Position P, tmp;
    P = FindPrevious( X, L );
    if( !IsLast(P, L) ){
        tmp = P->Next;
        P->Next = tmp->Next;
        free(tmp);
    }
}


void DeleteList( List L ) {
    Position P, tmp;

    P = First(L);
    L->Next = NULL;
    while( P != NULL ){
        tmp = P->Next;
        free(P);
        P = tmp;
    }
}


Position Header( List L ) {

    return L;

}


Position First( List L ) {

    return L->Next;

}


Position Advance( Position P ) {

    return P->Next;

}


ElementType Retrieve( Position P ) {
    return P->Element;
}
