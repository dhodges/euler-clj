#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh_utils
  (:use [clojure.contrib.lazy-seqs :only (primes)]))



; The Haskell Road to Logic, Maths and Programming
;
; LD(n) indicates the least divisor of n
;
; Proposition 1.2
; 1. If n > 1 then LD(n) is a prime number
; 2. If n > 1 and n is not a prime number, then (LD(n))² ≤ n

(defn pow [a b] (reduce * (for [n (range b)] a)))

(defn divides [d n] (= (rem n d) 0))

(defn ldf
  [k n]
  (cond (divides k n)
        k
        (> (pow k 2) n)
        n
        :else
        (recur (inc k) n)))

(defn ld [n] (ldf 2 n))

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

;; (defn pfactors
;;   [n]
;;   (cond (< n 1)
;;         (throw (new Exception "argument not positive"))
;;         (= n 1)
;;         []
;;         :else
;;         (let [p (ld n)]
;;           (lazy-cat [p] (pfactors (quot n p))))))




; (time (count (map prime? (range 2 1000000))))
