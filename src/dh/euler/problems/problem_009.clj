;; A Pythagorean triplet is a set of three natural numbers, a  b  c, for which:
;;     a² + b² = c²
;;
;; For example, 3² + 4² = 9 + 16 = 25 = 5².
;;
;; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
;; Find the product abc.
;;
;; http://projecteuler.net/problem=9
;;
;; http://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple

(ns dh.euler.problems.problem_009)


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

;; http://en.wikipedia.org/wiki/Formulas_for_generating_Pythagorean_triples#Progressions_of_whole_and_fractional_numbers

(defn nth-stifel
  [n]
  (let [a (+ (* n 2) 1)
        b (+ (* n a) n)
        c (+ b 1)]
    [a b c]))

;; (def stifel-sequence
;;   (lazy-seq
;;    (loop [n 1]
;;      (cons (nth-stifel n)
;;            (recur (inc n))))))

;; (take-while #(<= 1000 (reduce + %)) stifel-sequence)


(defn find-stifel
  []
  (loop [n 1]
    (let [[a b c] (nth-stifel n)
          sum     (+ a b c)]
      (println (format "%3d: %s = %4d" n [a b c] sum))
      (cond
       (= 1000 sum) [a b c]
       (< 1000 sum) nil
       :else
       (recur (inc n))))))






;; =====================================

(defn natural?
  [n]
  (and (>= n 0)
       (= (class n) java.lang.Integer)))

(defn triple?
  [a b c]
  (and (natural? a)
       (natural? b)
       (natural? c)
       (< a b c)
       (= (+ (* a a)
             (* b b))
          (* c c))))


(defn euler-009
  []
  (for [a (range 1 20)
        b (range 1 20)
        c (range 1 20)
        :when (and (triple? a b c)
                   (= 1000 (+ a b c)))]
    (* a b c)))

;; (deftest test-euler-009
;;   (is (= (euler-009) 31875000)))
