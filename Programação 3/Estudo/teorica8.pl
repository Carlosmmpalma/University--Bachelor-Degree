%Mostra uma lista com todos os resultados de uma recursiva
findall(PAT, GEN, SET)
bagof(PAT, GEN, SET)
setof(PAT, GEN, SET) %dulicados são removidos e a lista é ordenada

%Colecionar numa bd as soluções de um goal  
collect(G, GG) :-retractall(gg(_)),collect1(G, GG).

collect1(G, _) :- G, assertz(gg(G)), fail.
collect1(_, GG) :- collect2([], GG).

collect2(L, GG) :- retract(gg(G)), !, collect2([G|L], GG).
collect2(GG, GG).