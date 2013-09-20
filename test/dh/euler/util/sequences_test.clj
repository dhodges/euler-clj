(ns dh.euler.util.sequences_test
  (:require [dh.euler.util.sequences :refer :all]
            [clojure.test :refer :all]))

(deftest pascal-diagonal-item-test
  (is (= (pascal-diagonal-item  2 3)   3))
  (is (= (pascal-diagonal-item  3 8)  36))
  (is (= (pascal-diagonal-item 10 5) 715)))

(deftest pascal-diagonal-test
  (is (= (pascal-diagonal 3 10) [1 3 6 10 15 21 28 36 45 55])))

(deftest pascal-nth-row-test
  (is (= (pascal-nth-row  5) [1 4 6 4 1]))
  (is (= (pascal-nth-row 12) [1 11 55 165 330 462 462 330 165 55 11 1])))
