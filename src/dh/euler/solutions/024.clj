;; A permutation is an ordered arrangement of objects. For example,
;; 3124 is one possible permutation of the digits 1, 2, 3 and 4.
;;
;; If all of the permutations are listed numerically or alphabetically,
;; we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
;;
;; 012   021   102   120   201   210
;;
;; What is the millionth lexicographic permutation of the digits
;; 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
;;
;; http://projecteuler.net/index.php?section=problems&id=24
;;
;; Answer: 2783915460

(ns dh.euler.solutions.024
  (:use [dh.euler.util.core :refer [factorial]]))

; see:
; http://forums.xkcd.com/viewtopic.php?f=11&t=28832#p926767

(defn euler-024
  []
  (loop [soln   []
         digits [0 1 2 3 4 5 6 7 8 9]
         remain 999999
         f      9]
    (if (> remain 0)
      (let [n (quot remain (factorial f))
             c (nth digits n)]
        (recur (conj soln c)
               (concat (take n digits) (drop (inc n) digits))
               (- remain (* (factorial f) n))
               (dec f)))
      (apply str (conj soln (nth digits 0))))))
