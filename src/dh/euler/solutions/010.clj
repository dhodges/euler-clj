;; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
;;
;; Find the sum of all the primes below two million.
;;
;; Answer: 142913828922
;;
;; http://projecteuler.net/problem=10
;;

(ns dh.euler.solutions.010
  (:use [dh.euler.util.primes :refer [primes prime?]]))

(defn euler-010
  []
  (reduce + (take-while #(> 2000000 %) primes)))
