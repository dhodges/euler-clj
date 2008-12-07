#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

	
; Add all the natural numbers below one thousand that are multiples of 3 or 5.
; http://projecteuler.net/index.php?section=problems&id=1


(defn multiple-of
  [x y]
  (= java.lang.Integer (class (/ x y))))


(defn solution
  []
  (print (reduce + (filter #(or (multiple-of % 3)
                              (multiple-of % 5))
                         (range 1000)))))

(defn test-solution
  []
  (= (solution) 233168))
