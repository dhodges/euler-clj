(ns dh.euler.problem_010)

;; http://projecteuler.net/index.php?section=problems&id=10
;;
;; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
;;
;; Find the sum of all the primes below two million.

(defn prime?
  [n]
  (cond (= n 1)
        false
        (< n 4)
        true
        (= (rem n 2) 0)
        false
        (< n 9)
        true
        (= (rem n 3) 0)
        false
        :else
        (loop [r (Math/floor (Math/sqrt n))
               f 5]
          (cond (> f r)
                true
                (= (rem n f) 0)
                false
                (= (rem n (+ f 2)) 0)
                false
                :else
                (recur r (+ f 6))))))

(defn euler-010
  []
  (time
   (loop [n 1999999 psum 0]
     (if (= n 1)
       psum
       (recur (dec n) (if (prime? n) (+ psum n) psum))))))


(deftest test-euler-010
  (is (= (euler-010) 142913828922)))
