;; The following iterative sequence is defined for the set of positive integers:
;;
;; n → n/2 (n is even)
;; n → 3n + 1 (n is odd)
;;
;; Using the rule above and starting with 13, we generate the following sequence:
;; 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
;;
;; It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
;; Although it has not been proved yet (Collatz Problem), it is thought that
;; all starting numbers finish at 1.
;;
;; Which starting number, under one million, produces the longest chain?
;;
;; NOTE: Once the chain starts the terms are allowed to go above one million.
;;
;; http://projecteuler.net/problem=14
;;
;; Answer: 837799

(ns dh.euler.solutions.014)


(defn collatz-chain
  [n]
  (if (= n 1)
    '(1)
    (cons n (collatz-chain (if (even? n)
                             (/ n 2)
                             (inc (* 3 n)))))))

(defn- collatz-count
  [n]
  (count(collatz-chain n)))

(defn euler-014
  []
  (time (loop [n 0
               max-chain {:n 13 :len 10}]
          (if (>= n 1000000)
            (:n max-chain)
            (let [n (inc n)
                  len (collatz-count n)]
              (recur n
                     (if (> len (:len max-chain))
                       {:n n :len len}
                       max-chain)))))))
