;; Let d(n) be defined as the sum of proper divisors of n
;; (numbers less than n which divide evenly into n).
;;
;; If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair
;; and each of a and b are called amicable numbers.
;;
;; For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110;
;; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
;;
;; Evaluate the sum of all the amicable numbers under 10000.
;;
;; http://projecteuler.net/problem=21
;;
;; Answer: 31626


(ns dh.euler.solutions.021
  (:use [dh.euler.util.core :refer [proper-divisors]]))

(defn d
  "sum of proper divisors of n"
  [n]
  (reduce + (proper-divisors n)))

(defn amicable-pair
  [a]
  (let [b (d a)]
    (if (and (= a (d b))
             (not (= a b)))
      [a b]
      nil)))

(defn euler-021
  []
  (reduce + (filter amicable-pair (range 10000))))
