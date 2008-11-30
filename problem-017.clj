#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; Problem 17
;; 17 May 2002
;;
;; If the numbers 1 to 5 are written out in words: one, two, three, four, five, 
;; then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
;;
;; If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, 
;; how many letters would be used?
;;
;; NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) 
;; contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. 
;;
;; The use of "and" when writing out numbers is in compliance with British usage.

(def wordmap
     {1 "one", 2 "two", 3 "three", 4 "four", 5 "five", 
      6 "six", 7 "seven", 8 "eight", 9 "nine", 10 "ten", 
      11 "eleven", 12 "twelve", 13 "thirteen", 14 "fourteen", 
      15 "fifteen", 16 "sixteen", 17 "seventeen", 18 "eighteen", 
      19 "nineteen", 20 "twenty", 30 "thirty", 40 "forty", 50 "fifty",
      60 "sixty", 70 "seventy", 80 "eighty", 90 "ninety", 100 "hundred", 1000 "thousand"})


(defn qr
  [n d]
  [(quot n d) (rem n d)])


(defn written-number
  [n]
  (let [word   (ref "")
        append #(dosync (ref-set word (str @word %)))]

    (let [[q n]  (qr n 1000)]
      (if (> q 0)
        (append (str (wordmap q) (wordmap 1000))))

      (let [[q n] (qr n 100)]
        (when (> q 0)
          (append (str (wordmap q) (wordmap 100)))
          (if (> n 0)
            (append "and")))

        (cond (< n 20)
              (append (str (wordmap n)))
              :else
              (let [[q r] (qr n 10)]
                (append (str (wordmap (* q 10)) (wordmap r))))))

      @word)))
  
(defn solution
  []
  (let [lettercount (ref 0)]
    (dosync 
     (doseq [i (range 1 1001)]
       (ref-set lettercount 
                (+ @lettercount (count (written-number i))))))
    (printf "result => %d\n" @lettercount)))

; != 1366

