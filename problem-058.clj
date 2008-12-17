#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user
  (:use [clojure.contrib.seq-utils :only (flatten)])
  (:use [project_euler.dh_utils    :only (prime?)])
  (:use [clojure.contrib.test-is]))



; 2008-12-16
; currently get an error when trying to use clojure.contrib.test-is
; "Unable to resolve symbol: newmap in this context (template.clj:97)"

;; http://projecteuler.net/index.php?section=problems&id=58
;;
;; Problem 58
;; 05 December 2003
;;
;; Starting with 1 and spiralling anticlockwise in the following way,
;; a square spiral with side length 7 is formed.
;;
;; *37* 36  35  34 33  32 *31*
;;  38 *17* 16  15 14 *13* 30
;;  39  18  *5*  4 *3* 12  29
;;  40  19   6   1  2  11  28
;;  41  20  *7*  8  9  10  27
;;  42  21  22  23 24  25  26
;; *43* 44  45  46 47  48  49
;;
;; It is interesting to note that the odd squares lie along the bottom right diagonal,
;; but what is more interesting is that 8 out of the 13 numbers lying along both diagonals
;; are prime; that is, a ratio of 8/13 ≈ 62%.
;;
;; If one complete new layer is wrapped around the spiral above, a square spiral
;; with side length 9 will be formed. If this process is continued,
;; what is the side length of the square spiral for which the ratio of primes
;; along both diagonals first falls below 10%?

; Notes
;; 101 100  99  98  97  96  95  94  93  92  91
;; 102  65  64  63  62  61  60  59  58  57  90
;; 103  66 *37* 36  35  34  33  32 *31* 56  89
;; 104  67  38 *17* 16  15  14 *13* 30  55  88
;; 105  68  39  18  *5*  4  *3* 12  29  54  87
;; 106  69  40  19   6   1   2  11  28  53  86
;; 107  70  41  20  *7*  8   9  10  27  52  85
;; 108  71  42  21  22  23  24  25  26  51  84
;; 109  72 *43* 44  45  46  47  48  49  50  83
;; 110  73  74  75  76  77  78  79  80  81  82
;; 111 112 113 114 115 116 117 118 119 120 121


(defn nth-odd  [n] (- (* 2 n) 1))
(defn nth-even [n] (- (* 2 n) 2))

(defn sqr [n] (* n n))

(defn nw  [n] (inc (sqr (nth-even n))))
(defn ne  [n] (inc (* (nth-even n)
                      (dec (nth-even n)))))
(defn se  [n] (sqr (nth-odd n)))
(defn sw  [n] (inc (* (nth-odd n)
                      (nth-even n))))

(defn ratio-of-primes
  [n-sides]
  (let [n (/ (inc n-sides) 2)]
    (/ (count
        (filter prime?
                (flatten (for [i (range 2 (inc n))]
                           [(ne i) (nw i) (sw i)]))))
       (- (* 4 n) 3))))
                     
(defn euler-58
  []
  (time
   (let [threshold (/ 1.0 10.0)]
     (loop [n        4
            n-sides  7
            n-primes 8
            n-diagonals 13]
       (let [ratio-of-primes (/ n-primes n-diagonals)]
         (if (< ratio-of-primes threshold)
           n-sides
           (let [n (inc n)]
             (recur n
                    (+ n-sides 2)
                    (+ n-primes
                       (count (filter prime? [(ne n) (nw n) (sw n)])))
                    (+ n-diagonals 4)))))))))


(defn member-of-sequence?
  "assumes coll is a sorted list of numbers"
  [item coll]
  (cond (empty? coll)
        false
        (= item (first coll))
        true
        (< item (first coll))
        false
        :else
        (recur item (rest coll))))


(defn test-euler-58
  []
  (= (euler-58) 26241))



(deftest test-nw
  (is (= (map nw (range 2 7))
         [5 17 37 65 101])))

(deftest test-ne
  (is (= (map ne (range 2 7))
         [3 13 31 57  91])))

(deftest test-sw
  (is (= (map sw (range 2 7))
         [7 21 43 73 111])))

(deftest test-se
  (is (= (map se (range 2 7))
         [9 25 49 81 121])))

(deftest test-ratio-of-primes
  (is (= (ratio-of-primes 7)
         8/13)))

