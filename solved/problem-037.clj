#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [clojure.contrib.test-is]))

;; http://projecteuler.net/index.php?section=problems&id=37
;;
;; Problem 37
;; 14 February 2003
;;
;; The number 3797 has an interesting property. Being prime itself,
;; it is possible to continuously remove digits from left to right,
;; and remain prime at each stage: 3797, 797, 97, and 7.
;;
;; Similarly we can work from right to left: 3797, 379, 37, and 3.
;;
;; Find the sum of the only eleven primes that are both truncatable
;; from left to right and right to left.
;;
;; NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

; Notes
; Truncatable Primes
; http://en.wikipedia.org/wiki/Truncatable_prime
;

(defn factor?
  [x y]
  (= (rem x y) 0))

(defn factorise
  "return the factors of the given number"
  [n]
  (let [squint  (int (Math/floor (Math/sqrt n)))
        factors (loop [x 1 factors '()]
                  (if (> x squint)
                    factors
                    (recur (inc x)
                           (if (factor? n x)
                             (concat [x (/ n x)] factors)
                             factors))))]
    (sort (distinct factors))))

(defn prime?
  [n]
  (if (and (not (= n 2))
           (even? n))
    false
    (= [1 n] (factorise n))))

(defn left-truncatable-prime?
  [n]
  (let [nstr (str n)]
    (reduce #(and %1 %2)
            (for [i (range (count nstr))]
              (prime? (Integer/parseInt (.substring nstr i)))))))
              
(defn right-truncatable-prime?
  [n]
  (let [nstr (str n)]
    (reduce #(and %1 %2)
            (for [i (range (count nstr) 0 -1)]
              (prime? (Integer/parseInt (.substring nstr 0 i)))))))
              
(defn truncatable-prime?
  [n]
  (and (right-truncatable-prime? n)
       (left-truncatable-prime?  n)))

(def *upper-limit* 739397)

(defn euler-037
  []
  (time
   (apply + (for [n (range 8 (inc *upper-limit*))
                  :when (truncatable-prime? n)] n))))

(deftest test-euler-037
  []
  (is (= (solution) 748317)))
