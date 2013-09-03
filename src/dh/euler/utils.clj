(ns dh.euler.utils)

(defn pow
  [a b]
  (reduce * (for [n (range b)] a)))

(defn divides?
  [n d]
  "is n divisible by d?"
  (= (rem n d) 0))

(defn highest-factor
  [n f]
  "number <= n that is a factor of f"
  (if (divides? n f)
    n
    (recur (dec n) f)))

(defn fib
  [n]
  (if (= n 1)
    1
    (* n (fib (dec n)))))

;; fibonacci sequence
(def fibs
  (lazy-cat [0 1] (map + fibs (rest fibs))))



; -----------------------------------------------------
; see: http://clj-me.cgrand.net/index.php?s=Primes

(defn primes [max]
  (let [enqueue (fn [sieve n factor]
                  (let [m (+ n (+ factor factor))]
                    (if (sieve m)
                      (recur sieve m factor)
                      (assoc sieve m factor))))
        next-sieve (fn [sieve candidate]
                     (if-let [factor (sieve candidate)]
                       (-> sieve
                         (dissoc candidate)
                         (enqueue candidate factor))
                       (enqueue sieve candidate candidate)))]
    (cons 2 (vals (reduce next-sieve {} (range 3 max 2))))))


; The Haskell Road to Logic, Maths and Programming
;
; LD(n) indicates the least divisor of n
;
; Proposition 1.2
; 1. If n > 1 then LD(n) is a prime number
; 2. If n > 1 and n is not a prime number, then (LD(n))² ≤ n

(defn ldf
  [k n]
  (cond (divides? k n)
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

;; NB: lazy-cons is gone
;; see: http://clojure.org/lazier for use of lazy-seq

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

(defn squint
  "integer square root"
  [n]
  (int (Math/floor (Math/sqrt n))))

(defn factorise
  "return the factors of the given number"
  [n]
  (let [sqrt (squint n)
        factors (loop [x 1 factors '()]
                  (if (> x sqrt)
                    factors
                    (recur (inc x)
                           (if (factor? n x)
                             (concat [x (/ n x)] factors)
                             factors))))]
    (sort (set factors))))


