estado_inicial(e([x,o,x,v,v,o,x,v,o],x)).

  inv(x,o).
  inv(o,x).
  op1(e(L,J),(C),e(L1,J1)):- inv(J,J1), subs(v,J,L,L1,1,C).

  subs(A,J, [A|R], [J|R],C,C).
  subs(A,J, [B|R], [B|S],N,C):- M is N+1, subs(A,J,R,S,M,C).

  terminal(e([O,O,O,_,_,_,_,_,_],_)):- O==x; O == o.
  terminal(e([_,_,_,O,O,O,_,_,_],_)):- O==x; O == o.
  terminal(e([_,_,_,_,_,_,O,O,O],_)):- O==x; O == o.

  terminal(e([O,_,_,O,_,_,O,_,_],_)):- O==x; O == o.
  terminal(e([_,O,_,_,O,_,_,O,_],_)):- O==x; O == o.
  terminal(e([_,_,O,_,_,O,_,_,O],_)):- O==x; O == o.

  terminal(e([O,_,_,_,O,_,_,_,O],_)):- O==x; O == o.
  terminal(e([_,_,O,_,O,_,O,_,_],_)):- O==x; O == o.

  terminal(e(L,_)):- \+ member(v,L).
  valor(e(L,_),0,P):-  \+ member(v,L),!.

  
  valor(E,V,P):-terminal(E),
  X is P mod 2,
   (X== 1,V=1;X==0,V= -1). 
  
