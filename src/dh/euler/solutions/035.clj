;; The number 197 is called a circular prime because all rotations
;; of the digits: 197, 971, and 719, are themselves prime.
;;
;; There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17,
;; 31, 37, 71, 73, 79, and 97.
;;
;; How many circular primes are there below one million?
;;
;; http://projecteuler.net/problem=35
;;
;; Answer: 55

(ns dh.euler.solutions.035
  (:use [dh.euler.util.core   :refer [num-digits]]
        [dh.euler.util.primes :refer [primes prime?]]
        [dh.euler.util.string :refer [str-rotate-left]])
  (:import [java.lang Math]))


(defn rotate-left-num
  [num n]
  (Integer/parseInt (str-rotate-left (str num) n)))


(defn circular-prime?
  [n]
  (every? prime?
          (for [i (range 1 (inc (num-digits n)))]
            (rotate-left-num n i))))


(defn euler-035
  []
  (time (count
         (filter circular-prime?
                 (take-while #(> 1000000 %) primes)))))
