%Verifica se é lista
lista([]). % caso base
lista([_|L]) :- lista(L). % caso recursivo

%Comprimento de uma lista
compr([], 0).
compr([_|T], X) :- compr(T, Y), X is Y+1.

%Remove o elemento E da lista [E|L] e retorna  a lista L
sel(E, [E|L], L).
sel(E, [X|L], [X|M]) :- sel(E, L, M).

%Inverter lista
rev(L, R) :- rev(L, [], R).

rev([], R, R).
rev([A|B], X, R) :- rev(B, [A|X], R).

%Total da soma dos elementos de uma lista
somalista([], 0).
somalista([I|Is], S) :-somalista(Is, S0),S is I+S0.

%Outra forma de inverter lista
perm([], []).
perm(L, [X|LP]) :-sel(X, L, LX), perm(LX, LP).

%Verifica se lista está ordenada
ord([]).
ord([_]).
ord([A,B|X]) :- A<B, ord([B|X]).

%Ordenar lista
psort(L, S) :- perm(L, S), ord(S).

%Ordenação por inserção
isort(I, S) :- isort(I, [], S).

isort([], S, S).
isort([X|Xs], SI, SO) :-insord(X, SI, SX),isort(Xs, SX, SO).

insord(X, [], [X]).
insord(X, [A|As], [X,A|As]) :- X=<A.
insord(X, [A|As], [A|AAs]) :- X>A,
insord(X, As, AAs).

%QuickSort
qsort(L, S) :- qsort(L, [], S).
qsort([], L, L).
qsort([X|Xs], L0, L) :-part(Xs, X, MEN, MAI),qsort(MAI, L0, L1),qsort(MEN, [X|L1], L).
part([], _, [], []).
part([X|L], Y, [X|L1], L2) :-X =< Y, !, part(L, Y, L1, L2).
part([X|L], Y, L1, [X|L2]) :-part(L, Y, L1, L2).



  