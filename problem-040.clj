#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils]))

;; http://projecteuler.net/index.php?section=problems&id=40
;;
;; Problem 40
;; 28 March 2003
;;
;; An irrational decimal fraction is created by concatenating the
;; positive integers:
;;
;; 0.123456789101112131415161718192021...
;;              ^
;;
;; It can be seen that the 12th digit of the fractional part is 1.
;;
;; If d(n) represents the nth digit of the fractional part,
;; find the value of the following expression.
;;
;; d(1) × d(10) × d(100) × d(1000) × d(10000) × d(100000) × d(1000000)

; Notes
;;

(defn num-sequence
  []
  (let [nums (fn nums [n] (lazy-cons n (nums (inc n))))]
    (nums 1)))


(defn nth-digit-of
  "from num return the nth digit: (0 .. n-1)"
  [num n]
  (- (int (nth (str num) n)) 48))


(defn qr
  "returns the quotient and remainder"
  [n d]
  [(quot n d) (rem n d)])


;; 1 digit numbers x 9
;; 2 digit numbers x 90
;; 3 digit numbers x 900
;; 4 digit numbers x 9000
;; 5 digit numbers x 90000
;; 6 digit numbers x 900000

(defn d
  [n]
  (cond (< n 10) n
        (< n 190) (let [[q r] (qr (- n 10) 2)]
                    (nth-digit-of (+ 10 q) r))
        
        (< n 2890) (let [[q r] (qr (- n 190) 3)]
                     (nth-digit-of (+ 100 q) r)) 

        (< n 38890) (let [[q r] (qr (- n 2890) 4)]
                      (nth-digit-of (+ 1000 q) r))
        
        (< n 488890) (let [[q r] (qr (- n 38890) 5)]
                       (nth-digit-of (+ 10000 q) r))

        (< n 5888890) (let [[q r] (qr (- n 488890) 6)]
                        (nth-digit-of (+ 100000 q) r))
        ))


(defn euler-40
  []
  (time
   (* (d 1) (d 10) (d 100) (d 1000) (d 10000) (d 100000) (d 1000000))))


(deftest test-euler-40
  (= (solution) 210))

