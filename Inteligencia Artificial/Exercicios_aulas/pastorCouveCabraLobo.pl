%estado(([p,cv,cb,l]
estado_inicial(e([p,cv,cb,l],[])).
%estado_inicial(e([l],[p,cv,cb])).
estado_final(e([],_)).

	 op(e(E,D),dir(O),e(E1,[p,O|D]),1):- member(p,E),
	 retira(p,O,E,E1),certo(E1).


	 
	 op(e(E,D),esq(O),e([p,O|E],D1),1):- member(p,D),
	 retira(p,O,D,D1), certo(D1).

	 
	 op(e(E,D),esq,e([p|E],D1),1):- member(p,D),
	 retira(p,D,D1), certo(D1).

certo(L):- \+ come(L).
come(L):- \+ member(p,L), member(l,L), member(cb,L).	 
come(L):- \+ member(p,L), member(cb,L), member(cv,L).	 


	 retira(p,O,L,L1):- retira(p,L,L11),retira(O,L11,L1).

	 retira(A,[A|L],L).
	 retira(A,[B|L],[B|L1]):- retira(A,L,L1).


h(e(E,D),N):- length(D,M), N is 4 - M.

	 % e([p,cv,cb,l],[])
	 %e([cb,l],[p,cv])
	 %e([p, cv,l],[cb])
      	 %e([l],[p,cv,cb])
	 %e([p,cb,l],[cv])
	 %e([cb],[p,l,cv])
	 %e([p,cb],[l,cv])
	 %e([],[p,cb,l,cv])
