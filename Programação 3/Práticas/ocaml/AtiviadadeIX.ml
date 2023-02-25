type 'a abp =
  | Empty
  | Node of 'a * 'a abp * 'a abp

let example : int abp = Node (5, Node (3, Empty, Empty), Node (7, Empty, Empty))
    
let rec lookup tree element =
  match tree with
  | Empty -> false
  | Node (el, left, right) ->
      if element = el then true
      else if element < el then lookup left element
      else lookup right element;;

let tree : int abp = Node (5, Node (3, Empty, Empty), Node (7, Empty, Empty));;

let r1= lookup tree 3;;
let r2= lookup tree 4;;

let rec insert tree element =
  match tree with
  | Empty -> Node (element, Empty, Empty)
  | Node (el, left, right) ->
      if element = el then tree
      else if element < el then Node (el, insert left element, right)
      else Node (el, left, insert right element);;

let rec walk tree f =
  match tree with
  | Empty -> ()
  | Node (el, left, right) ->
      walk left f;
      f el;
      walk right f;;

let tree : int abp = Node (5, Node (3, Empty, Empty), Node (7, Empty, Empty))

let print_element x = print_int x; print_newline ()

let () = walk tree print_element