#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))


;; http://projecteuler.net/index.php?section=problems&id=56
;;
;; Problem 56
;; 07 November 2003
;;
;; A googol (10^(100)) is a massive number: one followed by one-hundred zeros;
;; 100^100 is almost unimaginably large: one followed by two-hundred zeros.
;; Despite their size, the sum of the digits in each number is only 1.
;;
;; Considering natural numbers of the form, a^b, where a, b < 100,
;; what is the maximum digital sum?

(defn digital_sum
  [n]
  (apply + (map #(- (int %) 48) (str n))))
                    

(defn euler-56
  []
  (reduce max (for [n (range 1 101)
                    e (range 1 101)]
                (digital_sum (pow n e)))))


(deftest test-euler-56
  (is (= (euler-56) 972)))
