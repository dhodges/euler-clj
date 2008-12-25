#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns project_euler.dh_utils
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

(defn pfactors
  [n]
  (cond (< n 1)
        (throw (new Exception "argument not positive"))
        (= n 1)
        []
        :else
        (let [p (ld n)]
          (lazy-cons p (pfactors (quot n p))))))

; -----------------------------------------------------


(defn member-of-sequence?
  "assumes coll is a sorted list of numbers"
  [item coll]
  (cond (empty? coll)
        false
        (= item (first coll))
        true
        (< item (first coll))
        false
        :else
        (recur item (rest coll))))


(defn factor?
  [x y]
  (= (rem x y) 0))


(defn proper-divisors
  "return the proper divisors of the given number"
  [n]
  (let [squint  (int (Math/floor (Math/sqrt n)))
        factors (loop [x 2 factors '()]
                  (if (> x squint)
                    factors
                    (recur (inc x)
                           (if (factor? n x)
                             (concat [x (/ n x)] factors)
                             factors))))]
    (sort (distinct (conj factors 1)))))


(defn member?
  [item coll]
  (cond (empty? coll)
        false
        (= item (first coll))
        true
        :else
        (recur item (rest coll))))


(defn strcontains?
  [s chr]
  (< -1 (.indexOf s (str chr))))

(defn strcat
  [& items]
  (apply str (apply concat (map str items))))


(defn pandigital-str?
  [nstr]
  (reduce #(and %1 %2)
          (for [i (range 1 (inc (count nstr)))]
            (strcontains? nstr (char (+ 48 i))))))

(defn pandigital?
  [n]
  (pandigital-str? (str n)))

(defn fac
  "factorial"
  [n]
  (cond (= n 0)
        1
        (= n 1)
        1
        :else
        (* n (fac (dec n)))))


(defn palindrome?
  [s]
  (= s (apply str (reverse s))))


