(ns dh.euler.problem_053
  (:use [dh.euler.utils]))


;; http://projecteuler.net/index.php?section=problems&id=53
;;
;; Problem 53
;; 26 September 2003
;;
;; There are exactly ten ways of selecting three from five, 12345:
;;
;; 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
;;
;;                                       5
;; In combinatorics, we use the notation, C  = 10.
;;                                         3
;;
;; In general,
;;
;; n          n!
;;  C   =  -------
;;   r     r!(n-r)!
;;
;; 	where r ≤ n, n! = n×(n−1)×...×3×2×1, and 0! = 1.
;;
;; It is not until n = 23, that a value exceeds one-million:
;;
;;   23
;;     C   = 1144066.
;;      10
;;                                               n
;; How many, not necessarily distinct, values of  C, for 1 ≤ n ≤ 100,
;; are greater than one-million?                   r


(defn C
  [n r]
  (/ (fac n)
     (* (fac r) (fac (- n r)))))


(defn euler-53
  []
  (time
   (count (for [n (range 23 101)
                r (range 1 (inc n))
                :when (> (C n r) 1000000)]
            true))))


(deftest test-euler-53
  (is (= (euler-53) 4075)))

