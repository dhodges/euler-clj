(ns dh.euler.problems.057
  (:use [dh.euler.util.core]
        [clojure.test :refer :all]))

;; http://projecteuler.net/index.php?section=problems&id=57

;; Problem 57
;; 21 November 2003

;; It is possible to show that the square root of two can be expressed as
;; an infinite continued fraction.
;;
;; √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
;;
;; By expanding this for the first four iterations, we get:
;;
;; 1 + 1/2                         = 3/2 = 1.5
;; 1 + 1/(2 + 1/2)                 = 7/5 = 1.4
;; 1 + 1/(2 + 1/(2 + 1/2))         = 17/12 = 1.41666...
;; 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
;;
;; The next three expansions are 99/70, 239/169, and 577/408,
;; but the eighth expansion, 1393/985, is the first example where the
;; number of digits in the numerator exceeds the number of digits
;; in the denominator.
;;
;; In the first one-thousand expansions, how many fractions
;; contain a numerator with more digits than denominator?
;;

; Notes
;; If  num(n) is the numerator of the fraction at n,
;; and den(n) is the denominator of the fraction at n,
;; such that num(n)/den(n) is the fraction at n,
;; then num(n+1) = 2 * den(n) + num(n)
;; and  den(n+1) = num(n) + den(n)
;;
;; i.e:
;; num(n+1) / den(n+1) = (2 * den(n) + num) / (num(n) + den(n))


(defn ngtd?
  "does the numerator have more digits than the denominator?"
  [r]
  (let [n (.numerator r) d (.denominator r)]
    (> (count (str n)) (count (str d)))))

(defn euler-057
  []
  (time
   (loop [r     (/ 3 2)
          n     1
          count 0]
     (if (> n 1000)
       count
       (let [num (.numerator r)
             den (.denominator r)]
         (recur (/ (+ (* 2 den) num) (+ num den))
                (inc n)
                (if (ngtd? r)
                  (inc count)
                  count)))))))


(deftest test-euler-57
  []
  (is (= (euler-057) 153)))
