(ns dh.euler.util.core_test
  (:require [dh.euler.util.core :refer :all]
            [clojure.test :refer :all]))

(deftest pow-test
  (is (= (pow 2 3)   8))
  (is (= (pow 2 8) 256))
  (is (= (pow 123456789 2) 15241578750190521)))

(deftest divisible-by-test
  (is (= (divisible-by?  9 3) true))
  (is (= (divisible-by?  3 9) false))
  (is (= (divisible-by? 12 5) false)))

(deftest squint-test
  (is (= (squint 100) 10))
  (is (= (squint  23)  4)))

(deftest fibonacci-test
  (is (= (take 10 fibonacci) [1 1 2 3 5 8 13 21 34 55])))

; java.lang.ArithmeticException: integer overflow
;
;; (deftest nth-fibonacci-test
;;   (is (= (nth-fibonacci   1) 1))
;;   (is (= (nth-fibonacci  23) 1))
;;   (is (= (nth-fibonacci  90) 1))
;;   (is (= (nth-fibonacci 100) 7540113804746346429)))

(deftest is-a-factor-test
  (is (= (is-a-factor? 23 7) false))
  (is (= (is-a-factor? 635421 211807) true)))

(deftest factors-of-test
  (is (= (factors-of 100) [1 2 4 5 10 20 25 50 100])))

(deftest proper-divisors-test
  (is (= (proper-divisors 100) [1 2 4 5 10 20 25 50])))

(deftest highest-factor-test
  (is (= (highest-factor 100) 50)))

(deftest gcd-test
  (is (= (gcd 2429 123578)   7))
  (is (= (gcd  324 366282) 162))
  (is (= (gcd    8    800)   8)))
