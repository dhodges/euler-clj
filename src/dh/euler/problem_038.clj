(ns dh.euler.problem_038
  (:use [dh.euler.utils])
  (:use [clojure.contrib.trace]))

;; http://projecteuler.net/index.php?section=problems&id=38
;;
;; Problem 38
;; 28 February 2003
;;
;; Take the number 192 and multiply it by each of 1, 2, and 3:
;;
;;     192 × 1 = 192
;;     192 × 2 = 384
;;     192 × 3 = 576
;;
;; By concatenating each product we get the 1 to 9 pandigital, 192384576.
;; We will call 192384576 the concatenated product of 192 and (1,2,3)
;;
;; The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4,
;; and 5, giving the pandigital, 918273645, which is the concatenated
;; product of 9 and (1,2,3,4,5).
;;
;; What is the largest 1 to 9 pandigital 9-digit number that can be
;; formed as the concatenated product of an integer with (1,2, ... , n)
;; where n > 1?


(defn catprod-pandigital
  [number]
  (loop [n 1
         catprod (str number)]
    (cond (not (= catprod (apply str (distinct catprod))))
          false
          (and (= (count catprod) 9)
               (pandigital? catprod))
          [number (Long/parseLong catprod)]
          :else
          (recur (inc n)
                 (strcat catprod (* number (inc n)))))))

(def cmp (proxy [java.util.Comparator] []
           (compare [o1 o2]
                    (let [num1 (second o1)
                          num2 (second o2)]
                      (cond (< num1 num2)
                            -1
                            (= num1 num2)
                            0
                            :else
                            1)))))

(defn generate-pandigitals
  [limit]
  (loop [n 1
         pans []]
    (if (> n limit)
      (reverse (sort cmp pans))
      (let [pan (catprod-pandigital n)]
        (recur (inc n)
               (if pan
                 (conj pans pan)
                 pans))))))

(defn euler-38
  []
  (time
   (second (first (generate-pandigitals 10000)))))


(deftest test-euler-38
  (is (= (euler-38) 932718654)))


