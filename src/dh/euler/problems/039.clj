(ns dh.euler.problems.039
  (:use [dh.euler.util.core])
  (:use [clojure.test]))

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
   (let [n2 (inc (/ n 2))]
     (for [a (range 1 n2)
           b (range a n2)
           :when (< a b (- n a b))]
       [a b (- n (+ a b))]))))

(defn triangle-partitions
  [n]
  (filter triangle-partition?
          (partitions n)))


(defn count-triangle-partitions
  [n]
  (count (triangle-partitions n)))


(defn euler-039
  []
  (time
   (loop [n      12
          maxn   n
          maxcount  1]
     (if (>= n 1000)
       {:count maxcount, :num maxn}
       (let [count (count-triangle-partitions n)
             [maxn maxcount] (if (> count maxcount)
                                 [n count]
                                 [maxn maxcount])]
         (recur (+ n 2)
                maxn
                maxcount))))))


(deftest test-partitions
  (is (= 11 (count (partitions 12)))))

(deftest test-euler-039
  (is (=  (:num (euler-039)) 840)))
