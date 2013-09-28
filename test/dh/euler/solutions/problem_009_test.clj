(ns dh.euler.solutions.problem_009_test
  (:require [dh.euler.solutions.problem_009 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-009
  (is (= (euler-009) 31875000)))
