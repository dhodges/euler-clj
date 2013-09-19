(ns dh.euler.util.sequences_test
  (:require [dh.euler.util.sequences :refer :all]
            [clojure.test :refer :all]))

(deftest pascal-diagonal-item-test
  (is (= (pascal-diagonal-item  2 3)   3))
  (is (= (pascal-diagonal-item  3 8)  36))
  (is (= (pascal-diagonal-item 10 5) 715)))

(deftest pascal-diagonal-test
  (is (= (pascal-diagonal 3 10) [1 3 6 10 15 21 28 36 45 55])))
