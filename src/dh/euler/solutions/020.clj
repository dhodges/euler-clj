;; n! means n × (n − 1) × ... × 3 × 2 × 1
;;
;; Find the sum of the digits in the number 100!
;;
;; http://projecteuler.net/problem=20
;;
;; Answer: 648

(ns dh.euler.solutions.020
  (:use [dh.euler.util.core :refer [factorial]]))

(defn euler-020
  []
  (apply +'
         (map #(- (int %) 48)
              (str (factorial 100)))))
