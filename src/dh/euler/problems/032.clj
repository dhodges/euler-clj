;; We shall say that an n-digit number is pandigital if it makes use of
;; all the digits 1 to n exactly once; for example, the 5-digit number,
;; 15234, is 1 through 5 pandigitial.
;;
;; The product 7254 is unusual, as the identity, 39 × 186 = 7254,
;; containing multiplicand, multiplier, and product is 1 through 9 pandigital.
;;
;; Find the sum of all products whose multiplicand/multiplier/product
;; identity can be written as a 1 through 9 pandigital.
;;
;; HINT: Some products can be obtained in more than one way so be sure
;; to only include it once in your sum.
;;
;; http://projecteuler.net/index.php?section=problems&id=32

(ns dh.euler.problems.032
  (:use [dh.euler.util.core   :refer [proper-divisors]]
        [dh.euler.util.string :refer [str-pandigital?]])
  (:require  [clojure.string :as str]))

(defn divisor-pairs
  [n]
  (loop [divisors (rest (proper-divisors n))
         pairs    []]
    (if (empty? divisors)
      pairs
      (let [next-pair [(first divisors) (last divisors)]]
        (recur (rest (reverse (rest divisors)))
               (conj pairs next-pair))))))

(defn pandigital-1-thru-9-str?
  [s]
  (and (= 9 (count s))
       (str-pandigital? s)))

(defn pandigital-1-thru-9-triple?
  [[multiplicand multiplier product]]
  (pandigital-1-thru-9-str? (str multiplicand multiplier product)))

(defn product-triples
  [n]
  (map #(conj % n)
       (divisor-pairs n)))

(defn valid-triples-from
  [n]
  (filter pandigital-1-thru-9-triple?
        (product-triples n)))

(defn generate-valid-products
  ([] (generate-valid-products 1000 '()))
  ([n products]
     (if (= 10000 n)
       products
       (let [triples  (valid-triples-from n)
             n        (inc n)
             products (if (pos? (count triples))
                        (cons (last (nth triples 0)) products)
                        products)]
         (recur n products)))))

(defn euler-032
  []
  (time (reduce + (generate-valid-products))))
