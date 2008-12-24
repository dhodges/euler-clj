#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user
  (:use [clojure.contrib.test-is]))

;; Each new term in the Fibonacci sequence is generated by adding the previous two terms. 
;; By starting with 1 and 2, the first 10 terms will be:
;;
;;     1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
;;
;; Find the sum of all the even-valued terms in the sequence which do not exceed four million.
;;
;; http://projecteuler.net/index.php?section=problems&id=2


; taken from: http://blog.n01se.net/?p=33
(def fibs (lazy-cat [0 1] (map + fibs (rest fibs))))

(def even-fibs (filter even? fibs))

(defn euler-002
  []
  (reduce + (take-while #(< % 4000000) 
                        even-fibs)))
  
(deftest test-euler-002
  []
  (= (euler-002) 4613732))