#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))

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


(defn count-coin-partitions
  [n]
  (count-partitions n dec-coins))




; from SICP, p. 40
; Example: Counting change

(defn first-denomination
  [kinds-of-coins]
  (cond (= kinds-of-coins 1) 1
        (= kinds-of-coins 2) 2
        (= kinds-of-coins 3) 5
        (= kinds-of-coins 4) 10
        (= kinds-of-coins 5) 20
        (= kinds-of-coins 6) 50
        (= kinds-of-coins 7) 100
        (= kinds-of-coins 8) 200))


(defn cc
  [amount kinds-of-coins]
  (cond (= amount 0)
        1
        (or (< amount 0) (= kinds-of-coins 0))
        0
        :else
        (+ (cc amount
               (- kinds-of-coins 1))
           (cc (- amount
                  (first-denomination kinds-of-coins))
               kinds-of-coins))))

(defn euler-031
  []
  (time (cc 200 8)))



(deftest test-generate-coin-partitions
  (= (count-coin-partitions 50) 441))

(deftest test-generate-partitions
  (= (count-partitions 10) 33))

(deftest test-count-coin-partitions
  (is (not (member-of-sequence?
            (count-coin-partitions 200)
            [73652
             73653]))))

(deftest euler-031
  (= (euler-031) 73682))
