%1)
fdic([]).
fdic([_=_|D]) :- fdic(D).

flookup([K=V|_], K, V). %Primeiro->Nome do dicionário,Segundo->KEY,Terceiro->Variável para guardar valor da key
flookup([K1=_|_], K, _):-K1@<K,!,fail.
flookup([_|D], K, V) :- flookup(D, K, V).

finsert([], K, V, [K=V]). %Primeiro->novo dicionário(lista),Segundo->chave,Terceiro->valor,Quarto->Dicionário onde vai ser colocada  a lista
finsert([K1=V1|T], K, V, [K=V,K1=V1|T]) :- K@<K1,!. 
finsert([KV|DI], K, V, [KV|DO]) :- finsert(DI, K, V, DO).

fremove([], _, []). %Primeiro->Dicionário já existente,Segundo->key que queremos remover,Terceiro->novo dicionário para guardar o anterior alterado
fremove([K=_|D], K, D) :- !.
fremove([KV|DI], K, [KV|DO]) :- fremove(DI, K, DO).

/*2-ALTERAR VALOR ASSOCIADO A CHAVE
finsert([], K, V, [K=V]). 
finsert([K=_|T], K, V, [K=V|T]) :- !. 
finsert([KV|DI], K, V, [KV|DO]) :- finsert(DI, K, V, DO).*/

/*3-ORDENAR OS VALORES POR ORDEM DE KEYS
flookup([K=V|_], K, V). 
flookup([K1=_|_], K, _):-K1@<K,!,fail.
flookup([_|D], K, V) :- flookup(D, K, V).

finsert([], K, V, [K=V]). 
finsert([K1=V1|T], K, V, [K=V,K1=V1|T]) :- K@<K1,!. 
finsert([KV|DI], K, V, [KV|DO]) :- finsert(DI, K, V, DO). */

%4
% abp(A) se A for binaria, dois casos: no(XXX, L, R) e nil
abp(nil).
abp(no(_, L, R)) :- abp(L), abp(R).

% alookup(ABP, K, V) se (K,V) for membro de ABP
alookup(no(K=V, _, _), K, V).
alookup(no(X=_, L, _), K, V) :- K @< X, !, alookup(L, K, V).
alookup(no(X=_, _, R), K, V) :- K @> X, !, alookup(R, K, V).

% ainsert(IN, K, V, OUT)
ainsert(nil, K, V, no(K=V, nil, nil)).
ainsert(no(K=_, L, R), K, V, no(K=V, L, R)).
ainsert(no(X=VX, L, R), K, V, no(X=VX, LL, R)) :- K@<X, ainsert(L, K, V, LL).
ainsert(no(X=VX, L, R), K, V, no(X=VX, L, RR)) :- K@>X, ainsert(R, K, V, RR).
