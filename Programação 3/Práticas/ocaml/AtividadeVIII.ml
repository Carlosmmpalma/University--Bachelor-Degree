let rec membro lst x=
  match lst with
  | [] -> false
  | h::t -> if h = x then true else membro t x;;

membro [1;2;3] 5;;

let rec conta x lst=
  match lst with 
  |[]->0
  |h::t ->if h=x then 1+conta x t else conta x t;; 

conta 1 [1;2;1];;

let rec append lst1 lst2=
  match lst1 with
  |[]->lst2
  |h::t -> h::append t lst2;;

append [1;2] [3;4];;

let rec soma_lista lst1 =
  match lst1 with 
  |[]->0
  |h::t-> h+soma_lista t;;

soma_lista[1;2;3;4];;

let rec remove x lst1=
  match lst1 with
  |[]->lst1
  |h::t-> if h=x then remove x t else h::remove x t;;

remove 1 [];;

let rec count_occurrences_rec list acc =
  match list with
  | [] -> acc
  | hd :: tl ->
      let count =
        try
          List.assoc hd acc
        with Not_found -> 0
      in
      let acc' = (hd, count + 1) :: List.remove_assoc hd acc in
      count_occurrences_rec tl acc' ;;

count_occurrences_rec [1;2;3;2;3] [];