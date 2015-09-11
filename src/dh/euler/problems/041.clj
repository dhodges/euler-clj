(ns dh.euler.problems.041
  (:use [dh.euler.util.core]
  (:use [clojure.contrib.duck-streams :only (spit)])
  (:use [clojure.contrib.lazy-seqs :only (permutations)])
  (:use [clojure.contrib.str-utils :only (str-join)])
  (:import [java.io RandomAccessFile]))

;; http://projecteuler.net/index.php?section=problems&id=41
;;
;; Problem 41
;; 11 April 2003
;;
;; We shall say that an n-digit number is pandigital if it makes use of
;; all the digits 1 to n exactly once.
;;
;; For example, 2143 is a 4-digit pandigital and is also prime.
;;
;; What is the largest n-digit pandigital prime that exists?

(defn factor?
  [x y]
  (= (rem x y) 0))

(defn factorise
  "return the factors of the given number"
  [n]
  (let [squint  (int (Math/floor (Math/sqrt n)))
        factors (loop [x 1 factors '()]
                  (if (> x squint)
                    factors
                    (recur (inc x)
                           (if (factor? n x)
                             (concat [x (/ n x)] factors)
                             factors))))]
    (sort (distinct factors))))

(defn prime?
  [n]
  (if (or (= (rem n 10) 5)
          (and (even? n) (not (= n 2))))
    false
    (= [1 n] (factorise n))))

(defn member?
  [item coll]
  (cond (empty? coll)
        false
        (= item (first coll))
        true
        :else
        (recur item (rest coll))))

(defn permutations-of
  [num]
  (map #(Long/parseLong %)
       (reverse
        (sort (map #(apply str %) (permutations (str num)))))))


(defn solution
  []
  (time
   (first (filter prime? (mapcat permutations-of [987654321
                                                  87654321
                                                  7654321))))))


(defn test-solution
  []
  (= (solution) 7652413))

