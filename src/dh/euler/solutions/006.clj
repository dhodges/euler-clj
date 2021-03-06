;; http://projecteuler.net/index.php?section=problems&id=6
;;
;; The sum of the squares of the first ten natural numbers is,
;; 1� + 2� + ... + 10� = 385
;;
;; The square of the sum of the first ten natural numbers is,
;; (1 + 2 + ... + 10)� = 55� = 3025
;;
;; Hence the difference between the sum of the squares of the
;; first ten natural numbers and the square of the sum is 3025 - 385 = 2640.
;;
;; Find the difference between the sum of the squares of the
;; first one hundred natural numbers and the square of the sum.
;;
;; Answer: 25164150

(ns dh.euler.solutions.006)

(defn sum-of-squares
  [n]
  (if (= n 1)
    1
    (+ (* n n)
       (sum-of-squares (dec n)))))

(defn sums-of
  [n]
  (reduce + (range (inc n))))

(defn square-of-sums
  [n]
  (let [sum (sums-of n)]
    (* sum sum)))

(defn euler-006
  []
  (time (let [squares (square-of-sums 100)
              sums    (sum-of-squares 100)]
          (- squares sums))))
