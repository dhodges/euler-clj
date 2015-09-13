;; Surprisingly there are only three numbers that can be written as
;; the sum of fourth powers of their digits:
;;
;;     1634 = 1^4 + 6^4 + 3^4 + 4^4
;;     8208 = 8^4 + 2^4 + 0^4 + 8^4
;;     9474 = 9^4 + 4^4 + 7^4 + 4^4
;;
;; As 1 = 1^4 is not a sum it is not included.
;;
;; The sum of these numbers is 1634 + 8208 + 9474 = 19316.
;;
;; Find the sum of all the numbers that can be written as the sum of
;; fifth powers of their digits.
;;
;; http://projecteuler.net/problem=30
;;
;; Answer: 443839

(ns dh.euler.solutions.030)


; Notes
;
; Armstrong Numbers:
; http://illuminations.nctm.org/LessonDetail.aspx?id=L648
; http://en.wikipedia.org/wiki/Armstrong_numbers


(defn pow [a b] (reduce * (for [n (range b)] a)))

(defn sum-of-powers
  [num k]
  (apply + (map #(pow % k)
                (for [n (str num)] (- (int n) 48)))))

(defn armstrong?
  [n k]
  (= n (sum-of-powers n k)))


(defn maximum-armstrong-sum
  [k]
  (* k (pow (- 10 1) k)))

(defn euler-030
  []
  (time (apply +
               (filter #(armstrong? % 5)
                       (range (maximum-armstrong-sum 5) 1 -1)))))
