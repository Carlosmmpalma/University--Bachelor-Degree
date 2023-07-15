%estado

%(Conteudo_de_c1,Conteudo_de_c2,Conteudo_de_saco)

%estado final
estado_inicial((0,0)).

%estado final
estado_final((1,0)).


capacidade(c1,3).
capacidade(c2,2).

%op(Estado_act,operador,Estado_seg,Custo)
op((A,B),e(c1),(A1,B),1):- capacidade(c1,A1), A \= A1.
op((A,B),e(c2),(A,B1),1):- capacidade(c2,B1),B\=B1.
op((A,B),d(c1),(0,B),1):- A\= 0.
op((A,B),d(c2),(A,0),1):- B\=0.
op((A,B),d(c1,c2),(A2,B3),1):- capacidade(c2,B1), B2 is A+B,
                              min(B3,B1,B2), A3 is B2 - B3,
                              max(A2,A3,0), A\=A2,B\=B3.
op((B,A),d(c2,c1),(B3,A2),1):- capacidade(c1,B1), B2 is A+B, 
                              min(B3,B1,B2), A3 is B2 - B3,
                              max(A2,A3,0), A\=A2,B\=B3.

max(A,A,B):- A>B,!.
max(A,_,A).

min(A,A,B):- A<B,!.
min(A,_,A).

%1.a)
%e([],(0,0))
%e(e(c1),(3,0))
%e(d(c1,c2),(1,2))
%e(d(c2),(1,0))
%e(d(c1,c2),(0,1))

%1.b.i)
%e([],(0,0))
%e(e(c1),(5,0))
%e(d(c1,c2),(3,2))
%e(d(c2),(3,0))
%e(d(c1,c2),(1,2))

%1.b.ii)
%e([],(0,0))
%e(e(c1),(5,0))
%e(d(c1,c2),(3,2))

%1.b.iii)
%e([],(0,0))
%e(e(c2),(0,2))
%e(d(c2,c1),(2,0))
%e(e(c2),(2,2))
%e(d(c2,c1),(4,0))
%e(e(c2),(4,2))
%e(d(c2,c1),(5,1))

%1.b.iv)
%IMPOSSIVEL





