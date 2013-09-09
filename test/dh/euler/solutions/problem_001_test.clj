(ns dh.euler.solutions.problem_001_test
  (:require [dh.euler.solutions.problem_001 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-001
    (is (= (euler-001) 233168)))
