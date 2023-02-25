%a numeral de Peano
num(z).
num(s(X)) :- num(X).

%b comparação

%less or equall
le(z, X) :- num(X).
le(s(A), s(B)) :- le(A, B).

%less then
lt(z, s(X)) :- num(X).
lt(s(A), s(B)) :- lt(A, B).

%c

%soma
soma(z, X, X) :- num(X).
soma(s(X), Y, s(Z)) :- soma(X, Y, Z).

%subtração
sub(A,B,X):- soma(X,B,A).

%multiplicação
mult(z, _, z).
mult(s(A), B, X) :-mult(A, B, Y),soma(B, Y, X).

%divisão
div(A, B, Q, R) :-mult(B, Q, X),soma(X, R, A),lt(R, B).

%d conversão
num(z, 0).
num(s(X), SY) :- num(X, Y), SY is Y+1.

%e primo
