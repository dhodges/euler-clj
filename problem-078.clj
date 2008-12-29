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

(defn min-above-1
  [seq]
  (apply min (filter #(> % 1) seq)))


(defn dec-coins
  [n]
  (cond (= n 200)
        100
        (= n 100)
        50
        (= n 50)
        20
        (= n 20)
        10
        (= n 10)
        5
        (= n 5)
        2
        (= n 2)
        1
        (= n 1)
        (throw (new Exception "cannot decrement coins any lower than 1"))
        :else
        (- n 1)))

(defn rindex-of
  "index (0...n-1) of the last instance of item in seq"
  [seq item]
  (loop [ndx (dec (count seq))]
    (cond (< ndx 0)
          -1
          (= (nth seq ndx) item)
          ndx
          :else
          (recur (dec ndx)))))
    

(defn generate-partitions
  ([n]
     (generate-partitions n dec))
  ([n decfn]
     (loop [partitions [[n]]]
       (let [partition  (last partitions)]
         (if (= (first partition) 1)
           partitions
           (let [least (min-above-1 partition)
                 ndx   (rindex-of partition least)
                 least (decfn least)
                 part  (into (into [] (take ndx partition)) [least])
                 sum   (apply + part)
                 part  (into part (for [x (range (quot (- n sum) least))] least))
                 sum   (apply + part)
                 partition (into part (for [x (range (- n sum))] 1))]
             (recur (conj partitions partition))))))))


(defn count-partitions
  ([n]
     (count-partitions n dec))
  ([n decfn]
     (loop [partition [n]
            count     1]
         (if (= (first partition) 1)
           count
           (let [least (min-above-1 partition)
                 ndx   (rindex-of partition least)
                 least (decfn least)
                 part  (conj (into [] (take ndx partition)) least)
                 sum   (apply + part)
                 part  (into part (for [x (range (quot (- n sum) least))] least))
                 sum   (apply + part)
                 partition (into part (for [x (range (- n sum))] 1))]
             (recur partition
                    (inc count)))))))


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

(defn nseq
  [n item]
  (for [x (range n)] item))

(defn generate-partitions-2
  [n]
  (loop [partitions [[n]]]
    (let [partition  (last partitions)]
      (if (= (first partition) 1)
        partitions
        (let [ndx    (rindex-not-1 partition)
              least  (dec (nth partition ndx))
              part   (into (into [] (take ndx partition)) [least])
              remain (- n (apply + part))
              partition (cond (= remain least)
                              (conj part remain)
                              (< remain least)
                              (into (conj part remain)
                                    (nseq (- n (apply + part) remain) 1))
                              (> remain least)
                              (concat part 
                                      (nseq (quot remain least) least)
                                      (nseq (rem remain least) 1)))]
                              (recur (conj partitions partition)))))))


(defn p
  [n]
  (count (distinct (generate-partitions-2 n))))



(deftest test-p
  (= (count (distinct (generate-partitions-2 5))) 7))

