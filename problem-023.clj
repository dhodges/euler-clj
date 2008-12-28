#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))

;; http://projecteuler.net/index.php?section=problems&id=23

;; Problem 23
;; 02 August 2002
;;
;; A perfect number is a number for which the sum of its proper divisors is 
;; exactly equal to the number. For example, the sum of the proper divisors of 28 
;; would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
;;
;; A number whose proper divisors are less than the number is called deficient and a 
;; number whose proper divisors exceed the number is called abundant.
;;
;; As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number 
;; that can be written as the sum of two abundant numbers is 24. By mathematical analysis,
;; it can be shown that all integers greater than 28123 can be written as the sum of 
;; two abundant numbers. However, this upper limit cannot be reduced any further by 
;; analysis even though it is known that the greatest number that cannot be expressed as 
;; the sum of two abundant numbers is less than this limit.
;;
;; Find the sum of all the positive integers which cannot be written as the sum of 
;; two abundant numbers.

(defn perfect?     [n] (= n (apply + (proper-divisors n))))

(defn deficient?   [n] (> n (apply + (proper-divisors n))))  
 
(defn abundant?    [n] (and (not (odd? n))
                            (< n (apply + (proper-divisors n)))))

(def abundants (filter abundant? (range 12 28123)))

(defn is-abundant? [n] (member-of-sequence? n abundants))

(defn sums-2-abundants?
  [n]
  (if (odd? n)
    false
    (let [half (quot n 2)]
      (if (and (= (* 2 half) n)
               (abundant? half))
        true
        (loop [diff 1]
          (cond (= diff half)
                false
                (and (abundant? diff)
                     (abundant? (- n diff)))
                true
                :else
                (recur (inc diff))))))))

(defn euler-23
  []
  (time
   (apply +
          (lazy-cons 1 (filter #(not (sums-2-abundants? %))
                               (range 2 28123))))))
    

(deftest test-euler-23
  (is (not (member-of-sequence? (euler-23) [4178875
                                            4178876
                                            197711987
                                            395437453
                                            395437454
                                            395437479]))))




  
  
