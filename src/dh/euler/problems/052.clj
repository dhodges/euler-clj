(ns dh.euler.problems.052
  (:require [dh.euler.util.core] :refer [pow])
  (:require [clojure.test :refer :deftest]))

;; http://projecteuler.net/index.php?section=problems&id=52
;;
;; Problem 52
;; 12 September 2003
;;
;; It can be seen that the number, 125874, and its double, 251748,
;; contain exactly the same digits, but in a different order.
;;
;; Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x,
;; contain the same digits.

(defn testn
  [n]
  (apply = (map #(sort (str %))
                [(* 2 n) (* 3 n) (* 4 n) (* 5 n) (* 6 n)])))


(defn display
  [n]
  (printf "%s: %s %s %s %s %s\n"
          n (* n 2) (* n 3) (* n 4) (* n 5) (* n 6)))


; brute force
(defn euler-52
  []
  (time
   (loop [n 1]
     (cond (testn n)    (display n)
           (> n 1000000) false
           :else
           (recur (inc n))))))

(deftest test-euler-52
  []
  (is (= (solution) 142857)))
