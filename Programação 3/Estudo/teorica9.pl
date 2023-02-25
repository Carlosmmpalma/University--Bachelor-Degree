%Rainhas
ok([]).
ok([R|Rs]) :- ok(Rs, R, 1), ok(Rs).

ok([], _, _).
ok([Rj|Rs], Ri, I) :- I1 is I+1, Ri #\= Rj, Ri #\= Rj+I, Ri+I #\= Rj,
ok(Rs, Ri, I1).

rainhas(N, R) :-length(R, N), fd_domain(R, 1, N), ok(R), fd_labeling(R).

%SEND+MORE=MONEY
X=[S,E,N,D,M,O,R,Y],
fd_domain(X,0,9), S#>0,M#>0,
fd_all_different(X),
D+10*(N+10*(E+10*S))+ E+10*(R+10*(O+10*M)) #=
Y+10*(E+10*(N+10*(O+10*M))),
fd_labeling(X).

ok([]).
ok([Rs|R]):- ok(R,Rs,1), ok(R).

ok([],_,_).
ok([Rj|R], Ri,I):- I1 is I+1, Ri#\=Rj, Ri#\=Rj+I, Rj\=Ri+I, ok(R,Ri,I1).

rainhas(N,R):- length(R,N),fd_domain(R,1,N),ok(R), fd_labeling(R).

