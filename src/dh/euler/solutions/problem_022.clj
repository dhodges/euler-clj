;; Using names.txt (right click and 'Save Link/Target As...'), a 46K text file
;; containing over five-thousand first names, begin by sorting it
;; into alphabetical order, then working out the alphabetical value
;; for each name, multiply this value by its alphabetical position
;; in the list to obtain a name score.
;;
;; For example, when the list is sorted into alphabetical order, COLIN,
;; which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
;; So, COLIN would obtain a score of 938 × 53 = 49714.
;;
;; What is the total of all the name scores in the file?
;;
;; http://projecteuler.net/problem=22

(ns dh.euler.solutions.problem_022
  (:use [dh.euler.util.string :refer [strcat]]
        [clojure.string :refer [split replace]]
        [clojure.java.io :as io]
))

(-> "names.txt" io/resource io/file)

(defn dirname [path]
  (.getParent (java.io.File. path)))

(def names-file
  (-> "names.txt" io/resource io/file))

(def names
  (sort
   (map #(replace % "\"" "")
        (split (slurp names-file) #","))))

(defn name-score
  [name ndx]
  (* ndx (apply + (map #(- (int %) 64) name))))

(defn gather-name-score
  [[name & rest] ndx cumulative-score]
  (if (= nil name)
    cumulative-score
    (recur rest
           (inc ndx)
           (+ cumulative-score (name-score name ndx)))))


(defn euler-022
  []
  (gather-name-score names 1 0))
