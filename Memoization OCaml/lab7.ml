let c n k = 
  let rec cing n k =
    if (k == 0) 
      then 
        1
  else 
    if (k != 0 && n == 0)
      then 
        0
else
  cing (n-1) k + cing (n-1) (k-1)
    in cing n k ;;

let memyC n k =
  let hsh = Hashtbl.create ~random: false 1000 in 
  let rec memyCing n k =
    match Hashtbl.find_opt hsh (n,k) with 
    Some v -> v
    | None ->
      match (n,k) with
      |(a,b) when (b ==0) ->
        Hashtbl.add hsh (n,k) 1;
        1
      |(a,b) when (a==0 && b!=0) ->
        Hashtbl.add hsh (n,k) 0;
        0
      | (a,b) ->
        Hashtbl.add hsh (n,k) (memyCing (a-1) b + memyCing (a-1) (b-1));
        memyCing (a-1) b + memyCing (a-1) (b-1)
  in memyCing n k ;;

  (*# #use "lab7.ml" ;;
val c : int -> int -> int = <fun>
val memyC : int -> int -> int = <fun>
# #use "tests7.ml" ;;
val time : string -> (unit -> 'a) -> 'a = <fun>
c test1 0.000003 seconds
- : int = 1
c test2 0.000002 seconds
- : int = 0
c test3 0.000002 seconds
- : int = 1
c test4 0.000003 seconds
- : int = 1
c test5 0.000022 seconds
- : int = 70
c test6 0.000005 seconds
- : int = 10
c test7 30.937100 seconds
- : int = 847660528
memyC test1 0.000006 seconds
- : int = 1
memyC test2 0.000005 seconds
- : int = 0
memyC test3 0.000005 seconds
- : int = 1
memyC test4 0.000006 seconds
- : int = 1
memyC test5 0.000018 seconds
- : int = 70
memyC test6 0.000010 seconds
- : int = 10
memyC test7 0.000176 seconds
- : int = 847660528
*)

