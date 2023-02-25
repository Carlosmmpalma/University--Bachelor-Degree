let idade1=21;;
let idade2=20;;

if(idade1>idade2) then "idade1" else "idade2";;

let soma1 = function x->x+1;;
let divide= function x-> 100/x;;

let pi=3.14;;
let d_c= function x->(2.0*.x)/.pi;;

d_c 25.0;;

let f= function x-> x::[];;
f 1;;

let par= function x-> x mod 2 = 0;;
let impar x = not (par x);;
par 15;;
impar 15;;
impar;;

let div_3 = function x -> x mod 3 = 0;;
div_3 4;;

let div= function n1 -> function n2 -> n1/.n2;;
div 5. 3.;;

let div2 x y= (x mod y);;
div2 4 2;;

let mult= function n1->function n2-> n1*.n2;;
mult 10. 5.;;

let rec fact n= if(n=1) then 1 else n*fact(n-1);;
fact 4;;

fst(1,2);;
snd(1,2);;

let (l,h,p)=(1,2,3);;

let nulo = function
  |0 -> "nulo"
  |_ -> "n nulo";;
nulo 1;;

let texto = function
  |0 -> "zero"
  |1 -> "um"
  |_ ->"outro";;
texto 0;;

match "day" with "day"->"dia" | "night"->"noite" | _->"outro";;

let rec fact2 = function
    0->1
  |n->n * fact2 (n-1);;

fact2 5;;

let rec length = 
  function []->0 
         | _::x -> 1+length x;;

length [4;2;3];;

let rec mem = function x-> function
    []-> false
  | h::t -> if x=h then true else mem x t;;
mem 1 [1;2;3];;
