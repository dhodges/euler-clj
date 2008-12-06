#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user (:import [java.math BigDecimal MathContext RoundingMode]
                  [java.text NumberFormat]))

;; http://projecteuler.net/index.php?section=problems&id=26
;;
;; Problem 26
;; 13 September 2002
;;
;; A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions 
;; with denominators 2 to 10 are given:
;;
;;     1/2	= 	0.5
;;     1/3	= 	0.(3)
;;     1/4	= 	0.25
;;     1/5	= 	0.2
;;     1/6	= 	0.1(6)
;;     1/7	= 	0.(142857)
;;     1/8	= 	0.125
;;     1/9	= 	0.(1)
;;     1/10	= 	0.1
;;
;; Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 
;; 1/7 has a 6-digit recurring cycle.
;;
;; Find the value of d < 1000 for which 1/d contains the longest recurring cycle 
;; in its decimal fraction part.


; Notes:
; apparently the length of the recurring cycle (if any)
; will not be larger than the denominator
; e.g: the size of the recurring cycle in 1/7 (142857) is 6
; and 6 < 7

; Console.printf

(defn find-recurring-cycle
  [n]
  (let [pattern (ref "")
        s (.substring (str (/ 1.0 n)) 2)]
    (dosync
     (doseq [ndx (range (dec (count s)))]
       (let [s (.substring s ndx)
             c (str (nth s 0))
             cndx (.indexOf (.substring s 1) c)]
         (when (> cndx -1)
           (let [cndx (inc cndx)
                 astr (.substring s 0 cndx)
                 bstr (.substring s cndx)]
             (if (and (.startsWith bstr astr)
                      (> (count astr) (count @pattern)))
               (ref-set pattern astr)))))))
    @pattern))
            
            
(defn solution
  []
  (time
   (let [num     (ref -1)
         pattern (ref "")]
     (dosync
      (doseq [n (range 1 1000)]
        (let [pat (find-recurring-cycle n)]
          (if (> (count pat) (count @pattern))
            (do
              (ref-set num n)
              (ref-set pattern pat))))))
     (println (str "1 / " @num " = "  (/ 1.0 @num)))
     (println (str "pattern: " @pattern))
     (println (str " length: " (count @pattern))))))

          
