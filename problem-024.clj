#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user
  (:use [clojure.contrib.lazy-seqs :only (permutations)]))

;; http://projecteuler.net/index.php?section=problems&id=24
;;
;; Problem 24
;; 16 August 2002
;;
;; A permutation is an ordered arrangement of objects. For example, 
;; 3124 is one possible permutation of the digits 1, 2, 3 and 4. 
;; 
;; If all of the permutations are listed numerically or alphabetically, 
;; we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
;;
;; 012   021   102   120   201   210
;;
;; What is the millionth lexicographic permutation of the digits 
;; 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

(defn fac
  "factorial"
  [n]
  (cond (= n 1)
        1
        (= n 0)
        1
        :else
        (* n (fac (dec n)))))

; see:
; http://forums.xkcd.com/viewtopic.php?f=11&t=28832#p926767

(defn solution
  []
  (time
   (loop [soln   []
          digits [0 1 2 3 4 5 6 7 8 9]
          remain 999999
          f      9]
     (if (> remain 0)
       (let [n (quot remain (fac f))
             c (nth digits n)]
         (recur (conj soln c)
                (concat (take n digits) (drop (inc n) digits))
                (- remain (* (fac f) n))
                (dec f)))
       (apply str (conj soln (nth digits 0)))))))


(defn test-solution
  []
  (= (solution) 278391546))

