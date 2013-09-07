; http://www.troubleshooters.com/codecorn/primenumbers/primenumbers.htm
;
; http://en.wikipedia.org/wiki/Sieve_of_Atkin
;
; http://en.wikipedia.org/wiki/Generating_primes
;
; https://groups.google.com/forum/#!topic/clojure/c1pIXjhcbrc

(ns dh.euler.utils.primes
  (:use [dh.euler.utils.core :refer :all]))

; -----------------------------------------------------
; Respectfully copied from the old, monolithic, clojure-contrib lib
; see also: "Lazy wheel sieves and spirals of primes"
; http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.55.7096&rep=rep1&type=pdf

(def primes
  (concat
   [2 3 5 7]
   (lazy-seq
    (let [primes-from
            (fn primes-from [n [f & r]]
                  (if (some #(zero? (rem n %))
                                  (take-while #(<= (* % %) n) primes))
                          (recur (+ n f) r)
                                (lazy-seq (cons n (primes-from (+ n f) r)))))
            wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6  4  2
                          6 4 6 8 4 2 4 2 4 8 6 4 6 2  4  6
                          2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
      (primes-from 11 wheel)))))

; -----------------------------------------------------
; The Haskell Road to Logic, Maths and Programming
;
; LD(n) indicates the least divisor of n
;
; Proposition 1.2
; 1. If n > 1 then LD(n) is a prime number
; 2. If n > 1 and n is not a prime number, then (LD(n))² ≤ n

(defn ldf
  [k n]
  (cond (divides? n k)
        k
        (> (pow k 2) n)
        n
        :else
        (recur (inc k) n)))

(defn least-divisor
  [n]
  (ldf 2 n))

(defn ldpf
  [[p & ps] n]
  (cond (= (rem n p) 0) p
        (> (pow p 2) n) n
        :else
        (ldpf ps n)))

(defn ldp
  [n]
  (ldpf primes n))

(defn prime?
  [n]
  (cond (< n 1)
        (throw (new Exception "not a positive integer"))
        (= n 1)
        false
        :else
        (= (ldp n) n)))

; -----------------------------------------------------

(defn prime-factors [n]
  (cond (< n 1)
        (throw (new Exception "argument not positive"))
        (= n 1)
        []
        :else
        (let [p (least-divisor n)]
          (lazy-seq
           (cons p (prime-factors (quot n p)))))))

; -----------------------------------------------------

