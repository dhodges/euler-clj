;; A Pythagorean triplet is a set of three natural numbers, a  b  c, for which:
;;     a² + b² = c²
;;
;; For example, 3² + 4² = 9 + 16 = 25 = 5².
;;
;; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
;;
;; Find the product abc.
;;
;; http://projecteuler.net/problem=9
;;
;; Answer: 31875000

(ns dh.euler.solutions.009
  (:use [clojure.math.numeric-tower :refer [floor]]))


;; Notes
;;
;; a² + b² = c²
;; a  + b  + c = 1000
;;
;; Therefore:
;; c       =  1000 - (a + b)
;; a² + b² = (1000 - (a + b))²
;;
;; solving for a and b:
;;
;; a = 1000(500 - b) / (1000 - b)
;; b = 1000(500 - a) / (1000 - a)
;;
;; Since there is only one solution a + b + c = 1000,
;; we can iterate 1000 >= b >= 1
;; and halt with the first integer value for b

(defn solve-for
  [n]
  (/ (* 1000 (- 500 n))
     (- 1000 n)))

(defn euler-009
  ([] (euler-009 1))
  ([b]
     (if (= b 1000)
       "WTF!"
       (let [a (solve-for b)
             c (- 1000 a b)]
         (if (= a (floor a))
           (* a b c)
           (recur (inc b)))))))

;; =====================================

;; http://en.wikipedia.org/wiki/Pythagorean_triple
;; http://en.wikipedia.org/wiki/Formulas_for_generating_Pythagorean_triples

;; (defn euclid-triple
;;   [m n]
;;   (list  (* 2 m n)
;;          (- (* m m) (* n n))
;;          (+ (* m m) (* n n))))

;; (defn natural?
;;   [n]
;;   (and (>= n 0)
;;        (= (class n) java.lang.Integer)))

;; (defn triple?
;;   [a b c]
;;   (and (natural? a)
;;        (natural? b)
;;        (natural? c)
;;        (< a b c)
;;        (= (+ (* a a)
;;              (* b b))
;;           (* c c))))
