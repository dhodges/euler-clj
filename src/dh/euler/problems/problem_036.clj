(ns dh.euler.problems.problem_036)

;; http://projecteuler.net/index.php?section=problems
;;
;; Problem 36
;; 31 January 2003
;;
;; The decimal number, 585 = 1001001001 (binary),
;; is palindromic in both bases.
;;
;; Find the sum of all numbers, less than one million, which are palindromic
;; in base 10 and base 2.
;;
;; (Please note that the palindromic number, in either base,
;; may not include leading zeros.)


(defn palindrome?
  [s]
  (= s (apply str (reverse s))))


(defn palindromic-both?
  [n]
  (and (palindrome? (str n))
       (palindrome? (Integer/toString n 2))))


(defn euler-036
  []
  (time
   (apply + (for [n (range 1000000)
                  :when (palindromic-both? n)] n))))


(deftest test-euler-036
  []
  (is (= (solution) 872187)))
