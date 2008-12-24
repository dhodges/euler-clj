#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user
  (:use [clojure.contrib.test-is])
  (:import [java.lang.Math]))

;; The prime factors of 13195 are 5, 7, 13 and 29.
;;
;; What is the largest prime factor of the number 600851475143 ?

;; http://projecteuler.net/index.php?section=problems&id=3

;; http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Algorithm_details_and_complexity
;; primes = sieve [2..]
;; sieve (p : xs) = p : sieve [x | x <− xs, x ‘mod‘ p > 0]


;; =======================================================
;; First solution uses a functional approach which prompty overflows the stack
;;   :(

(defn is-a-factor?
  [x y]
  (= java.lang.Integer (class (/ x y))))

(defn not-a-factor?
  [x y]
  (not (is-a-factor? x y)))

(defn sieve
  [xs]
  (let [head (first xs)
        tail (rest  xs)]
    (lazy-cons head 
               (sieve (for [x tail :when (not-a-factor? x head)] x))
               )))

(def primes
     (sieve (range 2 (int (Math/sqrt 600851475143)))))

(def prime-factors
     (filter #(is-a-factor? 600851475143 %) primes))

; the last item will be the highest prime factor
;; (time 
;;  (println 
;;   (format "the highest prime factor of 600851475143 is: %s"
;;           (last prime-factors))))

;; ; produces:
;; user=> (doseq [p prime-factors] (println p))
;; (doseq [p prime-factors] (println p))
;; 839
;; 1471
;; 6857
;; java.lang.StackOverflowError (NO_SOURCE_FILE:0)
;; user=> 


;; =======================================================
;; Second solution tries iteratively

(defn not-factors?
  "none of the numbers is a factor of x"
  [x numbers]
  (reduce #(and %1 %2) 
          (map #(not-a-factor? x %) 
               numbers)))
             

(defn next-prime
  "'primes' is a list in ascending order"
  [primes upper-limit]
  (loop [next (+ 2 (last primes))]
    (cond 
     (> next upper-limit)
       nil
     (not-factors? next primes)
       next
     :else
       (recur (+ 2 next)))))


(defn primes-less-than
  [n]
  (loop [primes [2 3]]
    (let [p (next-prime primes n)]
      (if (not p)
        (cons 1 primes)
        (recur (conj primes p))))))


(defn pfactors
  "return a list of prime factors of the given number n"
  [n]
  ; largest prime-factor will be ≦ √n"
  (let [upper-limit (int (Math/sqrt n))]
    (loop [primes   [2 3]
           pfactors (cons 1 (filter #(is-a-factor? n %) primes))]
      (let [p (next-prime primes upper-limit)]
        (if (not p)
          pfactors
          (recur (conj primes p) 
                 (if (is-a-factor? n p)
                   (conj pfactors p)
                   pfactors)))))))


(defn largest-prime-factor-of
  [n]
  (reduce max (pfactors n)))

(defn euler-003
  []
  (largest-prime-factor-of 600851475143))

(deftest test-euler-003
  []
  (= (euler-003) 6857))

;; =======================================================
;; here we go...

;; (print "calculating largest prime factor of 600851475143... ")
;; (. System/out flush)

;; (println (time (largest-prime-factor-of 600851475143)))

;;  ./problem-003.clj 
;;
;; calculating largest prime factor of 600851475143... "Elapsed time: 6297507.205 msecs"
;; 6857



;; =======================================================
;; solutions from 003_overview.pdf

(defn overview-solution
  [n]
  (loop [n n
         last-factor 1
         factor 2]
    (if (<= n 1)
      last-factor
      (if (is-a-factor? n factor)
        (recur (loop [n (/ n factor)]
                 (if (not-a-factor? n factor)
                   n
                   (recur (/ n factor))))
               factor
               (inc factor))
        (recur n
               last-factor
               (inc factor))))))


(defn overview-solution-2
  [n]
  (loop [n (loop [n n]
             (if (not-a-factor? n 2)
               n
               (recur [(/ n 2)])))
         last-factor (if (not-a-factor? n 2)
                       1
                       2)
         factor 3]

    (if (<= n 1)
      last-factor
      (if (is-a-factor? n factor)
        (recur (loop [n (/ n factor)]
                 (if (not-a-factor? n factor)
                   n
                   (recur (/ n factor))))
               factor
               (+ 2 factor))
        (recur n
               last-factor
               (+ 2 factor))))))
          
