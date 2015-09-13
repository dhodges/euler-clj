;; 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
;;
;; What is the sum of the digits of the number 2^1000?
;;
;; http://projecteuler.net/problem=16
;;
;; Answer: 1366

(ns dh.euler.solutions.016
  (:use [dh.euler.util.core :refer [pow]]))


(defn euler-016
  []
  (time (reduce + (map #(- (int %) 48)
                       (seq (str (pow 2 1000)))))))
