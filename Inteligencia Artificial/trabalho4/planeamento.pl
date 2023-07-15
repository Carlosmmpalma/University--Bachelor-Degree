%Condições
pos(X).

%Fluentes
esta-pos(Bloc,Pos).
pos-livre(Pos).
na-mão(Bloc).
mão-livre.

%Ações
mover(X, Y).
agarrar(Bloc,X).
largar(Bloc,X).

%Estados
estado_inicial([pos(0), mão-livre, esta-pos(A,0),esta-pos(B,1),esta-pos(C,2), pos-livre(3)]).
estado_final([pos(0), mão-livre, pos-livre(0), esta-pos(C,1), esta-pos(A,2), esta-pos(B,3)]).

%Solução mover(0,1),agarrar(B,1),mover(1,3),largar(B,3),mover(3,2),agarrar(C,2),mover(2,1),largar(C,1),mover(1,0),agarrar(A,0),mover(0,2),largar(A,2).