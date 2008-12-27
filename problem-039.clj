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
        (let [n2 (inc (/ n 2))]
          (for [a (range 1 n2)
                b (range a n2)
                :when (< a b (- n a b))]
            [a b (- n (+ a b))])))))


(defn triangle-partitions
  [n]
  (filter triangle-partition?
          (partitions n)))


(defn count-partitions
  [n]
  (count (triangle-partitions n)))


(defn show-partitions
  [n]
  (doseq [x (range 12 (inc n) 2)]
    (let [tp (triangle-partitions x)]
      (if tp
        (printf "%s (%s): %s\n" x (count tp) tp)))))


(defn euler-039
  []
  (time
   (loop [n        12
          maxnum   12
          maxcount  1]
     (if (>= n 1000)
       {:count maxcount, :num maxnum}
       (let [next  (+ n 2)
             count (count-partitions next)
             [maxnum maxcount] (if (> count maxcount)
                                 [next count]
                                 [maxnum maxcount])]
         (recur next
                maxnum
                maxcount))))))


(deftest test-partitions
  (= 11 (count (partitions 12))))

