% Nao, Emacs, nao e' Perl, e' -*- Prolog -*- !!

:- initialization(comando).

comando :- argument_list(As), comando(As).

comando([]).
comando([A|As]) :- argumento(A), frases(F), mostra(F),
		   comando(As).

argumento('-') :- !, format("\n[standard input]\n\n", []).
argumento(F) :- file_exists(F), see(F), !,
		format("\n[file ~w]\n\n", [F]).
argumento(U) :- format("[nao existe: ~w]\n", [U]), !, halt.

mostra([]) :- format("\n[acabou]\n", []).
mostra([F|Fs]) :- mostra_palavras(F), mostra(Fs).

mostra_frase(F) :- format("frase: <<", []), 
		   mostra_palavras(F), 
		   format(">>\n", []).

mostra_palavras([]).
mostra_palavras([P]) :- !, format("~w", [P]).
mostra_palavras([P|Ps]) :- format("~w ", [P]), mostra_palavras(Ps).