(ns dh.euler.problems.047
  (:require [dh.euler.util.primes] :refer [prime-factors]))

;; http://projecteuler.net/index.php?section=problems&id=47
;;
;; Problem 47
;; 04 July 2003
;;
;; The first two consecutive numbers to have two distinct prime factors are:
;;
;; 14 = 2 × 7
;; 15 = 3 × 5
;;
;; The first three consecutive numbers to have three distinct prime factors are:
;;
;; 644 = 2² × 7  × 23
;; 645 = 3  × 5  × 43
;; 646 = 2  × 17 × 19
;;
; Find the first four consecutive integers to have four distinct primes factors.
;; What is the first of these numbers?


(defn dpfactors
  "distinct prime factors"
  [n]
  (distinct (prime-factors n)))

(def factors (ref {}))

(defn dpf4?
  "does 'n' have 4 distinct prime factors?"
  [n]
  (let [result (get @factors n)]
    (if result
      result
      (dosync
        (ref-set factors (assoc @factors n (= (dpfactors n) 4)))
        (get @factors n)))))

(defn euler-047
  []
  (time
   (loop [a 1 b 2 c 3 d 4]
     (if (and (dpf4? a)
              (dpf4? b)
              (dpf4? c)
              (dpf4? d))
       a
       (recur (inc a) (inc b) (inc c) (inc d))))))
