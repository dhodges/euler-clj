#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler)

;; http://projecteuler.net/index.php?section=problems&id=39
;;
;; Problem 39
;; 14 March 2003
;;
;; If p is the perimeter of a right angle triangle with
;; integral length sides, {a,b,c}, there are exactly three solutions
;; for p = 120.
;;
;; {20,48,52}, {24,45,51}, {30,40,50}
;;
;; For which value of p ≤ 1000, is the number of solutions maximised?

