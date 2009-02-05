#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [clojure.contrib.test-is]))

;; http://projecteuler.net/index.php?section=problems&id=16
;;
;; Problem 16
;; 03 May 2002
;;
;; 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
;;
;; What is the sum of the digits of the number 2^1000?


(defn pow
  [n exp]
  (cond (= n 0)
        1
        (= n 1)
        n
        :else
        (let [base (ref n)]
          (dosync
           (dotimes [i (dec exp)]
             (ref-set base (* @base n))))
          @base)))


(defn euler-016
  []
  (time 
   (reduce + (map #(- (int %) 48) 
                  (seq (str (pow 2 1000)))))))

(deftest test-euler-016
  []
  (is (= (solution) 1366)))
