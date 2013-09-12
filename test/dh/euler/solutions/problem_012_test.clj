(ns dh.euler.solutions.problem_012_test
  (:require [dh.euler.solutions.problem_012 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-012
  (is (= (euler-012) 76576500)))
