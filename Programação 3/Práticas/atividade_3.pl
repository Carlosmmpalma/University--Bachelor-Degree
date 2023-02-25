%2

%a)
membro(X, [X|_]).
membro(X, [_|L]) :- membro(X, L).

%b)
/*Uma lista vazia é sempre prefixo de qlq lista, uma lista A é prefixo de B se B tiver coisas acrescentadas à direitaem relação a A *\
/*A=[1,2] e B=[1,2,3,4] caso A=[1,2,3,4] e B=[1,2] já não é verdade*/
prefixo([],_).
prefixo([X|A],[X|B]) :- prefixo(A,B).

%c)
/*Uma lista é sempre sufixo dela mesma, A é sufixo de B se A==B ou seja, é sufixo se B tiver coisas acrescentadas à esquerda em relação a A, de resto têm de ser iguais*/
/*A=[1,2,3] e B=[7,5,1,2,3]*/
sufixo(A,A).
sufixo(A,[_|B]):-sufixo(A,B).

%d)
/*Uma sublista é um prefixo de um sufixo(vice-versa)*/
/*A tem de estar contida na lista B*/
/*A=[1,2] E B=[7,5,1,2,3]*/
sublista(S,L):- prefixo(P,L),sufixo(S,P).

%e)
/*Concatenar uma lista vazia a uma qlq lista resulta na mesma lista*/
/*Se concatenar mos qlq lista a X resulta numa lista que começa por X*/
/* catena([1,2], [3,4], X) <==> X = [1,2,3,4]*/
catena([], L, L).
catena([X|Xs], L, [X|Y]) :- catena(Xs, L, Y).

%f)
/*rev([1,2,3],X) <==> X=[3,2,1]*/
rev(L, R) :- rev(L, [], R).
rev([], R, R).
rev([A|B], X, R) :- rev(B, [A|X], R).

%inverte([1,2,3],[3,2,1]) <==> yes 
%verifica se L1 invertida é igual  a L2
inverte(L1,L2):- X=L2, rev(L1,X).

%f_1)
/*nrev([1,2,3],X) <==> X=[3,2,1]*/
nrev([], []).
nrev([X|A], B) :- nrev(A, AR), catena(AR, [X], B).

%f_2) Para a recursão terminal existir não pode haver nada à direita da ultima chamada recursiva
%O que acontece em inverte pois a ultima chamada recursiva encontra-se à direita.

%g)

compr([], 0).
compr([_|T], X) :- compr(T, Y), X is Y+1.

tamanho(L,T):- compr(L,T).

%g_2) Nao apresenta recursividade terminal pois a ultima clausula recursiva encontra-se à direita.

%1)
sequencia(A,B,L):-membro(A,L).
sequencia(A,B,L):-A\=B, X is A+1, sequencia(A,B,L).

