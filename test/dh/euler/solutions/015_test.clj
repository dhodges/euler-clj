(ns dh.euler.solutions.015-test
  (:use [dh.euler.solutions.015]
        [clojure.test]))

(deftest test-euler-015
  (is (= (number-of-routes  2)            6))
  (is (= (number-of-routes  3)           20))
  (is (= (number-of-routes 20) 137846528820))
  (is (= (euler-015)           137846528820)))
