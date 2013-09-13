(ns dh.euler.solutions.problem_013_test
  (:require [dh.euler.solutions.problem_013 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-013
  (is (= (euler-013) "5537376230")))
