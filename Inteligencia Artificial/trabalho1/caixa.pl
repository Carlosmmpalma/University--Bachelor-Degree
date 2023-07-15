% estado -> m(agente-linha,agente-coluna,caixa-linha,caixa-coluna)
estado_inicial(m(7,2,6,2)).
estado_final(m(_,_,1,5)).

%bloqueios
bloqueio(m(1,3)).
bloqueio(m(2,1)).
bloqueio(m(2,3)).
bloqueio(m(2,7)).
bloqueio(m(4,4)).
bloqueio(m(5,4)).
bloqueio(m(6,4)).

% limites do tabuleiro
limite(X, Y) :- 
    X =< 7,
    X >= 1,
    Y =< 7,
    Y >= 1.

% verificar se duas posições são iguais
iguais(P1, P2).

% operadores de estado -> op(estado_atual, operador, estado_seguinte, custo)
op(m(X, Y, P, Q), cima, m(X, Y1, P, Q1), 1) :-
    Y1 is Y - 1,
    (iguais(m(X, Y1), m(P, Q)) -> 
        (
            Q1 is Q - 1,
            limite(P, Y1),
            \+ bloqueio(m(P, Q1))
        );
        (
            Q1 is Q,
            limite(X, Y1),
            \+ bloqueio(m(X, Y1))
        )
    ).
op(m(X,Y,P,Q),direita,m(X1,Y,P1,Q),1) :-
    X1 is X+1,
    (iguais(m(X1,Y),m(P,Q)) ->
        (
            P1 is P+1,
            limite(P1,Q),
            limite(X1,Y),
            \+ bloqueio(m(P1,Q))
        );
        (
            P1 is P,
            limite(X1,Y),
            \+ bloqueio(m(X1,Y))
        )
    ).
op(m(X,Y,P,Q),baixo,m(X,Y1,P,Q1),1) :-
    Y1 is Y+1,
    (iguais(m(X,Y1),m(P,Q)) ->
        (
            Q1 is Q+1,
            limite(P,Q1),
            limite(X,Y1),
            \+ bloqueio(m(P,Q1))
        );
        (
            Q1 is Q,
            limite(X,Y1),
            \+ bloqueio(m(X,Y1))
        )
    ).
op(m(X,Y,P,Q),esquerda,m(X1,Y,P1,Q),1) :-
    X1 is X-1,
    (iguais(m(X1,Y),m(P,Q)) ->
        (
            P1 is P-1,
            limite(P1,Q),
            limite(X1,Y),
            \+ bloqueio(m(P1,Q))
        );
        (
            P1 is P,
            limite(X1,Y),
            \+ bloqueio(m(X1,Y))
        )
    ).

manhattan(m(_,_,Ix,Iy),SOMA):-
	estado_final(m(_,_,Fx,Fy)),
	Dx is abs(Ix - Fx), 
 	Dy is abs(Iy - Fy),
	SOMA is Dx + Dy.

euclidiana(m(_,_, _,Iy),SOMA):-
	estado_final(m(_,_,_,Fy)),
	Dy is abs(Iy - Fy), 
	SOMA is Dy.

%h(A, B) :- manhattan(A, B).
h(A, B) :- euclidiana(A, B).