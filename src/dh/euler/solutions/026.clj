(ns dh.euler.solutions.026
  (:use [dh.euler.util.core :refer :all]))

;; http://projecteuler.net/index.php?section=problems&id=26
;;
;; Problem 26
;; 13 September 2002
;;
;; A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions
;; with denominators 2 to 10 are given:
;;
;;     1/2	= 	0.5
;;     1/3	= 	0.(3)
;;     1/4	= 	0.25
;;     1/5	= 	0.2
;;     1/6	= 	0.1(6)
;;     1/7	= 	0.(142857)
;;     1/8	= 	0.125
;;     1/9	= 	0.(1)
;;     1/10	= 	0.1
;;
;; Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that
;; 1/7 has a 6-digit recurring cycle.
;;
;; Find the value of d < 1000 for which 1/d contains the longest recurring cycle
;; in its decimal fraction part.


; Notes:
; The length of the recurring cycle (if any)
; will not be larger than the denominator
; e.g: the size of the recurring cycle in 1/7 (142857) is 6, and 6 < 7
;
; see: http://en.wikipedia.org/wiki/Cyclic_number

;; solve for 1 == 10^i % n
;; where i < n
(defn recurring-cycle-count
  [n]
  (loop [i 1]
    (if (= i n)
      0
      (if (= 1 (mod (pow 10 i) n))
        i
        (recur (inc i))))))


(defn euler-026
  []
  (loop [n 1
         max_n n
         max_cycle 0]
    (if (= n 1000)
      max_n
      (let [cycle (recurring-cycle-count n)]
        (if (> cycle max_cycle)
          (recur (inc n) n     cycle)
          (recur (inc n) max_n max_cycle))))))
