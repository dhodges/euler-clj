;; Euler discovered the remarkable quadratic formula:
;;
;; n² + n + 41
;;
;; It turns out that the formula will produce 40 primes
;; for the consecutive values n = 0 to 39.
;;
;; However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41,
;; and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.
;;
;; The incredible formula  n² − 79n + 1601 was discovered,
;; which produces 80 primes for the consecutive values n = 0 to 79.
;; The product of the coefficients, −79 and 1601, is −126479.
;;
;; Considering quadratics of the form:
;;
;; n² + an + b, where |a| < 1000 and |b| < 1000
;;
;; where |n| is the modulus/absolute value of n
;; e.g. |11| = 11 and |−4| = 4
;;
;; Find the product of the coefficients, a and b,
;; for the quadratic expression that produces the maximum number of primes
;; for consecutive values of n, starting with n = 0.
;;
;; http://projecteuler.net/problem=27
;;
;; Answer: -59231

(ns dh.euler.solutions.027
  (:use [dh.euler.util.primes :refer [primes prime?]]))

;; Notes
;;
;; n² + an + b
;;
;; b will be prime for all solutions,
;; because the result must be prime when n = 0


(defn num-consecutive-primes
  [a b]
  (loop [n 0]
    (if (prime? (+ (* n n) (* a n) b))
      (recur (inc n))
      n)))

(defn max-consecutive-primes-and-b-for
  [a]
  (reduce (fn [x y]
            (if (> (first x) (first y))
              x
              y))
          (for [b (take-while #(< % 1000) primes)]
            [(num-consecutive-primes a b) b])))

(defn euler-027
  []
  (loop [a     -999
         maxa -1000
         maxb -1000
         maxnum   0]
    (let [[num b] (max-consecutive-primes-and-b-for a)]
      (if (= a 1000)
        (* maxa maxb)
        (if (> maxnum num)
          (recur (inc a)
                 maxa
                 maxb
                 maxnum)
          (recur (inc a)
                 a
                 b
                 num))))))
