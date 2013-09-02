(ns dh.euler.problem_015
  (:use [dh.euler.utils]))

;; Problem 15
;;
;; http://projecteuler.net/index.php?section=problems&id=15
;;
;; Starting in the top left corner of a 2 x 2 grid, there are 6 routes
;; (without backtracking) to the bottom right corner.
;;
;; How many routes are there through a 20 x 20 grid?


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


(deftest test-euler-015
  (is (= (euler-015) 137846528820)))
