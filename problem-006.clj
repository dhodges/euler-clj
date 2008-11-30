#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; http://projecteuler.net/index.php?section=problems&id=6
;;
;; The sum of the squares of the first ten natural numbers is,
;; 1� + 2� + ... + 10� = 385
;;
;; The square of the sum of the first ten natural numbers is,
;; (1 + 2 + ... + 10)� = 55� = 3025
;;
;; Hence the difference between the sum of the squares of the first ten natural numbers 
;; and the square of the sum is 3025 - 385 = 2640.
;;
;; Find the difference between the sum of the squares of the first one hundred natural numbers 
;; and the square of the sum.

(defn sum-of-squares
  [n]
  (if (= n 1)
    1
    (+ (* n n)
       (sum-of-squares (dec n)))))

(defn sums-of
  [n]
  (if (= n 1)
    1
    (+ n (sums-of (dec n)))))
     
(defn square-of-sums
  [n]
  (let [sum (sums-of n)]
    (* sum sum)))

(defn solution
  [n]
  (let [squares (square-of-sums n)
        sums    (sum-of-squares n)]
  (println
   (format "%s - %s = %s" squares sums (- squares sums)))))
