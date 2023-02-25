% Nao, Emacs, nao e' Perl, e' -*- Prolog -*- !!


% -- predicado: frases/1

frases(F) :- engole(S), frases(F, S, _R), !. % _R=resto (depois da frase)
frases(_) :- true.		% haveria de dar erro... :-)

% -- predicado: engole/1: engole stdin para um string

engole(S) :- get0(C), engole(C, S).

  engole(-1, []) :- !, seen.
  engole(C, [C|S]) :- engole(S).


% -- predicado: frases/3 (nao-terminal frases/1)

frases([F|FS]) --> frase(F), frases(FS), !.
frases([F]) --> frase(F).

frase(PS) --> palavras(PS), ponto.

espaco --> " ".
espaco --> "\t".
espaco --> "\n".

virgula --> ",".

ponto --> ".", espacos.

espacos --> espaco, espacos.
espacos --> [].

letra(L) --> minuscula(L).
letra(L) --> maiuscula(L). 
letra(L) --> numero(L).

maiuscula(M) --> [L], { 0'A =< L, L =< 0'Z, !, M is L+0'a-0'A }.
minuscula(L) --> [L], { 0'a =< L, L =< 0'z }.

letras([L|LS]) --> letra(L), letras(LS).
letras([L]) --> letra(L), !.


palavra(P) --> letras(LS), { name(P, LS) }.

palavras([P|PS]) --> espacos, palavra(P), virgula, !, palavras(PS).
palavras([P|PS]) --> espacos, palavra(P), espacos, palavras(PS).
palavras([P|PS]) --> espacos, palavra(P), !, palavras(PS).
palavras([]) --> [].
