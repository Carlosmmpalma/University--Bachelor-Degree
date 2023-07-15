% estado -> m(linha,coluna)
estado_inicial(m(7,2)).
estado_final(m(1, 5)).

%bloqueios
bloqueio(m(1,3)).
bloqueio(m(2,1)).
bloqueio(m(2,3)).
bloqueio(m(2,7)).
bloqueio(m(4,4)).
bloqueio(m(5,4)).
bloqueio(m(6,4)).

% movimentos -> op(estado_atual, movimento, estado_final, custo)
op(m(X,Y), cima, m(X,Y1),1) :-
    Y>1,
    Y1 is Y-1, 
    \+ bloqueio(m(X,Y1)).

op(m(X,Y), baixo, m(X,Y1),1) :-
    Y<7,
    Y1 is Y+1, 
    \+ bloqueio(m(X,Y1)).

op(m(X,Y), direita, m(X1,Y),1) :-
    X<7,
    X1 is X+1, 
    \+ bloqueio(m(X1,Y)).

op(m(X,Y), esquerda, m(X1,Y),1) :-
    X>1,
    X1 is X-1, 
    \+ bloqueio(m(X1,Y)).

%heuristicas
manhattan(m(A,B),C):-
	estado_final(m(X,Y)),
	X1 is abs(A - X), 
 	Y1 is abs(B - Y),
	C is X1 + Y1.

euclidiana(m(Ix,Iy),SOMA):-
	estado_final(m(Fx,Fy)),
	Dx is abs(Ix - Fx), 
 	Dy is abs(Iy - Fy),
	SOMA is round(sqrt(Dy ** 2 + Dx ** 2)).

h(A, B) :- manhattan(A, B).
%h(A, B) :- euclidiana(A, B).

