(ns dh.euler.solutions.problem_021_test
  (:require [dh.euler.solutions.problem_021 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-021
  (is (= (euler-021) 31626)))
