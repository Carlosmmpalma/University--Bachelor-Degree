%Inicialização das peças
initial(piece(white, rook,   1, 1)).
initial(piece(white, knight, 2, 1)).
initial(piece(white, bishop, 3, 1)).
initial(piece(white, queen,  4, 1)).
initial(piece(white, king,   5, 1)).
initial(piece(white, bishop, 6, 1)).
initial(piece(white, knight, 7, 1)).
initial(piece(white, rook,   8, 1)).
initial(piece(white, pawn,   X, 2)) :- between(1, 8, X).

initial(piece(black, rook,   1, 8)).
initial(piece(black, knight, 2, 8)).
initial(piece(black, bishop, 3, 8)).
initial(piece(black, queen,  4, 8)).
initial(piece(black, king,   5, 8)).
initial(piece(black, bishop, 6, 8)).
initial(piece(black, knight, 7, 8)).
initial(piece(black, rook,   8, 8)).
initial(piece(black, pawn,   X, 7)) :- between(1, 8, X).

%Transformar tabuleiro numa lista
initial_board(Board) :- findall(Piece, initial(Piece), Board).

%Descreve os oponentes
opponent(white, black).
opponent(black, white).

%Descrição do tabuleiro
vacant(X, Y, Board) :-
  between(1, 8, X),
  between(1, 8, Y),
  \+(member(piece(_, _, X, Y), Board)).

%Captura de peças
capture(piece(Color, Which, X, Y), Motion, End) :-
  opponent(Color, Other),
  select(piece(Other, _, X, Y),     Motion, Motion2),
  End = [piece(Color, Which, X, Y) | Motion2].

%Mover a peça no tabuleiro
place(Piece, Motion, End) :-
  End = [Piece | Motion].

%Movimentos do rei
possible_move(Board, Color, End) :-
  select(piece(Color, knight, X, Y), Board, Motion),
  (
    N_X is X + 1, N_Y is Y + 2;
    N_X is X - 1, N_Y is Y + 2;
    N_X is X + 1, N_Y is Y - 2;
    N_X is X - 1, N_Y is Y - 2;
    N_X is X + 2, N_Y is Y + 1;
    N_X is X - 2, N_Y is Y + 1;
    N_X is X + 2, N_Y is Y - 1;
    N_X is X - 2, N_Y is Y - 1
  ),
  (
    vacant(N_X, N_Y, Board),
    place(piece(Color, knight, N_X, N_Y), Motion, End)
    ;
    capture(piece(Color, knight, N_X, N_Y), Motion, End)
  ).

  %Movimentos do peão
possible_move(Board, Color, End) :-
  select(piece(Color, pawn, X, Y), Board, Motion),
  (
    (Color == white, N_Y is Y + 1) ;
    (Color == black, N_Y is Y - 1)
  ),
  (
    vacant(X, N_Y, Board),
    place(piece(Color, pawn, X, N_Y), Motion, End)
    ;
    (
      (Color == white, N_X is X + 1) ;
      (Color == black, N_X is X - 1)
    ),
    opponent(Color, Other),
    member(piece(Other, _, N_X, N_Y), Board),
    capture(piece(Color, pawn, X, N_Y), Motion, End)
  ).

%Printar o tabuleiro
draw_board(Board) :-
  nl, draw_row(1, 1, Board), nl.

draw_row(X, Y, Board) :-
  X =< 8,
  draw_piece(X, Y, Board),
  X1 is X+1,
  draw_row(X1, Y, Board).
draw_row(9, Y, Board) :-
  Y =< 7,
  nl,
  Y1 is Y+1,
  draw_row(1, Y1, Board).
draw_row(9, 8, _) :-
  nl.

draw_piece(X, Y, Board) :-
  X == 1,
  write('    .'),
  draw_piece_on_board(X, Y, Board),
  write('.').

draw_piece(X, Y, Board) :-
  X > 1,
  draw_piece_on_board(X, Y, Board),
  write('.').

draw_piece_on_board(X, Y, Board) :-
  ( member(piece(_, Which, X, Y), Board) ->
    letter(Which, Letter),
    write(Letter)
    ;
    write('_')
  ).

letter(bishop, 'b').
letter(king,   'k').
letter(knight, 'n').
letter(pawn,   'p').
letter(queen,  'q').
letter(rook,   'r').

find_and_draw_moves :-
  initial_board(Board),
  possible_move(Board, white, piece(white,pawn,3,3)),
  draw_board(Board).
