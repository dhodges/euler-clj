(ns dh.euler.problems.075)

;; It turns out that 12cm is the smallest length of wire that can be bent to form an
;; integer sided right angle triangle in exactly one way, but there are many more examples.
;;
;;             12cm: (3, 4, 5)
;;             24cm: (6, 8, 10)
;;             30cm: (5, 12, 13)
;;             36cm: (9, 12, 15)
;;             40cm: (8, 15, 17)
;;             48cm: (12, 16, 20)
;;
;; In contrast, some lengths of wire, like 20cm, cannot be bent to form an integer sided
;; right angle triangle, and other lengths allow more than one solution to be found -
;; for example, using 120cm it is possible to form exactly three different
;; integer sided right angle triangles.
;;
;;             120cm: (30, 40, 50), (20, 48, 52), (24, 45, 51)
;;
;; Given that L is the length of the wire, for how many values of L ≤ 2,000,000
;; can exactly one integer sided right angle triangle be formed?


(defn square [n]
  (* n n))

(defn triangle-partition?
  [triple]
  (let [[a b c] triple]
    (= (+ (square a)
          (square b))
       (square c))))

(defn partition-into-threes
  [n]
  (distinct
   (let [n2 (inc (/ n 2))]
     (for [a (range 1 n2)
           b (range a n2)
           :when (< a b (- n a b))]
       [a b (- n (+ a b))]))))

(defn make-triangle-partitions
  [n]
  (map sort
       (filter triangle-partition?
               (partition-into-threes n))))

(defn count-partitions
  [n]
  (count (make-triangle-partitions n)))

(defn single-triangle?
  "can exactly one integer sided right angle triangle be formed from n?"
  [n]
  (= (count-partitions n) 1))

(defn multiple-of-any
  "is any one of factors a divisor of 'n'?"
  [n factors]
  (cond (empty? factors)
        false
        (= (rem n (first factors)) 0)
        true
        :else
        (recur n (rest factors))))

(defn euler-075
  []
  (time (loop [n 1
               count 0]
          (if (= n 2000000)
            count
            (recur (inc n)
                   (if (single-triangle? n)
                     (inc count)
                     (count)))))))
