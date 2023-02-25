pferro(Xs, Ys) :- pferro(Xs, [], Ys).

pferro(V, T, [V|T]) :- var(V), !.
pferro([], T, T) :- !.
pferro([H|TX], T, L) :- !,
pferro(H, F, L),
pferro(TX, T, F).
pferro(NL, T, [NL|T]).

palindrome(Input) :- 
name(Input,CharList),   % Converte o input para uma lista de caracteres 
reverse1(CharList,CharList).  %Verifica se é capicua

reverse1([] , []) .  % the empty list is already reversed.
reverse1([X] , [X]) .  % a list of 1 item is already reversed. This special case is, strictly speaking, optional, as it will be handled by the general case.
reverse1([X|Xs] , R) :- % The general case, a list of length >= 1 , is reversed by
reverse1(Xs,T),        % - reversing its tail, and
append(T, [X], R).  % - appending its head to the now-reversed tail