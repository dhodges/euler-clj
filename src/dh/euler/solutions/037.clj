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
  (:use [dh.euler.util.core   :refer [num-digits pow]]
        [dh.euler.util.primes :refer [prime? primes]]))


;; Notes
;; Truncatable Primes
;; http://en.wikipedia.org/wiki/Truncatable_prime

(defn flint [n] (int (Math/floor n)))

(defn left-truncatable-prime?
  [n]
  (every? prime?
          (for [i (range (num-digits n) 0 -1)]
            (int (mod n (pow 10 i))))))

(defn right-truncatable-prime?
  [n]
  (every? prime?
          (for [i (range 1 (num-digits n))]
            (flint (/ n (pow 10 i))))))

(defn truncatable-prime?
  [n]
  (and (right-truncatable-prime? n)
       (left-truncatable-prime?  n)))

(def upper-limit 739397)

(defn euler-037
  []
  (time (apply + (for [n (range 8 (inc upper-limit))
                       :when (truncatable-prime? n)]
                   n))))

;; (defn euler-037-new
;;   []
;;   (time
;;    (apply + (filter truncatable-prime?
;;                     (take-while #(> upper-limit %)
;;                                 (drop-while #(> 8 %) primes))))))
