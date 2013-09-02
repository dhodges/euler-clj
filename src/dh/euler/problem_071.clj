(ns dh.euler.problem_071
  (:use [dh.euler.utils]))

;; http://projecteuler.net/index.php?section=problems&id=71
;;
;; Problem 71
;; 04 June 2004
;;
;; Consider the fraction, n/d, where n and d are positive integers.
;; If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
;;
;; If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size,
;; we get:
;;
;; 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
;;
;; It can be seen that 2/5 is the fraction immediately to the left of 3/7.
;;
;; By listing the set of reduced proper fractions for d ≤ 1,000,000
;; in ascending order of size, find the numerator of the fraction
;; immediately to the left of 3/7.


(def fractions
     (for [d (range 1 1000001)]
       (/ 1 d)))


(defn euler-71
  []
  (time
   (loop [previous  (first fractions)
          this      (second fractions)
          remainder (drop 2 fractions)]
     (if (= this (/ 3 7))
       previous
       (recur this
              (first remainder)
              (rest remainder))))))

