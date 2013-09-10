(ns dh.euler.solutions.problem_007_test
  (:require [dh.euler.solutions.problem_007 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-007
  (is (= (euler-007) 104743)))
