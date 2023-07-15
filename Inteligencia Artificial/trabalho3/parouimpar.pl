estado_inicial(0, 0).

% Definindo os estados terminais. Par é 1, ímpar é -1.
terminal(P, 1) :- P mod 2 =:= 0.
terminal(P, -1) :- P mod 2 =\= 0.

% Função de utilidade. Retorna o valor dos estados terminais.
valor(P, V, _) :- terminal(P, V).

% Definindo as jogadas. O jogador escolhe par ou ímpar.
op1(P, par, R) :- X is P mod 2, X =:= 0, R is P+1.
op1(P, par, R) :- X is P mod 2, X =\= 0, R is P-1.
op1(P, impar, R) :- X is P mod 2, X =:= 0, R is P-1.
op1(P, impar, R) :- X is P mod 2, X =\= 0, R is P+1.
