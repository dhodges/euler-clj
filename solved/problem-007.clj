#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [clojure.contrib.test-is]))

;; http://projecteuler.net/index.php?section=problems&id=7
;;
;; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, 
;; we can see that the 6th prime is 13.
;;
;; What is the 10001st prime number?

(defn is-a-factor?
  [x y]
  (= java.lang.Integer (class (/ x y))))

(defn not-a-factor?
  [x y]
  (not (is-a-factor? x y)))

(defn not-factors?
  "none of the numbers is a factor of x"
  [x numbers]
  (reduce #(and %1 %2) 
          (map #(not-a-factor? x %) 
               numbers)))
             
(defn next-prime
  "'primes' is a list in ascending order"
  [primes]
  (loop [next (+ 2 (last primes))]
    (if (not-factors? next primes)
      next
      (recur (+ 2 next)))))



(defn nth-prime
  [n]
  (cond (= n 1)
        2
        (= n 2)
        3
        :else
        (loop [primes [2 3]
               count n]
          (if (= count 2)
            (last primes)
            (let [next (next-prime primes)]
              (recur (conj primes next)
                     (dec count)))))))
        

(defn euler-007
  []
  (time
   (nth-prime 10001)))

(deftest test-euler-007
  []
  (is (= (euler-007) 104743)))


