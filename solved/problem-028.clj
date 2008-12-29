#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [clojure.contrib.test-is]))

;; http://projecteuler.net/index.php?section=problems&id=28
;;
;; Problem 28
;; 11 October 2002
;;
;; Starting with the number 1 and moving to the right in a clockwise direction 
;; a 5 by 5 spiral is formed as follows:
;;
;; 21 22 23 24 25
;; 20  7  8  9 10
;; 19  6  1  2 11
;; 18  5  4  3 12
;; 17 16 15 14 13
;;
;; It can be verified that the sum of both diagonals is 101.
;;
;; What is the sum of both diagonals in a 1001 by 1001 spiral formed in the same way?

;
; Notes:
;
; 73 74 75 76 77 78 79 80 81
; 72 43 44 45 46 47 48 49 50
; 71 42 21 22 23 24 25 26 51
; 70 41 20  7  8  9 10 27 52
; 69 40 19  6  1  2 11 28 53
; 68 39 18  5  4  3 12 29 54
; 67 38 17 16 15 14 13 30 55
; 66 37 36 35 34 33 32 31 56
; 65 64 63 62 61 60 59 58 57
;
; Starting from the centre (i.e, "1")
; and heading out along the four diagonals,
; the numbers follow four distinct patterns:
;
;     north-east: n*n
;     north-west: n*(n-1)+1
;     south-west: n*(n-2)+2
;     south-east: n*(n-3)+3

(defn north-east [n] (* n n))
(defn north-west [n] (+ 1 (* n (- n 1))))
(defn south-west [n] (+ 2 (* n (- n 2))))
(defn south-east [n] (+ 3 (* n (- n 3))))

(defn euler-028
  [n]
  (time
   (reduce + 1 (for [x (range n 1 -2)]
                 (+ (north-east x)
                    (north-west x)
                    (south-west x)
                    (south-east x))))))


(deftest test-euler-028
  []
  (is (= (solution 1001) 669171001)))


