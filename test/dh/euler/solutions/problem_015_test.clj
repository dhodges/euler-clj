(ns dh.euler.solutions.problem_015_test
  (:require [dh.euler.solutions.problem_015 :refer :all]
            [clojure.test :refer :all]))

(deftest test-euler-015
  (is (= (number-of-routes  2)            6))
  (is (= (number-of-routes  3)           20))
  (is (= (number-of-routes 20) 137846528820))
  (is (= (euler-015)           137846528820)))
