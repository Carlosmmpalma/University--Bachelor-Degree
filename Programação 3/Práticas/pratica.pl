homem(joao).
homem(rui).
homem(manuel).
homem(ricardo).
mulher(maria).
mulher(ana).
mulher(rita).
mulher(silvia).

progenitor(joao, maria).
progenitor(joao, rui).
progenitor(manuel, joao).
progenitor(ricardo, manuel).
progenitor(ana, rui).
progenitor(rita, joao).
progenitor(rita, silvia).

pai(X, Y) :- homem(X), progenitor(X, Y).

avo(A,B) :- progenitor(A,X), progenitor(X,B).

antepassado(A,B) :- progenitor(A,B).
antepassado(A,B) :- progenitor(A,X), antepassado(X,B).

irmao(A,B):- progenitor(X,B),progenitor(X,A), A \= B.

tio(A,B):- progenitor(X,B), irmao(A,X); progenitor(Y,A), irmao(B,Y).



