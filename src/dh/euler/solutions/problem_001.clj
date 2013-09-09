;; If we list all the natural numbers below 10 that are multiples
;; of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
;;
;; Find the sum of all the multiples of 3 or 5 below 1000.
;;
;; http://projecteuler.net/problem=1
;;
;; Answer: 233168

(ns dh.euler.solutions.problem_001)

(defn multiple-of
  [x y]
  (= (rem x y) 0))


(defn euler-001
  []
  (reduce + (filter #(or (multiple-of % 3)
                         (multiple-of % 5))
                    (range 1000))))
