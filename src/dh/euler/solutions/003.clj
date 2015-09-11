;; The prime factors of 13195 are 5, 7, 13 and 29.
;;
;; What is the largest prime factor of the number 600851475143 ?
;;
;; http://projecteuler.net/problem=3
;;
;; Answer: 6857

(ns dh.euler.solutions.003
  (:use [dh.euler.util.primes :refer [prime-factors]]))


(defn largest-prime-factor-of
  [n]
  (reduce max (prime-factors n)))

(defn euler-003
  []
  (largest-prime-factor-of 600851475143))
