#include "stackar.h"
#include "fatal.h"
#include <stdlib.h>


#define EmptyTOS ( -1 )
#define MinStackSize ( 5 )


struct StackRecord
{
    int Capacity;
    int TopOfStack;
    ElementType *Array;
};



Stack CreateStack( int MaxElements )
{

    Stack S;	

	if( MaxElements < MinStackSize )
		Error( "Stack size is too small" );

	S = malloc( sizeof( struct StackRecord ) );
	if( S == NULL )
		FatalError( "Out of space!!!" );

	S->Array = malloc( sizeof( ElementType ) * MaxElements );
	if( S->Array == NULL )
		FatalError( "Out of space!!!" );

	S->Capacity = MaxElements;
	MakeEmpty( S );

	return S;
}



void DisposeStack( Stack S )
{
    if( S != NULL )
    {
        free( S->Array );
        free( S );
    }
}


int IsEmpty( Stack S )
{
	if(S->TopOfStack==EmptyTOS){
		return 1;
	}else{
		return 0;
	}

}


int IsFull( Stack S )
{
	if(S->TopOfStack==S->Capacity-1){
		return 1;
	}else{
		return 0;
	}
}


void MakeEmpty( Stack S )
{
	S->TopOfStack==EmptyTOS;
}


void Push( ElementType X, Stack S )
{
	if(IsFull(S)==1){
		printf("\nPILHA CHEIA");
	}else{
		S->TopOfStack++;
		S->Array[S->TopOfStack]=X;
	}
}


ElementType Top( Stack S )
{
	if(IsEmpty(S)==1){
		printf("\nPILHA VAZIA");
	}else{
		return S->Array[S->TopOfStack];
	}
}


ElementType Pop( Stack S )
{
	if(IsFull(S)==1){
		printf("\nPILHA CHEIA");
	}else{
		return S->Array[S->TopOfStack];
		S->TopOfStack--;
	}
}