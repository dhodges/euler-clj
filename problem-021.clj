#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; http://projecteuler.net/index.php?section=problems&id=21
;;
;; Problem 21
;; 05 July 2002
;;
;; Let d(n) be defined as the sum of proper divisors of n 
;; (numbers less than n which divide evenly into n).
;;
;; If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair 
;; and each of a and b are called amicable numbers.
;;
;; For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; 
;; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
;;
;; Evaluate the sum of all the amicable numbers under 10000.

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
    (sort (set (conj factors 1)))))


(defn amicable-pairs-less-than
  [n]
  (with-local-vars [pairs [] dsums []]
    (dotimes [i n]
      (var-set dsums
               (conj (var-get dsums) (apply + (proper-divisors i)))))
    (let [dsums (var-get dsums)]
      (dotimes [i n]
        (let [d (nth dsums i)]
          (if (and (< d n)
                   (not (= d i))
                   (= i (nth dsums d)))
            (var-set pairs
                     (conj (var-get pairs) (list i d)))))))
    
    (distinct (map sort (var-get pairs)))))

      
; lifted from clojure.contrib.seq-utils

(defn flatten [x]
  (let [s? #(instance? clojure.lang.Sequential %)]
    (filter (complement s?) (tree-seq s? seq x))))              
      

(defn solution 
  []
 (time
  (apply + (flatten (amicable-pairs-less-than 10000)))))

(defn test-solution
  []
  (= (solution) 31626))
