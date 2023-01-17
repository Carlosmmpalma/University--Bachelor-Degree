#include "stackar.h"
#include "fatal.h"
#include <stdlib.h>

#define EmptyTOS (-1)
#define MinStackSize (5)

struct StackRecord
{
	int Capacity;
	int TopOfStack;
	ElementType *Array;
};

Stack CreateStack(int MaxElements)
{

	Stack S;

	if (MaxElements < MinStackSize)
		Error("Stack size is too small");

	S = malloc(sizeof(struct StackRecord));
	if (S == NULL)
		FatalError("Out of space!!!");

	S->Array = malloc(sizeof(ElementType) * MaxElements);
	if (S->Array == NULL)
		FatalError("Out of space!!!");

	S->Capacity = MaxElements;
	MakeEmpty(S);

	return S;
}

void DisposeStack(Stack S)
{
	if (S != NULL)
	{
		free(S->Array);
		free(S);
	}
}

bool IsEmpty(Stack S)
{
	return S->TopOfStack==EmptyTOS;
}

bool IsFull(Stack S)
{
	return S->TopOfStack==S->Capacity-1;
}

void MakeEmpty(Stack S)
{
	S->TopOfStack==EmptyTOS;
}

void Push(ElementType X, Stack S)
{
	if(IsFull(S)){
		ERROR("Full Stack");
	}else{
		TopOfStack++;
		X=S->Array[S->TopofStack];
	}
	}

ElementType Top(Stack S)
{	
	if(IsEmpty(S)){
		ERROR("Empty Stack");
	}else{
	return S->Array[S->TopOfStack];
}
}

ElementType Pop(Stack S)
{
	if(IsEmpty(S)){
		ERROR("Empty Stack");
	}else{
		TopOfStack--;
		S->Array[S->TopofStack];
	}
}