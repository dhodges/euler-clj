#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))

;; Let p(n) represent the number of different ways in which n coins can be separated
;; into piles.
;;
;; For example, five coins can be separated into piles in exactly seven different ways,
;; so p(5) = 7.
;;
;; Find the least value of n for which p(n) is divisible by one million.


(defn nseq
  [n item]
  (for [x (range n)] item))


(defn rindex-not-1
  "index (0...n-1) of the last instance of item > 1"
  [seq]
  (loop [ndx (dec (count seq))]
    (cond (< ndx 0)
          -1
          (> (nth seq ndx) 1)
          ndx
          :else
          (recur (dec ndx)))))

(defn generate-partitions
  [n]
  (loop [partitions [[n]]]
    (let [partition  (last partitions)]
      (if (= (first partition) 1)
        partitions
        (let [ndx    (rindex-not-1 partition)
              least  (dec (nth partition ndx))
              part   (into [] (concat (take ndx partition) [least]))
              remain (- n (apply + part))
              partition (cond (= remain least)
                              (conj part remain)
                              (< remain least)
                              (into (conj part remain)
                                    (nseq (- n (apply + part) remain) 1))
                              (> remain least)
                              (concat part 
                                      (nseq (quot remain least) least)
                                      (nseq (rem remain least)  1)))]
                              (recur (conj partitions partition)))))))

(defn p
  "Counts each partition generated."
  [n]
  (loop [count     1
         partition [n]]
    (if (= (first partition) 1)
      count
      (let [ndx    (rindex-not-1 partition)
            least  (dec (nth partition ndx))
            part   (into [] (concat (take ndx partition) [least]))
            remain (- n (apply + part))]
        (recur (inc count)
               (cond (= remain least)
                     (conj part remain)
                     (< remain least)
                     (into (conj part remain)
                           (nseq (- n (apply + part) remain) 1))
                     (> remain least)
                     (concat part 
                             (nseq (quot remain least) least)
                             (nseq (rem remain least)  1))))))))

(deftest test-p
  (= (p 5) 7))

