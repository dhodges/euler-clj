(ns dh.euler.problem_014)

;; Problem 14

;; http://projecteuler.net/index.php?section=problems&id=14

;; The following iterative sequence is defined for the set of positive integers:

;; n → n/2 (n is even)
;; n → 3n + 1 (n is odd)

;; Using the rule above and starting with 13, we generate the following sequence:
;; 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1

;; It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
;; Although it has not been proved yet (Collatz Problem), it is thought that
;; all starting numbers finish at 1.

;; Which starting number, under one million, produces the longest chain?

;; NOTE: Once the chain starts the terms are allowed to go above one million.

(defstruct term :start :chain :len)

(defn gen-chain
  [n]
  (if (= n 1)
    '(1)
    (cons n (gen-chain (if (even? n) (/ n 2) (inc (* 3 n)))))))


(defn euler-014
  []
  (loop [n 1
         t (struct-map term
             :start 13
             :chain '(13 40 20 10 5 16 8 4 2 1)
             :len 10)]
    (if (>= n 1000000)
      t
      (let [chain (gen-chain n)]
        (recur (inc n)
               (if (> (count chain) (t :len))
                 (struct-map term :start n :chain chain :len (count chain))
                 t))))))

(deftest test-euler-014
  (is (= (solution) 837799)))



