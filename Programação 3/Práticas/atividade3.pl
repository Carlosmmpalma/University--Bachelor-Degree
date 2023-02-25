%a
membro(X, [X|_]).
membro(X,[_|L]) :-membro(X,L). 

%b
prefixo([],_).
prefixo([X|A],[X|B]):- prefixo(A,B).

%c
sufixo(A,A).
sufixo(A,[_|B]):- sufixo(A,B).