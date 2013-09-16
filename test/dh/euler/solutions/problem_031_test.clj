(ns dh.euler.solutions.problem_031_test
  (:use [dh.euler.solutions.problem_031 :refer :all]
        [dh.euler.util.core :refer :all]
        [clojure.test :refer :all]))


(deftest test-generate-coin-partitions
  (is (= (count-coin-partitions 50) 441)))

(deftest test-generate-partitions
  (is (= (count-partitions 10) 33)))

(deftest test-euler-031
  (is (= (euler-031) 73682)))
