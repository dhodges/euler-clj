(ns dh.euler.util.core
  (:use [clojure.set :refer [intersection]])
  (:use [clojure.math.numeric-tower :refer [expt]]))

(defn num-digits
  [n]
  (inc (Math/floor (Math/log10 n))))

(defn pow
  [a b]
  (expt a b))

(defn squint
  "integer square root"
  [n]
  (int (Math/floor (Math/sqrt n))))

; fibonacci
; taken from: http://blog.n01se.net/?p=33

(def fibonacci
  (lazy-cat [1 1] (map +' fibonacci (rest fibonacci))))

(defn nth-fibonacci
  [n]
  (last (take n fibonacci)))

(defn factorial
  [n]
  (cond (= n 0)
        1
        (= n 1)
        1
        :else
        (*' n (factorial (dec n)))))

(defn fac
  [n]
  (factorial n))

; -----------------------------------------------------

(defn member-of-sequence?
  "assumes coll is a sorted list"
  [item coll]
  (cond (empty? coll)
        false
        (= item (first coll))
        true
        (< item (first coll))
        false
        :else
        (recur item (rest coll))))

(defn divisible-by?
  "is n divisible by d?"
  [n d]
  (= (rem n d) 0))

(defn is-a-factor?
  [n d]
  (divisible-by? n d))

(defn factor?
  [n d]
  (is-a-factor? n d))

(defn factors-of
  [n]
  (let [sqrt    (squint n)
        factors (loop [x 2 factors '()]
                  (if (> x sqrt)
                    factors
                    (recur (inc x)
                           (if (factor? n x)
                             (concat [x (/ n x)] factors)
                             factors))))]
    (sort (distinct (conj factors 1 n)))))

(defn factorise
  [n]
  (factors-of n))

(defn proper-divisors
  "all the factors of n (other than n itself)"
  [n]
  (let [factors (factorise n)]
    (take (dec (count factors)) factors)))

(defn highest-factor
  "highest factor of n (other than n itself)"
  [n]
  (let [factors (factors-of n)
        factors' (take (dec (count factors)) factors)]
    (reduce max factors')))

(defn gcd
  "greatest common divisor"
  [a b]
  (let [afactors (set (factorise a))
        bfactors (set (factorise b))]
    (reduce max (intersection afactors bfactors))))
