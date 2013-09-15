;; 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
;;
;; Find the sum of all numbers which are equal to the sum of
;; the factorial of their digits.
;;
;; Note: as 1! = 1 and 2! = 2 are not sums they are not included.
;;
;; http://projecteuler.net/problem=34

(ns dh.euler.solutions.problem_034
  (:use [dh.euler.util.core :refer [factorial]]))


; Notes
; Factorions
; see: http://en.wikipedia.org/wiki/Factorion


(def *upper-bound* (* (factorial 9) 7))


(defn facsum
  "sum of the factorial of each digit in n"
  [n]
  (apply + (for [c (str n)]
             (factorial (Integer/parseInt (str c))))))


(defn euler-034
  []
  (apply + (for [n (range 3 (inc *upper-bound*))
                  :when (= n (facsum n))]
             n)))
