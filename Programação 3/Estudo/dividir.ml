(*FUNÇÕES AUXILIARES PARA REPRESENTAÇÕES*)

(* Cria uma lista com o mapeamento entre as letras e os números para as colunas*)
let mapping = ['a', 0; 'b', 1; 'c', 2; 'd', 3; 'e', 4; 'f', 5; 'g', 6; 'h', 7]

(* Função para converter uma letra em um número *)
let letter_to_number c =
  (* Procura o par (letra, número) na lista *)
  let rec find lst =
    match lst with
    | [] -> failwith "Invalid character"
    | (c1, n) :: tl -> if c = c1 then n else find tl
  in
  find mapping


(*Inicialização do tabuleiro, inciais em ingles e maiusculas para diferenciar os jogadores*)
let board = Array.make_matrix 8 8 ""
let ()=
board.(0).(0) <- "R";
board.(0).(1) <- "N";
board.(0).(2) <- "B";
board.(0).(3) <- "Q";
board.(0).(4) <- "K";
board.(0).(5) <- "B";
board.(0).(6) <- "N";
board.(0).(7) <- "R";
board.(1).(0) <- "P";
board.(1).(1) <- "P";
board.(1).(2) <- "P";
board.(1).(3) <- "P";
board.(1).(4) <- "P";
board.(1).(5) <- "P";
board.(1).(6) <- "P";
board.(1).(7) <- "P";
for i = 2 to 5 do
  for j = 0 to 7 do
    board.(i).(j) <- "-"
  done
done;
board.(6).(0) <- "p";
board.(6).(1) <- "p";
board.(6).(2) <- "p";
board.(6).(3) <- "p";
board.(6).(4) <- "p";
board.(6).(5) <- "p";
board.(6).(6) <- "p";
board.(6).(7) <- "p";
board.(7).(0) <- "r";
board.(7).(1) <- "n";
board.(7).(2) <- "b";
board.(7).(3) <- "q";
board.(7).(4) <- "k";
board.(7).(5) <- "b";
board.(7).(6) <- "n";
board.(7).(7) <- "r"

(*Função para printar o tabuleiro após cada jogada*)
let print_board ()=
  for i = 0 to 7 do
  for j = 0 to 7 do
    print_string board.(i).(j);
    print_string " ";
  done;
  print_newline()
done

(*VERIFICAÇÃO DE LEGALIDADE DA JOGADA*)

(* Se minuscula->Peão*/Se par->Jogador1, Se impar->Jogador2*)
let process_lowercase s i =
let x = s.[1] in 
  if(x <> 'x') then begin (*Jogada em que não se come peça*)
    let x = Char.code s.[1] - 48 in
    let y = letter_to_number s.[0] in
    if (i mod 2 = 0) then begin
      print_endline (Printf.sprintf "Jogador1: %s" s);
      if board.(x + 1).(y) = "p" then begin
        board.(x).(y) <- "p";
        board.(x + 1).(y) <- "-";
        print_endline "possible"
      end else if board.(x + 1).(y) = "-" && board.(x + 2).(y) = "p" then begin
        board.(x).(y) <- "p";
        board.(x + 2).(y) <- "-";
        print_endline "possible"
      end else
        print_endline "not_possible"
    end else 
        print_endline (Printf.sprintf "Jogador2: %s" s);
      if board.(x - 1).(y) = "P" then begin
        board.(x).(y) <- "P";
        board.(x - 1).(y) <- "-";
        print_endline "possible"
      end else if board.(x - 1).(y) = "-" && board.(x - 2).(y) = "P" then begin
        board.(x).(y) <- "P";
        board.(x - 2).(y) <- "-";
        print_endline "possible"
      end else
        print_endline "not_possible1";
print_board()

  end else (*Jogada em que se come peça*)
    let y1 = letter_to_number s.[0] in
    let x = Char.code s.[3] - 48 in
    let y = letter_to_number s.[2] in
    if i mod 2 = 0 then begin
      print_endline (Printf.sprintf "Jogador1: %s" s);
      if board.(x-1).(y1)="p" then begin
        board.(x).(y) <- "p";
        board.(x - 1).(y1) <- "-";
      end else if board.(x+1).(y1)="p" then begin 
        board.(x).(y) <- "p";
        board.(x + 1).(y1) <- "-";
      end else
        print_endline "not_possible";
    end else
      print_endline (Printf.sprintf "Jogador2: %s" s);
      if board.(x-1).(y1)="P" then begin
        board.(x).(y) <- "P";
        board.(x - 1).(y1) <- "-";
      end else if board.(x+1).(y1)="P" then begin 
        board.(x).(y) <- "P";
        board.(x + 1).(y1) <- "-";
      end else
        print_endline "not_possible";
print_board()


(*Verifica a incial e consoante a inicial identifica a peça e a jogada*)
let process_uppercase s i =
  let p= s.[0] in
  if p='R' then begin (*ROOK-TORRE*)
    let come = s.[1] in
    if(come <> 'x') then (*Jogada em que não se come peça*)
      let x= Char.code s.[2] in
      let y= letter_to_number s.[1] in
      if i mod 2 = 0 then begin
        print_endline (Printf.sprintf "Jogador1: %s" s);
      end else 
        print_endline (Printf.sprintf "Jogador2: %s" s); 
    else  (*Jogada em que não se come peça*)
      print_endline "come"
  end else
    print_endline "outro"

let process_digit s i =
  print_endline "digit";
  print_endline (string_of_int i)

(*INPUTS*)

(* Transformar input em lista *)
let ler_jogadas (arquivo : string) : string list =
  let canal = open_in arquivo in
  let rec loop acc =
    try
      let jogada = input_line canal in
      if jogada = "" then List.rev acc
      else (
        (* Split the line into a list of words. *)
        let words = ref [] in
        let current_word = ref "" in
        for i = 0 to String.length jogada - 1 do
          let c = jogada.[i] in
          if c = ' ' || c = '\t' || c = '\n' || c = '\r' then (
            words := !current_word :: !words;
            current_word := "";
          )
          else current_word := !current_word ^ (String.make 1 c);
        done;
        if !current_word <> "" then words := !current_word :: !words;
        (* Append the words to the accumulator. *)
        loop (!words @ acc)
      )
    with End_of_file -> List.rev acc
  in
  loop []

(*Verifica a primeira letra de cada input e consoante envia para a função correspondente*)
let check_input lst =
  List.mapi (fun i s ->
    let c = String.get s 0 in
    if let code= Char.code c in
    code >= Char.code '0' && code <= Char.code '9' then process_digit s i
    else if Char.lowercase_ascii c = c then process_lowercase s i
    else process_uppercase s i
  ) lst


let jogadas = ler_jogadas ("jogo-1.txt")
let check=check_input jogadas
