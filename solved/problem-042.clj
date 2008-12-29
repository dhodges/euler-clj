#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [clojure.contrib.test-is])
  (:import [java.io File]
           [org.apache.commons.io FileUtils]))

;; http://projecteuler.net/index.php?section=problems&id=42
;;
;;; Problem 42
;; 25 April 2003
;;
;; The nth term of the sequence of triangle numbers is given by,
;; tn = �n(n+1); so the first ten triangle numbers are:
;;
;; 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
;;
;; By converting each letter in a word to a number corresponding to
;; its alphabetical position and adding these values we form a word value.
;;
;; For example, the word value for SKY is 19 + 11 + 25 = 55 = t10.
;; If the word value is a triangle number then we shall call the word a triangle word.
;;
;; Using words.txt (right click and 'Save Link/Target As...'),
;; a 16K text file containing nearly two-thousand common English words,
;; how many are triangle words?

(defn nth-triangle
  [n]
  (* (/ n 2) (inc n)))

(defn triangle-number?
  [num]
  (loop [n 1]
    (let [tn (nth-triangle n)]
      (cond (= num tn) true
            (< num tn) false
            :else
            (recur (inc n))))))

(defn triangle-word?
  [w]
  (triangle-number?
   (apply + (map #(- (int %) 64) (.toUpperCase w)))))

(def words 
     (sort 
      (.split 
       (apply str (remove #(= % \")
                          (FileUtils/readFileToString (new File "words.txt"))))
       ",")))

(defn euler-42
  []
  (time
   (count (filter triangle-word? words))))


(deftest test-euler-42
  []
  (is (= (solution) 162)))
