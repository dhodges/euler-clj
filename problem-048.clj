#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; http://projecteuler.net/index.php?section=problems&id=48
;;
;; Problem 48
;; 18 July 2003
;;
;; The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
;;
;; Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.



(defn pow [a b] (reduce * (for [n (range b)] a)))

(defn solution
  []
  (time
   (apply str (reverse
               (take 10 (reverse (str (reduce + (for [n (range 1 1001)] (pow n n))))))))))



(defn test-solution
  []
  (= (solution) 9110846700))
