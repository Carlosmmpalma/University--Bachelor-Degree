tamanho_tabuleiro(3). %(3x3)

%Problema do quadrado mágico (CSP)
estado_inicial(e([
    v(p(1, 1), [1,2,3,4,5,6,7,8,9], _),
    v(p(1, 2), [1,2,3,4,5,6,7,8,9], _),
    v(p(1, 3), [1,2,3,4,5,6,7,8,9], _),
    v(p(2, 1), [1,2,3,4,5,6,7,8,9], _),
    v(p(2, 2), [1,2,3,4,5,6,7,8,9], _),
    v(p(2, 3), [1,2,3,4,5,6,7,8,9], _),
    v(p(3, 1), [1,2,3,4,5,6,7,8,9], _),
    v(p(3, 2), [1,2,3,4,5,6,7,8,9], _),
    v(p(3, 3), [1,2,3,4,5,6,7,8,9], _)
],[])).

%Restricoes 
ve_restricoes(E):-
 ver_linhas(E),
 ver_colunas(E),
 ver_diagonal_principal(E),
 ver_diagonal_secundaria(E),
 ver_todos(E).

ver_todos(e(_, Vs)):-
  findall(V, member(v(_, _, V), Vs), L),
  todos_diff(L).

%Verifica se a soma dos elementos da linha = constante_magica
ver_linhas(e(_,[])).
ver_linhas(e(_Nafect, [v(p(X, Y), _D, V)|R])):-
  findall(V1, member(v(p(X, _), _, V1), R), L),
  todos_diff([V|L]),
  linha_valida([V|L]). % verifica se os valores na linha são diferentes

%Verifica se a soma dos elementos da coluna = constante_magica
ver_colunas(e(_,[])).
ver_colunas(e(_Nafect,[v(p(_X,Y), _D, V)|R])):-
  findall(V1,member(v(p(_,Y),_,V1),R),L),
  todos_diff([V|L]),
  linha_valida([V|L]).

%Verifica se a soma dos elementos da diagonal principal  =constante_magica
ver_diagonal_principal(e(_, [])).
ver_diagonal_principal(e(_Nafect, [v(p(X, Y), _D, V)|R])) :-
    findall(V1, (member(v(p(X1, Y1), _, V1), R), X1 = Y1), L),
    todos_diff([V|L]),
    linha_valida([V|L]).

%Verifica se a soma dos elementos da diagonal principal  =constante_magica
ver_diagonal_secundaria(e(_, [])).
ver_diagonal_secundaria(e(_Nafect, [v(p(X, Y), _D, V)|R])) :-
    tamanho_tabuleiro(N),
    findall(V1, (member(v(p(X1, Y1), _, V1), R), X1 + Y1 =:= N + 1), L),
    todos_diff([V|L]),
    linha_valida([V|L]).

% verifica se os elementos numa lista sao todos diferentes
todos_diff([]).
todos_diff([X|R]):-
  \+ member(X,R), todos_diff(R).

% Verifica se uma linha do quadrado mágico é válida
linha_valida(L2) :-
    soma(L2, S),
    soma_magica(SM),
    S =< SM.

% Soma os elementos de uma lista
soma([], 0).
soma([X|Xs], S) :-
    soma(Xs, S1),
    S is X + S1.

% Retorna a constante mágica para um tabuleiro de tamanho n
soma_magica(S) :-
    tamanho_tabuleiro(N),
    S is N * (N*N + 1) / 2.

%Função de print
esc(L):-sort(L, L1), esc_a(L1),nl.
esc_a(L):- tamanho_tabuleiro(S), esc_l(L, 1, S).

esc_l([H], S, S):-
  H = v(_,_,X), write(X),nl.

esc_l([H|T], S, S):-
  H = v(_,_,X), write(X), nl,
  esc_l(T, 1, S).

esc_l([H|T], I, S):- 
  I<S, I2 is I+1,
  H = v(_,_,X), write(X),write(' . '),
  esc_l(T, I2, S).

%Função sucessor 
sucessor(e([v(N,D,_)|R],E),e(R,[v(N,D,V)|E])):- member(V,D).

%Backtracking
quadrado_back:- 
    estado_inicial(E0), 
    back(E0,A), 
    esc(A).

%Foward check
forward(e([],A),A).
forward(E,Sol):- 
    sucessor(E,E1),
    ve_restricoes(E1), 
    forCheck(E1, E2),
    forward(E2, Sol).

forCheck(e(Lni,[v(N,D,V)|Li]), e(Lnii, [v(N,D,V)|Li])) :- corta(V,Lni,Lnii).
corta(_,[],[]).
corta(V,[v(N,_D,_)|NAfect],[v(N,_DS,_)|NAfectS]):-  
    corta(V, NAfect, NAfectS).

quadrado_forward:-
  estado_inicial(E0),
  forward(E0,A), 
  esc(A).


%Foward check com backtrack(Melhorar complexidade)
back(e([],A),A).
back(E,Sol):- sucessor(E,E1), 
        ve_restricoes(E1),
        forwardC(E1,E2),
        back(E2,Sol).

forwardC(e(NAfect,[v(N,D,V)|Afect]),e(NAfectS,[v(N,D,V)|Afect])):-
actualizaDom(V, NAfect, NAfectS).

actualizaDom(_,[],[]).
actualizaDom(V,[v(N,D,_)|NAfect],[v(N,DS,_)|NAfectS]):-
delete(D,V,DS),
actualizaDom(V, NAfect, NAfectS).

quadrado_foward_back:-
    estado_inicial(E0), 
    back(E0,A),
    esc(A).


