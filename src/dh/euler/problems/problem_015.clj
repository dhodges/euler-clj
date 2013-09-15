;; Starting in the top left corner of a 2 x 2 grid, there are 6 routes
;; (without backtracking) to the bottom right corner.
;;
;; How many routes are there through a 20 x 20 grid?
;;
;; http://projecteuler.net/problem=15

(ns dh.euler.problems.problem_015
  (:use [dh.euler.util.core :refer [pow]]))


(defn grid-routes
  [size]
  (loop [ndx 1
         m   1]
    (if (> ndx size)
      m
      (recur (inc ndx)
             (/ (* m (- (dec (* (inc size) 2)) ndx)) ndx)))))

(defn euler-015
  []
  (time
   (grid-routes 20)))


;; (deftest test-euler-015
;;   (is (= (euler-015) 137846528820)))

(defn gridseq-n
  [n]
  (if (= n 0)
    0
    (+' (*' 2 n)
        (gridseq-n (dec n)))))

(defn path-count
  [n]
  (if (= n 1)
    2
    (+ (pow 2 n)
       (path-count (dec n)))))


;; . . . . . .
;; . . . . . .
;; . . . . . .
;; . . . . . .
;; . . . . . .
;; . . . . . .

;; 0 x 0 =  0
;; 1 x 1 =  2
;; 2 x 2 =  6
;; 3 x 3 = 14
;; 4 x 4 = 30

;; . . . .
;; . . . .
;; . . . .
;; . . . .

;; . . . . .
;; . . . . .
;; . . . . .
;; . . . . .
;; . . . . .
