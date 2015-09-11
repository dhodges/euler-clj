;; 2520 is the smallest number that can be divided by each of the numbers
;; from 1 to 10 without any remainder.
;;
;; What is the smallest number that is evenly divisible by all of the numbers from 1 to 20?
;;
;; http://projecteuler.net/problem=5
;;
;; Answer: 232792560

(ns dh.euler.solutions.005
  (:use [dh.euler.util.core :refer [is-a-factor?]]))


(defn not-a-factor?
  [x y]
  (not (is-a-factor? x y)))


(defn evenly-divisible-by-all?
  [x divisors]
  (loop [d (first divisors)
         others (rest divisors)]
    (cond (not-a-factor? x d)
          false
          (empty? others)
          true
          :else
          (recur (first others) (rest others)))))


(defn euler-005
  []
  (let [divisors (range 1 20)]
    (loop [x 2520]
      (if (evenly-divisible-by-all? x divisors)
        x
        (recur (+ x 2520))))))
