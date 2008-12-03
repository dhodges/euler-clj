#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

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

(defn factor?
  [x y]
  (= (rem x y) 0))


(defn proper-divisors
  "return the proper divisors of the given number"
  [n]
  (let [squint  (int (Math/floor (Math/sqrt n)))
        factors (loop [x 2 factors '()]
                  (if (> x squint)
                    factors
                    (recur (inc x)
                           (if (factor? n x)
                             (concat [x (/ n x)] factors)
                             factors))))]
    (sort (set (conj factors 1)))))


(defn perfect?
  [n]
  (= n (apply + (proper-divisors n))))

(defn deficient?
  [n]
  (> n (apply + (proper-divisors n))))  
  

(defn abundant?
  [n]
  (< n (apply + (proper-divisors n))))

(defn abundant-numbers-less-than
  [n]
  (let [result (ref [])]
    (dosync
     (doseq [i (range 12 n)]
       (if (abundant? i)
         (ref-set result (conj @result i))))
     @result)))


(defn solution
  []
  (time
   (let [abundants (ref (reverse (abundant-numbers-less-than 28123)))
         results   (ref [])]
     (dosync
      (doseq [n (range 28123 1 -1)]
        (ref-set abundants (drop-while #(> % n) @abundants))
        (let [found (ref false)
              num-abundants (count @abundants)]
          (loop [ndx 0]
            (if (and (< ndx num-abundants)
                     (not @found))
              (do
                (if (abundant? (- n (nth @abundants ndx)))
                  (ref-set found true))
                (recur (inc ndx)))))

          (if (not @found)
            (ref-set results (conj @results n))))))

     (apply + @results))))


; != 4178875
