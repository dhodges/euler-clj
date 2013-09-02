(ns dh.euler.problem_078
  (:use [dh.euler.utils]))

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
              part   (concat (take ndx partition) [least])
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
  "Counts each partition generated - faster than (count (generate-partitions _))"
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
               (cond (< remain least)
                     (into (conj part remain)
                           (nseq (- n (apply + part) remain) 1))

                     (= remain least)
                     (conj part remain)

                     (> remain least)
                     (concat part
                             (nseq (quot remain least) least)
                             (nseq (rem remain least)  1))))))))

(defn print-growth-of-p
  []
  (let [xs    (for [x (range 1 52)] (p x))
        steps (for [x (range 50)] (- (nth xs (inc x))
                                     (nth xs x)))]
    (doseq [x (range 50)]
      (printf "%2s-%2s: %5s\n" x (inc x) (nth steps x)))))

(defn print-p
  []
  (doseq [x (range 1 62)]
    (printf "%2s: %6s\n" x (p x))))


(deftest test-p
  (is (= (p 5) 7)))

