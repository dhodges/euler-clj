;; The number 3797 has an interesting property. Being prime itself,
;; it is possible to continuously remove digits from left to right,
;; and remain prime at each stage: 3797, 797, 97, and 7.
;;
;; Similarly we can work from right to left: 3797, 379, 37, and 3.
;;
;; Find the sum of the only eleven primes that are both truncatable
;; from left to right and right to left.
;;
;; NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
;;
;; http://projecteuler.net/problem=37

(ns dh.euler.solutions.037
  (:use [dh.euler.util.core   :refer [factor? factorise]]
        [dh.euler.util.primes :refer [prime?]]))


; Notes
; Truncatable Primes
; http://en.wikipedia.org/wiki/Truncatable_prime
;

(defn left-truncatable-prime?
  [n]
  (let [nstr (str n)]
    (reduce #(and %1 %2)
            (for [i (range (count nstr))]
              (prime? (Integer/parseInt (.substring nstr i)))))))

(defn right-truncatable-prime?
  [n]
  (let [nstr (str n)]
    (reduce #(and %1 %2)
            (for [i (range (count nstr) 0 -1)]
              (prime? (Integer/parseInt (.substring nstr 0 i)))))))

(defn truncatable-prime?
  [n]
  (and (right-truncatable-prime? n)
       (left-truncatable-prime?  n)))

(def upper-limit 739397)

(defn euler-037
  []
  (apply + (for [n (range 8 (inc upper-limit))
                 :when (truncatable-prime? n)]
             n)))
