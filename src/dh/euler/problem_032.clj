(ns dh.euler.problem_032
  (:use [dh.euler.utils]))

;; http://projecteuler.net/index.php?section=problems&id=32
;;
;; Problem 32
;; 06 December 2002
;;
;; We shall say that an n-digit number is pandigital if it makes use of all the digits
;; 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigitial.
;;
;; The product 7254 is unusual, as the identity, 39 × 186 = 7254,
;; containing multiplicand, multiplier, and product is 1 through 9 pandigital.
;;
;; Find the sum of all products whose multiplicand/multiplier/product
;; identity can be written as a 1 through 9 pandigital.
;;
;; HINT: Some products can be obtained in more than one way so be sure
;; to only include it once in your sum.



(defn divisor-pairs
  [n]
  (loop [divisors (rest (proper-divisors n))
         pairs    []]
    (if (empty? divisors)
      pairs
      (recur (rest (reverse (rest divisors)))
             (conj pairs [(first divisors) (last divisors)])))))


; Notes
; - only test triples (multiplicand/multiplier/product) of length 9 digits
; - lower/upper bounds = 123456788/987654320 ?


(defn pandigital-mmp?
  [n]
  (filter #(let [s (apply str %)]
             (and (pandigital-str? s) (= 9 (count s))))
          (map #(conj % n) (divisor-pairs n))))


(defn euler-32
  []
  (time
   (let [numbers (filter #(not (nil? %))
                         (for [x (range 10000)]
                           (first (pandigital-mmp? x))))]
     (apply + (for [n numbers]
                (last n))))))



(deftest test-pandigital-mmp?
  (is (= (pandigital-mmp? (list 39 186) 7254) true)))


(deftest test-euler-32
  (is (= (euler-32) 45228)))

