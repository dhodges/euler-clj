#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))

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


(defn triangle-partition?
  [triple]
  (let [[a b c] triple]
    (= (+ (* a a) (* b b))
       (* c c))))


(defn triangle-partitions
  [n]
  (filter triangle-partition?
          (distinct
           (map sort
                (let [n2 (inc (/ n 2))]
                  (for [a (range 1 n2)
                        b (range a n2)
                        :when (< a b (- n a b))]
                    [a b (- n (+ a b))]))))))


(defn count-partitions
  [n]
  (count (triangle-partitions n)))


(defn show-partitions
  [n]
  (doseq [x (filter #(not (triangle-multiple? %))
                    (range 12 (inc n) 2))]
    (let [tp (triangle-partitions x)]
      (if tp
        (printf "%3s (%s): %s\n" x (count tp) tp)))))

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


(defn show-single-partitions
  [limit]
  (loop [n 12
         multiples []]
    (if (not (multiple-of-any n multiples))
      (let [tp  (triangle-partitions n)
            num (count tp)]
        (if (= num 1)
          (printf "%3s (%s): %s\n" n num tp))))
    (recur (+ n 2)
           (if (> num 1) (conj multiples n) num))))
          
        
(deftest test-single-triangle
  (and (= (single-triangle? 182) true)
       (= (single-triangle? 120) false)))

(deftest test-multiple-of-any
  (and (= (multiple-of-any 10 [3 7]) false)
       (= (multiple-of-any 10 [3 7 5]) true)))

