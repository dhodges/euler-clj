;; A palindromic number reads the same both ways. The largest palindrome
;; made from the product of two 2-digit numbers is 9009 = 91 x  99.
;;
;; Find the largest palindrome made from the product of two 3-digit numbers.
;;
;; http://projecteuler.net/problem=4
;;
;; Answer: 906609

(ns dh.euler.solutions.004
  (:use [dh.euler.util.string :refer [palindrome?]]))


; brute force

(defn find-palindromes
  []
  (let [palindromes (ref '())]
    (doseq [x (range 999 100 -1)
            y (range 998 100 -1)]
      (if (palindrome? (* x y))
        (dosync (ref-set palindromes (conj @palindromes [x y])))))
    @palindromes))


(defn find-largest-pair
  []
  (let [comp (proxy [java.util.Comparator] []
                    (compare [o1 o2]
                             (let [a (* (first o1) (second o1))
                                   b (* (first o2) (second o2))]
                               (cond (= a b)  0
                                     (< a b) -1
                                     :else 1))))
        pairs (sort comp (find-palindromes))
        pair  (last pairs)
        x (first pair)
        y (second pair)]
    (* x y)
    ))

(defn euler-004
  []
  (time (find-largest-pair)))
