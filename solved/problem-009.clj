#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user
  (:use [clojure.contrib.test-is]))

;; http://projecteuler.net/index.php?section=problems&id=9
;;
;; A Pythagorean triplet is a set of three natural numbers, a  b  c, for which,
;;     a² + b² = c²
;;
;; For example, 3² + 4² = 9 + 16 = 25 = 5².
;;
;; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
;; Find the product abc.
;;
;; http://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple


(defn nth-triple
  [n]
  (let [a n]
    (if (odd? n)
      (let [b  (/ (dec (* a a)) 2)
            c  (inc b)]
        (list a b c))
      (let [b  (dec (* (/ a 2) (/ a 2)))
            c  (+ b 2)]
        (list a b c)))))
   

(defn make-triple
  [n]
  (let [a  (inc (* 2 n))
        b  (* (* 2 n) (inc n))
        c  (inc b)]
    (list a b c)))

    
(defn find-triple
  []
  (loop [n 1]
    (let [[a b c] (make-triple n)
          total   (+ (* a a) (* b b) (* c c))]
      (cond (= total 1000)
            '(a b c)
            (> total 1000000)
            nil
            :else
            (recur (inc n))))))

(defn euclid-triple
  [m n]
  (list  (* 2 m n)
         (- (* m m) (* n n))
         (+ (* m m) (* n n))))


;; =====================================

(defn natural?
  [n]
  (and (>= n 0)
       (= (class n) java.lang.Integer)))

(defn triple?
  [a b c]
  (and (and (natural? a) 
            (natural? b) 
            (natural? c))
       (< a b c)
       (= (+ (* a a) (* b b)) (* c c))))

(defn print-triple
  [a b c]
  (println (format "%s + %s + %s = %s" a b c (+ a b c)))
  (println (format "%s * %s * %s = %s" a b c (* a b c))))


(defn euler-009
  []
  (time
   (for [a (range 1001)
         b (range (inc a) 1001)
         c (range (inc b) 1001)
         :when (and (triple? a b c) (= 1000 (+ a b c)))]
       (* a b c))))
      
(deftest test-euler-009
  []
  (= (euler-009) 31875000))

