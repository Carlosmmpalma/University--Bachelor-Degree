%1
fd_domain([T,W,O,F,U,R],0,7), 
T#>0, F#>0, 
fd_all_different([T,W,O,F,U,R]), 
O+10*(W+10*T) + O+10*(W+10*T) #= R+10*(U+10*(O+10*F)), 
fd_labeling([T,W,O,F,U,R]).

%2

%3.1
length(R, N),
fd_domain(R, 1, N).

ok([]).
ok([R|Rs]) :- ok(Rs, R, 1), ok(Rs).

ok([], _, _).
ok([Rj|Rs], Ri, I) :-
I1 is I+1,
ok(Rs, Ri, I1),
Ri #\= Rj, Ri #\= Rj+I, Ri+I #\= Rj.

rainhas(N, R) :-
length(R, N),
fd_domain(R, 1, N),
ok(R),
fd_labeling(R).