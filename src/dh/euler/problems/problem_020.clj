(ns dh.euler.problems.problem_020)

;; http://projecteuler.net/index.php?section=problems&id=20
;;
;; Problem 20
;; 21 June 2002
;;
;; n! means n × (n − 1) × ... × 3 × 2 × 1
;;
;; Find the sum of the digits in the number 100!

(defn euler-020
  []
  (time
   (apply +
          (map #(- (int %) 48)
               (str (nth-fibonacci 100))))))

(deftest test-euler-020
  []
  (is (= (solution) 648)))
