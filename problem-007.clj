#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

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
              (println (format "%s: %s" count next))
              (recur (conj primes next)
                     (dec count)))))))
        

(defn solution
  [n]
  (println)
  (println (format "nth-prime (%s) : %s" n (nth-prime n)))))

(defn test-solution
  []
  (= (nth-prime 10001) 104743))


