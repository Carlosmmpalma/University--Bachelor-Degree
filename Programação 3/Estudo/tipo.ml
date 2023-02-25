type piece =
  | Pawn
  | Knight
  | Bishop
  | Rook
  | Queen
  | King

type move =
  | Forward of int
  | ForwardRight of int
  | Right of int
  | BackwardRight of int
  | Backward of int
  | BackwardLeft of int
  | Left of int
  | ForwardLeft of int

let parse_move (input: string) : piece * move =
  let first_char = input.[0] in
  let second_char = input.[1] in
  let third_char = input.[2] in
  let fourth_char = input.[3] in
  let fifth_char = input.[4] in
  let sixth_char = input.[5] in
  let piece =
    match first_char with
    | 'P' -> Pawn
    | 'N' -> Knight
    | 'B' -> Bishop
    | 'R' -> Rook
    | 'Q' -> Queen
    | 'K' -> King
    | _ -> failwith "Invalid piece"
  in
  let col1 =
    match second_char with
    | 'a' -> 0
    | 'b' -> 1
    | 'c' -> 2
    | 'd' -> 3
    | 'e' -> 4
    | 'f' -> 5
    | 'g' -> 6
    | 'h' -> 7
    | _ -> failwith "Invalid column"
  in
  let row1 = int_of_char third_char - int_of_char '0' - 1 in
  let col2 =
    match fifth_char with
    | 'a' -> 0
    | 'b' -> 1
    | 'c' -> 2
    | 'd' -> 3
    | 'e' -> 4
    | 'f' -> 5
    | 'g' -> 6
    | 'h' -> 7
    | _ -> failwith "Invalid column"
  in
  let row2 = int_of_char sixth_char - int_of_char '0' - 1 in
  let dx = col2 - col1 in
  let dy = row2 - row1 in
  let move =
    match dx, dy with
    | 0, y when y > 0 -> Forward y
    | x, y when x > 0 && y > 0 -> ForwardRight (x + y)
    | x, 0 when x > 0 -> Right x
    | x, y when x > 0 && y < 0 -> BackwardRight (x - y)
    | 0, y when y < 0 -> Backward (-y)
    | x, y when x < 0 && y < 0 -> BackwardLeft (-x - y)
    | x, 0 when x < 0 -> Left (-x)
    | x, y when x < 0 && y > 0 -> ForwardLeft (-x + y)
    | _ -> failwith "Invalid move"
  in 
  (piece,move)

  