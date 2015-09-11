;; Starting in the top left corner of a 2 x 2 grid, there are 6 routes
;; (without backtracking) to the bottom right corner.
;;
;; How many routes are there through a 20 x 20 grid?
;;
;; http://projecteuler.net/problem=15

(ns dh.euler.solutions.015
  (:use [dh.euler.util.sequences :refer [pascal-diagonal]]))


; The number of times a path passes through
; a node at the edge; these sequences correspond to
; the diagonals of Pascal's triangle.
;
;;   . . . 1
;;   . . . 2
;;   . . .
;;   1 2

;;   . . . . 1
;;   . . . . 3
;;   . . . . 6
;;   . . . .
;;   1 3 4

;; .  .  .  .  .  1
;; .  .  .  .  .  4
;; .  .  .  .  . 10
;; .  .  .  .  . 20
;; .  .  .  .  .
;; 1  4 10 20

(defn number-of-routes
  [n]
  (* 2 (apply + (pascal-diagonal n n))))

(defn euler-015
  []
  (number-of-routes 20))
