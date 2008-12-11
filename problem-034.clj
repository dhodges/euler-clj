#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; http://projecteuler.net/index.php?section=problems&id=34
;;
;; Problem 34
;; 03 January 2003
;;
;; 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
;;
;; Find the sum of all numbers which are equal to the sum of
;; the factorial of their digits.
;;
;; Note: as 1! = 1 and 2! = 2 are not sums they are not included.

; Notes
; Factorions
; see: http://en.wikipedia.org/wiki/Factorion


(def *upper-bound* (* (fac 9) 7))


(defn fac
  "factorial"
  [n]
  (cond (= n 1)
        1
        (= n 0)
        1
        :else
        (* n (fac (dec n)))))


(defn facsum
  "sum of the factorial of each digit in n"
  [n]
  (apply + (for [c (str n)]
             (fac (Integer/parseInt (str c))))))


(defn solution
  []
  (time
   (apply + (for [n (range 3 (inc *upper-bound*))
                  :when (= n (facsum n))]
              n))))

(defn test-solution
  []
  (= (solution) 40730))
