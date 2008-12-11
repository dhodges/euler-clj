#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; http://projecteuler.net/index.php?section=problems&id=35
;;
;; Problem 35
;; 17 January 2003
;;
;; The number 197 is called a circular prime because all rotations
;; of the digits: 197, 971, and 719, are themselves prime.
;;
;; There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17,
;; 31, 37, 71, 73, 79, and 97.
;;
;; How many circular primes are there below one million?


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


(defn rotate-left-str
  [s n]
  (assert (>= n 0))
  (let [n (rem n (count s))]
    (cond (= n 0)
          s
          (= n (count s))
          s
          :else
          (str
           (.substring s n)
           (.substring s 0 n)))))


(defn rotate-left-num
  [num n]
  (Integer/parseInt (rotate-left-str (str num) n)))


(defn circular-prime?
  [n]
  (and (prime? n)
       (reduce #(and %1 %2)
               (for [i (range (count (str n)))]
                 (prime? (rotate-left-num n i))))))

(defn solution
  []
  (time
   (count
    (for [n (range 2 1000000) :when (circular-prime? n)] n))))


(defn test-solution
  []
  (= (solution) 55))

