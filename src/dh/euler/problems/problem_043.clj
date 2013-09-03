(ns dh.euler.problems.problem_043
  (:use [clojure.contrib.lazy-seqs :only (permutations)]))

;; http://projecteuler.net/index.php?section=problems&id=43

;; Problem 43
;; 09 May 2003

;; The number, 1406357289, is a 0 to 9 pandigital number because it is made up of
;; each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string
;; divisibility property.
;;
;; Let d1 be the 1st digit, d2 be the 2nd digit, and so on.
;; In this way, we note the following:
;;
;;     * d2d3d4=406  is divisible by  2
;;     * d3d4d5=063  is divisible by  3
;;     * d4d5d6=635  is divisible by  5
;;     * d5d6d7=357  is divisible by  7
;;     * d6d7d8=572  is divisible by 11
;;     * d7d8d9=728  is divisible by 13
;;     * d8d9d10=289 is divisible by 17
;;
;; Find the sum of all 0 to 9 pandigital numbers with this property.

(defn divisible?
  "is 'a' (evenly) divisible by 'b'?"
  [a b]
  (= 0 (rem a b)))

(defn bingo
  [n]
  (let [nstr (str n)]
    (and (divisible? (Long/parseLong (.substring nstr 1 4))   2)
         (divisible? (Long/parseLong (.substring nstr 2 5))   3)
         (divisible? (Long/parseLong (.substring nstr 3 6))   5)
         (divisible? (Long/parseLong (.substring nstr 4 7))   7)
         (divisible? (Long/parseLong (.substring nstr 5 8))  11)
         (divisible? (Long/parseLong (.substring nstr 6 9))  13)
         (divisible? (Long/parseLong (.substring nstr 7 10)) 17))))

(defn permutations-of
  [num]
  (map #(Long/parseLong %)
       (map #(apply str %) (permutations (str num)))))


(defn euler-043
  []
  (time
   (apply + (filter bingo (permutations-of 9876543210)))))

