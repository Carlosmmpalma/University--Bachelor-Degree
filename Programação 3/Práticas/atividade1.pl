%familia

homem(chico_palma).
homem(carlos_palma).
homem(jose_maldonado).
homem(jose_francisco).
homem(carlos_machado).
homem(zacarias).

mulher(luisa_palma).
mulher(tininha).
mulher(cristina_palma).
mulher(isabel_machado).
mulher(barbara_maldonado).
mulher(carolina_maldonado).
mulher(carlota).

progenitor(carlos_palma,carlos_machado).
progenitor(carlos_palma,luisa_palma).
progenitor(isabel_machado,carlos_machado).
progenitor(isabel_machado,luisa_palma).
progenitor(jose_maldonado,carolina_maldonado).
progenitor(jose_maldonado,barbara_maldonado).
progenitor(jose_maldonado,jose_francisco).
progenitor(cristina_palma,carolina_maldonado).
progenitor(cristina_palma,barbara_maldonado).
progenitor(cristina_palma,jose_francisco).
progenitor(tininha,carlos_palma).
progenitor(tininha,cristina_palma).
progenitor(chico_palma,cristina_palma).
progenitor(chico_palma,carlos_palma).
progenitor(carlos_palma,carlota).
progenitor(cristina_palma,carlota).
progenitor(carolina_maldonado,zacarias).


%1
pai(X,Y):-progenitor(X,Y), homem(X).

%2
mae(X,Y):- progenitor(X,Y),mulher(X).

%3
irmao(X,Y):- progenitor(A,X), progenitor(A,Y), X\=Y.

%4
irma(X,Y):- progenitor(A,X),progenitor(A,Y), mulher(X), X\=Y.

%5
avos(X,Y):- progenitor(X,A),progenitor(A,Y).

%casado auxiliar
casados(X,Y):- progenitor(X,A),progenitor(Y,A).

%6
tio(X,Y):- homem(X),progenitor(A,Y),irmao(X,A).
tio(X,Y):- homem(X),progenitor(A,Y),irmao(B,A),casados(X,B).

%7
primo(X,Y):-progenitor(A,Y),progenitor(B,X),irmao(A,B).

%8
antepassado(X,Y):- progenitor(X,Y).
antepassado(X,Y):- progenitor(A,Y),antepassado(X,A).

%9
descendente(X,Y):- antepassado(Y,X).

%10
meio_irmao(X,Y):- mae(A,X), mae(A,Y),pai(C,X),pai(D,Y).
meio_irmao(X,Y):- mae(A,X), mae(B,Y),pai(C,X),pai(C,Y).

%11
primo_segundo(X,Y):-primo(A,Y),progenitor(A,X).

%12
parentesco(X,Y):- antepassado(A,Y), antepassado(A,X).
parentesco(X,Y):- descendente(A,Y), descendente(A,X).

%13

%estradas

e(lisboa, santarem).
e(santarem, coimbra).
e(santarem, caldas).
e(caldas, lisboa).
e(coimbra, porto).
e(lisboa, evora).
e(evora, beja).
e(lisboa, faro).
e(beja, faro).

a(X, Y) :- e(X, Y).
a(X, Y) :- e(Y, X).

cam(A, B) :- cam(A,B,A).
cam(A, B, X) :- a(A, B), nao_figura(B, X).
cam(A, B, X) :- a(A, C), nao_figura(C, X), cam(C, B, c(C,X)).

nao_figura(N,K) :- \+ figura(N,K).

figura(N, N).
figura(N, c(N,_)).
figura(N, c(_,K)) :- figura(N, K).

%1
/*cam(caldas,beja) true*\

%2


