%estado inicial (posição do branco, lista de listas com linhas colunas)
%estado_inicial(((2,1),[[1,b,3],[4,2,5],[6,7,8]])).
%estado_inicial(((1,1),[[b,1,3],[4,2,5],[6,7,8]])).
%estado_inicial(((1,2),[[4,1,3],[b,2,5],[6,7,8]])).

estado_inicial(((2,2),[[4,1,3],[2,b,5],[6,7,8]])).

%estado_inicial(((2,2),[[4,1,3],[5,b,8],[2,6,7]])).

%estado final
estado_final(((2,2),[[1,2,3],[4,b,5],[6,7,8]])).

%op(Estado_act,operador,Estado_seg,Custo)

op(((X,Y),L),sobe,((X,Z),L1),1):- Y<3, Z is Y+1, substitui(V,b,(X,Z),L,L0),
                                substitui(b,V,(X,Y),L0,L1).  
op(((X,Y),L),desce,((X,Z),L1),1):- Y>1, Z is Y-1, substitui(V,b,(X,Z),L,L0),
                                substitui(b,V,(X,Y),L0,L1).  
op(((X,Y),L),esq,((Z,Y),L1),1):-  X>1, Z is X-1, substitui(V,b,(Z,Y),L,L0),
                                substitui(b,V,(X,Y),L0,L1).  
op(((X,Y),L),dir,((Z,Y),L1),1):-  X<3, Z is X+1, substitui(V,b,(Z,Y),L,L0),
                                substitui(b,V,(X,Y),L0,L1).  

substitui(Vi,Vf,(Y,X),Li,Lf):- retira(X,Fi,Ff,Li,Lf), retira(Y,Vi,Vf,Fi,Ff).

retira(1,X,Y,[X|R],[Y|R]).
retira(N,X,Y,[A|R],[A|S]):- M is N-1, retira(M,X,Y,R,S).




dist(X,Y,K):- X>Y,!, K is X -Y.
dist(X,Y,K):-  K is Y - X.


