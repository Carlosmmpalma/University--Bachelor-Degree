%estado inicial (posição do cavalo)
estado_inicial((1,1)).

%estado final
estado_final((8,8)).
	

%op(Estado_act,operador,Estado_seg,Custo)
  op((X,Y), (N,M), (X1,Y1), 1):- member(N,[1,-1]),
  member(M,[2,-2]),
  X1 is X+N, Y1 is Y+M, lim(X1,Y1).

 op((X,Y), (N,M), (X1,Y1), 1):- member(M,[1,-1]),
			member(N,[2,-2]), 
			X1 is X+N, Y1 is Y+M, lim(X1,Y1).

lim(A,B) :- A =< 8, A >= 1, B =< 8, B >= 1.

%heuristica para estimar distancia h(Estado,Valor)

h((X,Y),Val):- estado_final((Xf,Yf)), mod(Vi, Xf, X), mod(Vj, Yf, Y), Val is (Vi+Vj) div 3.

%h((X,Y),Val):- estado_final((Xf,Yf)), mod(Vi, Xf,X), mod(Vj, Yf,Y),  max(V,Vi,Vj), Val is V div 2.

%h((X,Y),0).
  
mod(Vj,X,Y) :- X<Y,!, Vj is Y-X.
mod(Vj,X,Y) :- Vj is X-Y.

   max(Vi,Vi,Vj):-Vi>Vj,!.
  max(Vj,_,Vj).
