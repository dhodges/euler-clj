#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; http://projecteuler.net/index.php?section=problems&id=24
;;
;; Problem 24
;; 16 August 2002
;;
;; A permutation is an ordered arrangement of objects. For example, 
;; 3124 is one possible permutation of the digits 1, 2, 3 and 4. 
;; 
;; If all of the permutations are listed numerically or alphabetically, 
;; we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
;;
;; 012   021   102   120   201   210
;;
;; What is the millionth lexicographic permutation of the digits 
;; 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

(defn fac
  [n]
  (if (= 1 n)
    1
    (* n (fac (dec n)))))
