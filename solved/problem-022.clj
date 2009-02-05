#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [clojure.contrib.test-is])
  (:import [java.io File]
           [org.apache.commons.io FileUtils]))

;; http://projecteuler.net/index.php?section=problems&id=22
;;
;; Problem 22
;; 19 July 2002
;;
;; Using names.txt (right click and 'Save Link/Target As...'), a 46K text file 
;; containing over five-thousand first names, begin by sorting it into alphabetical order. 
;; Then working out the alphabetical value for each name, multiply this value 
;; by its alphabetical position in the list to obtain a name score.
;;
;; For example, when the list is sorted into alphabetical order, COLIN, 
;; which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. 
;; So, COLIN would obtain a score of 938 × 53 = 49714.
;;
;; What is the total of all the name scores in the file?

(def names 
     (sort 
      (.split 
       (apply str (remove #(= % \")
                          (FileUtils/readFileToString (new File "names.txt"))))
       ",")))



(defn name-score
  [name n]
  (* n (apply + (map #(- (int %) 64) name))))


(defn euler-022
  []
  (time
   (apply + (for [n (range (count names))]
              (name-score (nth names n) (inc n))))))


(defn test-euler-022
  []
  (= (solution) 871198282))




