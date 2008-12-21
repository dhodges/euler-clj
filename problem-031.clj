#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils]))

;; http://projecteuler.net/index.php?section=problems&id=31
;;
;; Problem 31
;; 22 November 2002

;; In England the currency is made up of pound, £, and pence, p,
;; and there are eight coins in general circulation:
;;
;;     1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
;;
;; It is possible to make £2 in the following way:
;;
;;     1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
;;
;; How many different ways can £2 be made using any number of coins?


(def coins [1 2 5 10 20 50 100 200])

(defn set-nth
  "set the nth item of sequence, counted 0...n-1"
  [sequ ndx item]
  (concat (take (dec ndx) sequ) [1] (drop ndx sequ)))

(defn min-above-1
  [seq]
  (apply min (filter #(> % 1) seq)))

(defn decr
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
        1))

(defn rindex-of
  "index (0...n-1) of the last instance of item in seq"
  [seq item]
  (let [len  (count seq)
        seq2 (reverse seq)]
    (loop [ndx 0]
      (if (>= ndx len)
        -1
        (let [x (nth seq2 ndx)]
          (if (= x item)
            (- len ndx 1)
            (recur (inc ndx))))))))
    

(defn generate-partitions
  [n]
  (if (not (member-of-sequence? n coins))
    (throw
     (new Exception
          (format "n must be a member of the sequence: %s" coins)))
    (loop [partitions [[n]]]
      (let [partition  (last partitions)]
        (if (= (first partition) 1)
          partitions
          (let [least (min-above-1 partition)
                ndx   (rindex-of partition least)
                least (decr least)
                part  (into (into [] (take ndx partition)) [least])
                sum   (apply + part)
                part  (into part (for [x (range (quot (- n sum) least))] least))
                sum   (apply + part)
                partition (into part (for [x (range (- n sum))] 1))]
            (recur (conj partitions partition))))))))

