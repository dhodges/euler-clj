;; http://projecteuler.net/problem=7
;;
;; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
;; we can see that the 6th prime is 13.
;;
;; What is the 10001st prime number?
;;
;; Answer: 104743

(ns dh.euler.solutions.007
  (:use [dh.euler.util.primes :refer [primes]]))

(defn nth-prime
  [n]
  (last (take n primes)))

(defn euler-007
  []
  (time (nth-prime 10001)))
