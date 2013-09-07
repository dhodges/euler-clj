(ns dh.euler.utils.core)

(defn pow
  [a b]
  (reduce * (for [n (range b)] a)))

(defn divides?
  [n d]
  "is n divisible by d?"
  (= (rem n d) 0))

(defn is-a-factor?
  [n d]
  (divides? n d))

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

; fibonacci
; taken from: http://blog.n01se.net/?p=33

(def fibs
  (lazy-cat [0 1] (map + fibs (rest fibs))))

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


