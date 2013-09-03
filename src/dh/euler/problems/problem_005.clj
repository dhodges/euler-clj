(ns dh.euler.problems.problem_005)

;; http://projecteuler.net/index.php?section=problems&id=5
;;
;; 2520 is the smallest number that can be divided by each of the numbers
;; from 1 to 10 without any remainder.
;;
;; What is the smallest number that is evenly divisible by all of the numbers from 1 to 20?


(defn is-a-factor?
  [x y]
  (= java.lang.Integer (class (/ x y))))


(defn not-a-factor?
  [x y]
  (not (is-a-factor? x y)))


(defn evenly-divisible-by-all?
  [x divisors]
  (loop [d (first divisors)
         others (rest divisors)]
    (cond (not-a-factor? x d)
          false
          (empty? others)
          true
          :else
          (recur (first others) (rest others)))))


(defn euler-005
  []
  (time
   (let [divisors (range 1 20)]
     (loop [x 20]
       (if (evenly-divisible-by-all? x divisors)
         x
         (recur (inc x)))))))

