%Dicionário funcional

%Verifica se é um dicionário funcional (chave e valor)
fdic([]).
fdic([_=_|D]) :- fdic(D).

%Verifica se existe a chave
flookup([K=V|_], K, V).
flookup([_|D], K, V) :- flookup(D, K, V).

%Insere key e valor no dicionário 
finsert([], K, V, [K=V]).
finsert([K=_|_], K, _, _) :- !, fail.
finsert([KV|DI], K, V, [KV|DO]) :- finsert(DI, K, V, DO).

%Remove key  do dionário
fremove([], _, []).
fremove([K=_|D], K, D) :- !.
fremove([KV|DI], K, [KV|DO]) :- fremove(DI, K, DO).

%Dicionário lógico

%Verifica se é dicionário lógico
ldic(D) :- var(D), !.
ldic([_=_|D]) :- ldic(D).

%Verifica se a chave existe no dicionário
llookup(D, _, _) :- var(D), !, fail.
llookup([K=V|_], K, V).
llookup([_|D], K, V) :- llookup(D, K, V).

%Insere chave e valor no dicionário
linsert(D, K, V) :- var(D), !, D=[K=V|_].
linsert([K=_|_], K, _) :- !, fail.
linsert([_KV|D], K, V) :- linsert(D, K, V).

%Subtermo com functor
subterm(T, T).
subterm(ST, T) :-compound(T),functor(T, _, N),subterm(N, ST, T).

%Subtermo com arg
subterm(N, ST, T) :-N > 1, N1 is N-1,
subterm(N1, ST, T).
subterm(N, ST, T) :-arg(N, T, A),subterm(ST, A).

%Arvore binária de pesquisa

% abp(A) se A for binaria, dois casos: no(XXX, L, R) e nil
abp(nil).
abp(no(_, L, R)) :- abp(L), abp(R).

% alookup(ABP, K, V) se (K,V) for membro de ABP
alookup(no(K=V, _, _), K, V).
alookup(no(X=_, L, _), K, V) :- K @< X, !, alookup(L, K, V).
alookup(no(X=_, _, R), K, V) :- K @> X, !, alookup(R, K, V).

% ainsert(IN, K, V, OUT)
ainsert(nil, K, V, no(K=V, nil, nil)).
ainsert(no(X=VX, L, R), K, V, no(X=VX, LL, R)) :-
K@<X, ainsert(L, K, V, LL).
ainsert(no(X=VX, L, R), K, V, no(X=VX, L, RR)) :-
K@>X, ainsert(R, K, V, RR).