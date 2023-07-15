homem('Afonso Henriques','rei de Portugal',1109).
homem('Henrique de Borgonha','conde de Portugal',1069).

homem('Sancho I','rei de Portugal',1154).
homem('Fernando II','rei de Leão',1137).
homem('Afonso IX', 'rei de Leão e Castela', 1171).
homem('Afonso II', 'rei de Portugal',1185).

homem('Sancho II', 'rei de Portugal',1207).
homem('Afonso III', 'rei de Portugal',1210).


mulher('Teresa de Castela', 'condessa de Portugal', 1080).
mulher('Mafalda', 'condessa de Saboia', 1125).
mulher('Urraca', 'infanta de Portugal',1151).
mulher('Dulce de Barcelona','infanta de Aragão',1160).
mulher('Berengária', 'infanta de Portugal',1194).
mulher('Urraca C','infanta de Castela',1186).


filho('Afonso Henriques','Henrique de Borgonha').
filho('Afonso Henriques','Teresa de Castela').
filho('Urraca','Afonso Henriques').
filho('Sancho I','Afonso Henriques').
filho('Urraca','Mafalda').
filho('Sancho I','Mafalda').
filho('Afonso IX','Urraca').
filho('Afonso IX','Fernando II').
filho('Afonso II','Sancho I').
filho('Afonso II','Dulce de Barcelona').
filho('Berengária','Sancho I').
filho('Berengária','Dulce de Barcelona').
filho('Sancho II','Afonso II').
filho('Afonso III','Afonso II').
filho('Sancho II','Urraca C').
filho('Afonso III','Urraca C').

%2
irmao(X,Y) :- filho(X,Z), filho(Y,Z), X\=Y.

%3
primoDireito(X,Y) :- filho(X,P1), filho(Y,P2), irmao(P1,P2).

%4
irmaos(X,Y) :- findall(M, irmao(X,M),Y).

%5
primos(A,B) :- findall(M, primoDireito(A,M),B).

%6
esposa(X,Y) :- filho(Z,X), filho(Z,Y), X\=Y.

%7
descende(X,Y) :- findall(M, descende1(X,M),Y).
descende1(X,Y) :- filho(X,Y).
descende1(X,Y) :- filho(A,Y), descende1(X,A).

%8
descendente(X,Y) :- filho(X,Y).
descendente(X,Y) :- filho(X,A), descendente(A,Y).

a([],A,A).		
a([A|R],B,[A|S]):- a(R,B,S).