;; The decimal number, 585 = 1001001001 (binary),
;; is palindromic in both bases.
;;
;; Find the sum of all numbers, less than one million, which are palindromic
;; in base 10 and base 2.
;;
;; (Please note that the palindromic number, in either base,
;; may not include leading zeros.)
;;
;; http://projecteuler.net/problem=36
;;
;; Answer: 872187

(ns dh.euler.solutions.036
  (:use [dh.euler.util.string :refer [palindrome?]]))


(defn palindromic-both?
  [n]
  (and (palindrome? (str n))
       (palindrome? (Integer/toString n 2))))


(defn euler-036
  []
  (apply + (for [n (range 1000000) :when (palindromic-both? n)]
             n)))
