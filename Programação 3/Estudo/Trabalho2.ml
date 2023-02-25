(*Definição do tipo de peça*)
type piece = Pawn | Knight | Bishop | Rook | Queen | King
(*Definição do jogador*)
type color = White | Black
(*Definição da posição*)
type position = (char * int)
(*Definição da posição do tabuleiro*)
type piece_position = (piece * color) * position
(*Definição de jogada(Peça,jogador,pi e pf)*)
type jogada = piece*(color*position*position)

(*Inicialização do tabuleiro*)
let initial_positions : piece_position list = [
  ((Pawn, White), ('a', 2));
  ((Pawn, White), ('b', 2));
  ((Pawn, White), ('c', 2));
  ((Pawn, White), ('d', 2));
  ((Pawn, White), ('e', 2));
  ((Pawn, White), ('f', 2));
  ((Pawn, White), ('g', 2));
  ((Pawn, White), ('h', 2));
  ((Rook, White), ('a', 1));
  ((Knight, White), ('b', 1));
  ((Bishop, White), ('c', 1));
  ((Queen, White), ('d', 1));
  ((King, White), ('e', 1));
  ((Bishop, White), ('f', 1));
  ((Knight, White), ('g', 1));
  ((Rook, White), ('h', 1));
  ((Pawn, Black), ('a', 7));
  ((Pawn, Black), ('b', 7));
  ((Pawn, Black), ('c', 7));
  ((Pawn, Black), ('d', 7));
  ((Pawn, Black), ('e', 7));
  ((Pawn, Black), ('f', 7));
  ((Pawn, Black), ('g', 7));
  ((Pawn, Black), ('h', 7));
  ((Rook, Black), ('a', 8));
  ((Knight, Black), ('b', 8));
  ((Bishop, Black), ('c', 8));
  ((Queen, Black), ('d', 8));
  ((King, Black), ('e', 8));
  ((Bishop, Black), ('f', 8));
  ((Knight, Black), ('g', 8));
  ((Rook, Black), ('h', 8))
]
let board : piece_position list = initial_positions

(*Função para printar o tabuleiro*)
let print_board (board: piece_position list) : string =
  let row_separator = "+---+---+---+---+---+---+---+---+\n" in
  let print_row (row: int) : string =
    let print_square (col: int) : string =
      let position = (char_of_int (col + 97), row) in
      let piece =
        match List.find_opt (fun (_, pos) -> pos = position) board with
        | Some (p, _) ->
            begin
              match p with
              | (Pawn, White) -> "P"
              | (Pawn, Black) -> "p"
              | (Knight, White) -> "N"
              | (Knight, Black) -> "n"
              | (Bishop, White) -> "B"
              | (Bishop, Black) -> "b"
              | (Rook, White) -> "R"
              | (Rook, Black) -> "r"
              | (Queen, White) -> "Q"
              | (Queen, Black) -> "q"
              | (King, White) -> "K"
              | (King, Black) -> "k"
            end
        | None -> " "
      in
      "| " ^ piece ^ " "
    in
    row_separator ^ "| " ^ (String.concat "" (List.map print_square [0; 1; 2; 3; 4; 5; 6; 7])) ^ "|\n"
  in
  let rows = List.map print_row [8; 7; 6; 5; 4; 3; 2; 1] in
  (String.concat "" rows) ^ row_separator

let result = print_string(print_board board)


  (* Returns true if the pawn at position `pawn_pos` can move to position `dest`,
false otherwise. *)
let can_move_pawn (pawn_pos: position) (dest: position) : bool =
  let col_distance = abs (int_of_char (fst pawn_pos) - int_of_char (fst dest)) in
  let row_distance = abs (snd pawn_pos - snd dest) in 
  (* Pawns can only move one or two squares on their first move. *)
  if snd pawn_pos = 2 then row_distance <= 2 
  else if snd pawn_pos =7 then row_distance <= -2
  (* Otherwise, pawns can only move one square. *)
  else row_distance = 1 || row_distance = -1
  (* Pawns can only move diagonally to capture an enemy piece. *)
                           && col_distance = 1
          
(* Returns the position of the first pawn of color `c` found on the board,
   or None if no such pawn is found. *)
let find_pawn (c: color) (board: piece_position list) : position option =
  List.find_opt (fun ((p, color), _) -> p = Pawn && color = c) board
  |> Option.map snd

(* Find the position of the first white pawn on the board. *)
let white_pawn_pos = find_pawn White board

(* Find the position of the first black pawn on the board. *)
let black_pawn_pos = find_pawn Black board 
    
let white_possible = Option.map (fun pos -> can_move_pawn pos ('a', 7)) white_pawn_pos

(* Check if the black pawn can be moved to e4. *)
let black_possible = Option.map (fun pos -> can_move_pawn pos ('a', 7)) black_pawn_pos
    
(* Returns a new board with the pawn at position `pawn_pos` moved to position `dest`. *)
let move_pawn (pawn_pos: position) (dest: position) (board: piece_position list) : piece_position list =
  let pawn = List.find (fun (_, pos) -> pos = pawn_pos) board in
  let new_board = List.filter (fun (_, pos) -> pos <> pawn_pos) board in
  (* Add the pawn to the destination position. *)
  (fst pawn, dest) :: new_board

(* Move the white pawn to e4 if it is possible. *)
let board =
  match white_possible with
  | Some true -> move_pawn (Option.get white_pawn_pos) ('a', 7) board
  | _ -> board
let result = print_string(print_board board)