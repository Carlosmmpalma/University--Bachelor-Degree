%Maximo divisor comum
mdc(I, 0, I).
mdc(I, J, MDC) :-J > 0,R is I mod J,mdc(J, R, MDC).

%Outra implementação do maximo divisor comum
mdc1(N, N, N).
mdc1(A, B, C) :- A>B, X is A-B, mdc(X, B, C).
mdc1(A, B, C) :- A<B, X is B-A, mdc(A, X, C). 

%Factorial de numero
fact(N, F) :- fact(N, 1, F).

fact(0, F, F).
fact(N, T, F) :-N > 0,T1 is T*N,N1 is N-1,fact(N1, T1, F).

%Soma dos elementos de uma lista
soma(Is, S) :- soma(Is, 0, S).

soma([], S, S).
soma([I|Is], T, S) :-T1 is T+I,soma(Is, T1, S).

%Passar de matriz para array(junta tudo)
flatten([X|Xs], Ys) :-
flatten(X, Y1), flatten(Xs, Y2), append(Y1, Y2, Ys).
flatten(X, [X]) :- atomic(X), X \== []. % não lista
flatten([], []).

