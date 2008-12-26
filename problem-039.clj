#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [clojure.contrib.test-is])
  (:use [clojure.contrib.seq-utils])
  (:use [project_euler.dh_utils]))

;; http://projecteuler.net/index.php?section=problems&id=39
;;
;; Problem 39
;; 14 March 2003
;;
;; If p is the perimeter of a right angle triangle with
;; integral length sides, {a,b,c}, there are exactly three solutions
;; for p = 120.
;;
;; {20,48,52}, {24,45,51}, {30,40,50}
;;
;; For which value of p ≤ 1000, is the number of solutions maximised?


(defn triangle-partition?
  [triple]
  (let [[a b c] triple]
    (= (+ (* a a) (* b b))
       (* c c))))


(defn partitions
  [n]
  (distinct
   (map sort
        (apply concat
               (filter #(not (nil? %))
                       (for [c (range (int (/ n 2)) n)]
                         (let [diff (- n c)]
                           (for [a (range (int (/ diff 2)) diff)]
                             (let [b (- diff a)]
                               [a b c])))))))))
          
(defn triangle-partitions
  [n]
  (filter triangle-partition?
          (partitions n)))


(defn count-partitions
  [n]
  (count (triangle-partitions n)))


(defn euler-039
  []
  (time
   (loop [n    12
          count 1]
     (if (> n 1000)
       {:count count, :num n}
       (recur (inc n)
              (count-partitions n))))))


(deftest test-partitions
  (= 11 (count (partitions 12))))
